package club.fallenstar.controller;

import club.fallenstar.entity.Product;
import club.fallenstar.entity.Trade;
import club.fallenstar.entity.User;
import club.fallenstar.service.ProductService;
import club.fallenstar.service.UserService;
import club.fallenstar.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    //登出
    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
        User user = (User) session.getAttribute("user");
        CookieUtils.cookie(0, response, user.getNickname(), user.getPassword());
        session.removeAttribute("user");
        return "redirect:/";
    }

    //空间
    @RequestMapping("/space")
    public String space(Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        String name = user.getNickname();
        List<Product> sell = productService.sellProduct(name);//我要卖
        List<Product> buy = productService.buyProduct(name);//我要买
        List<Product> sold = productService.soldProduct(name);//我卖过
        List<Product> bought = productService.boughtProduct(name);//我买到
        List<Trade> trades = productService.getMyTrades(name);
        Map<String,Trade> map = new HashMap<String, Trade>();
        for(Trade trade:trades){ map.put(trade.getProductNum(),trade); }
        model.addAttribute("sell",sell).addAttribute("buy",buy).addAttribute("sold",sold).addAttribute("bought",bought).addAttribute("map",map);
        return "space";
    }

    //添加商品
    @RequestMapping(value="/addProduct",method = RequestMethod.GET)
    public String addForm(HttpSession session){
        Product product = new Product();
        session.setAttribute("product", product);
        return "addProduct";
    }

    //编辑商品
    @RequestMapping(value="/editProduct",method = RequestMethod.GET)
    public String editForm(HttpSession session,@RequestParam String productNum){
        session.setAttribute("product",productService.findByNum(productNum));
        session.setAttribute("image",new String[4]);
        return "editProduct";
    }

    //编辑商品操作
    @RequestMapping(value="/editProduct",method = RequestMethod.POST)
    public String editProduct(@RequestParam String productNum, HttpSession session,@RequestParam String name,@RequestParam String category,@RequestParam String description,
                              @RequestParam Integer price){
        String[] picture = (String[]) session.getAttribute("image");
        productService.editProduct(productNum,name,category,description,price,picture);
        return "redirect:/detail?productNum="+productNum;
    }

    //添加商品操作
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public String addProduct(HttpSession session, @RequestParam String name,@RequestParam String description,@RequestParam int price,@RequestParam String category){
        Product product = (Product)session.getAttribute("product");
        User user = (User) session.getAttribute("user");
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setSeller(user.getNickname());
        productService.save(product);
        return "redirect:/user/space";
    }

    //接受订单
    @RequestMapping(value = "/accept",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> accept(@RequestParam String productNum,@RequestParam String buyer){
        Map<String,String> map = new HashMap<String, String>();
        if(productService.confirmTrade(buyer,productNum)){
            map.put("status","success");
        }else {
            map.put("status","failed");
        }
        return map;
    }

    //买家下订单
    @RequestMapping("/trade")
    public String trade(@RequestParam String productNum,HttpSession session){
        User user = (User) session.getAttribute("user");
        productService.placeTrade(user.getNickname(),productNum);
        session.setAttribute("justTraded","yes");
        return "redirect:/detail?productNum="+productNum;
    }

    //卖家删除商品
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String delete(@RequestParam String productNum,HttpSession session){
        Product product = productService.findByNum(productNum);
        User user = (User) session.getAttribute("user");
        if(user.getNickname().equals(product.getSeller())){
            productService.delete(productNum);
        }
        return "redirect:/user/space";
    }

    //买家取消订单
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> cancel(@RequestParam String productNum,HttpSession session){
        Map<String,String> map = new HashMap<String, String>();
        User user = (User) session.getAttribute("user");
        if(productService.cancelTrade(user.getNickname(),productNum)){
            map.put("result","success");
        }else {
            map.put("result","failed");
        }
        return map;
    }

    //成交后买家删除订单
    @RequestMapping(value = "/deleteTrade",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deleteTrade(@RequestParam Integer tradeId,HttpSession session){
        Map<String,String> map = new HashMap<String, String>();
        User user = (User) session.getAttribute("user");
        Trade trade = productService.findTradeById(tradeId);
        if(trade.getBuyer().equals(user.getNickname())){
            productService.deleteTradeById(tradeId);
            map.put("result","success");
        }
        return map;
    }

    //TA的空间
    @RequestMapping(value = "/viewspace",method = RequestMethod.POST)
    public String viewspace(@RequestParam String phone,Model model){
        User user = userService.findByPhone(phone);
        List<Product> sell = productService.sellProduct(user.getNickname());//ta要卖
        model.addAttribute("user",user);
        model.addAttribute("sell",sell);
        return "viewspace";
    }

    //编辑个人信息
    @RequestMapping(value = "/editinfo",method = RequestMethod.GET)
    public String editinfo(){
        return "editinfo";
    }

    //编辑个人信息操作
    @RequestMapping(value = "/editinfo",method = RequestMethod.POST)
    public String edituser(@RequestParam String email,@RequestParam String grade,@RequestParam String academy,@RequestParam String contact,HttpSession session){
        User user = (User)session.getAttribute("user");
        if(userService.edit(user.getNickname(),user.getNickname(),email,grade,academy,contact))
            return "redirect:/user/space";
        else
            return "error";
    }

    // 上传图片
    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile myfile,HttpSession session)
            throws IllegalStateException, IOException {
        // 原始名称
        String oldFileName = myfile.getOriginalFilename(); // 获取上传文件的原名
        // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
        String saveFilePath = "/home/hqs/image";
        // 上传图片
        if (myfile != null && oldFileName != null && oldFileName.length() > 0) {
            // 新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            // 新图片
            File newFile = new File(saveFilePath + "/" + newFileName);
            // 将内存中的数据写入磁盘
            myfile.transferTo(newFile);
            // 将新图片名称返回到前端
            Product product = (Product) session.getAttribute("product");
            String[] s = (String[])session.getAttribute("image");
            if(s==null) {
                s = product.getPicture();
            }
            for(int i=0;i<4;i++){
                if(s[i]==null) {
                    s[i] = newFileName;
                    break;
                }
            }
            product.setPicture(s);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", "成功啦");
            map.put("url", newFileName);
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("error", "图片不合法");
            return map;
        }
    }


}
