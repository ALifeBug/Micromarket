package club.fallenstar.controller;

import club.fallenstar.entity.*;
import club.fallenstar.service.ProductService;
import club.fallenstar.service.UserService;
import club.fallenstar.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    private  static  Map<String,String> map = new HashMap<String, String>();

    static {
        map.put("book","图书");map.put("clothes","衣服");map.put("food","食品");
        map.put("shoes","鞋靴");map.put("cosmetics","化妆品");map.put("electronic","电子产品");
        map.put("daily","生活用品");map.put("sports","运动户外");map.put("others","其他");
        map.put("vip","视频会员");
    }

    //首页
    @RequestMapping(value = {"/","/index"})
    public String index(@RequestParam(required = false) String order,@RequestParam(required = false) Integer pageNo,Model model,HttpSession session){
        if(order==null && pageNo==null){
             model.addAttribute("page",productService.findAllPages(1,10,"new"));
             model.addAttribute("order","new");
        }else {
            model.addAttribute("page",productService.findAllPages(pageNo,10,order));
            model.addAttribute("order",order);
        }
        if(session.getAttribute("shoppingcart")==null)
            session.setAttribute("shoppingcart",new ShoppingCart());
        return "index";
    }

    //分类
    @RequestMapping(value = {"/classify"})
    public String index(@RequestParam String order,
                        @RequestParam Integer pageNo,
                        @RequestParam String category,
                        Model model) {
        Page<Product> page = productService.findAllPagesByCategory(pageNo,10,order,category);
        model.addAttribute("page",page);
        model.addAttribute("order",order);
        model.addAttribute("category",category);
        return "category";
    }

    //搜索
    @RequestMapping(value = {"/search"})
    public String index(@RequestParam Integer pageNo,
                        @RequestParam String name,
                        Model model) {
        Page<Product> page = productService.findAllPagesByName(pageNo,10,name);
        model.addAttribute("page",page);
        model.addAttribute("name",name);
        return "search";
    }

    //商品详情
    @RequestMapping("/detail")
    public String detail(@RequestParam String productNum,Model model,HttpSession session){
        Product product = productService.findByNum(productNum);
        if(product==null){
            return "redirect:/";
        }else {
            List<Trade> trades = productService.findTradesByNum(productNum);
            int picNum = 0;
            for (int i = 0; i < 4; i++) {
                if (product.getPicture()[i] != null)
                    picNum++;
            }
            User user = (User) session.getAttribute("user");
            if (user != null) {
                if (productService.findUniqueTrade(productNum, user.getNickname()) != null) {
                    model.addAttribute("orderstate", "ordered");
                }
            }
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingcart");

            if(shoppingCart.inCart(productNum)){
                model.addAttribute("cartstate","put");
            }
            model.addAttribute("picNum", picNum);
            model.addAttribute("product", product);
            model.addAttribute("trades", trades);
            model.addAttribute("map", map);
            return "detail";
        }
    }

    //注册
    @RequestMapping(value = "/reg",method = RequestMethod.GET)
    public String regpage(){
        return "reg";
    }

    //注册检查
    @RequestMapping(value = "/userexist",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> nickname(@RequestParam String param) {
        Map<String, String> result = new HashMap<String, String>();
        if(userService.userExist(param)){
            result.put("user","reged");
        }else {
            result.put("user","not");
        }
        return  result;
    }

    //注册操作
    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public String reg(User user, Model model, HttpSession session,HttpServletResponse response) throws UnsupportedEncodingException {
        if(userService.save(user)){
            session.setAttribute("user",user);
            user = userService.findByName(user.getNickname());
            CookieUtils.cookie(30*24*60*60,response,user.getNickname(),user.getPassword());
            return "redirect:/";
        }else{
            model.addAttribute("result","failed");
            return "reg";
        }
    }

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginpage(){
        return "reg";
    }

    //登录检查
    @RequestMapping(value = "/checkpwd",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> checkpwd(@RequestParam String name,@RequestParam String pwd) {
        Map<String, String> result = new HashMap<String, String>();
        if(userService.checkpwd(name,pwd))
            result.put("result","success");
        else
            result.put("result","failed");
        return  result;
    }

    //登录操作
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String nickname,HttpSession session,HttpServletResponse response) throws UnsupportedEncodingException {
            User user = userService.findByName(nickname);
            session.setAttribute("user",user);
            CookieUtils.cookie(30*24*60*60,response,user.getNickname(),user.getPassword());
            return "redirect:/";
    }

    //购物车
    @RequestMapping("/shoppingcart")
    public String shoppingCart(){
        return "shoppingcart";
    }

    //加入购物车
    @RequestMapping("/addtocart")
    public String addtocart(@RequestParam String productNum,HttpSession session){
        Product product = productService.findByNum(productNum);
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingcart");
        if(!shoppingCart.inCart(productNum)){
            shoppingCart.addProduct(product);
            return "redirect:/detail?productNum="+productNum;
        }
        return "error";
    }

    //移出购物车
    @RequestMapping("/removefromcart")
    public String removefromcart(@RequestParam String productNum,HttpSession session){
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingcart");
        if(shoppingCart.inCart(productNum)){
            shoppingCart.removeProduct(productNum);
            return "redirect:/detail?productNum="+productNum;
        }
        return "error";
    }

    //读写图片
    @RequestMapping("/getImage")
    public void getImage(@RequestParam String imgName,HttpServletResponse response){
        String picUrl = "/home/hqs/image/";
        FileInputStream in;
        response.setContentType("application/octet-stream;charset=UTF-8");

        try {
            in = new FileInputStream(picUrl+imgName);
            int i = in.available();
            byte[] data = new byte[i];
            in.read(data);
            in.close();

            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
