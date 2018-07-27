package club.fallenstar.service;


import club.fallenstar.dao.TradeDao;
import club.fallenstar.dao.ProductDao;
import club.fallenstar.dao.UserDao;
import club.fallenstar.entity.Page;
import club.fallenstar.entity.Trade;
import club.fallenstar.entity.Product;
import club.fallenstar.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private TradeDao TradeDao;

    @Autowired
    private UserDao userDao;

    //保存商品
    public void save(Product product){
        product.setUpTime(new Date());
        product.setNumber(UUIDUtils.getUUID());
        productDao.save(product);
    }

    //删除商品
    public void delete(String productNum){
        Product product = productDao.findByNumber(productNum);
        productDao.delete(product);
        List<Trade> trades = TradeDao.findByPnum(productNum);
        for(Trade trade:trades){
            TradeDao.delete(trade);
        }
    }

    //按照商品编号查询商品
    public Product findByNum(String num){
        return productDao.findByNumber(num);
    }

    //修改商品
    public void editProduct(String productNum,String name,String category,String description,Integer price,String[] images){
        productDao.update(productNum,name,category,description,price,images);
    }


    //查询指定买家和商品编号的订单
    public Trade findUniqueTrade(String productNum,String buyer){
        return TradeDao.findUniqueTrade(buyer,productNum);
    }

    //分页查询商品
    public Page<Product> findAllPages(final int pageNo,final int pageSize,String order){
        Page<Product> page = new Page<Product>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(order.equals("new"))
            page.setList(productDao.findNew(page));
        else if(order.equals("hot"))
            page.setList(productDao.findHot(page));
        page.setTotalRecords(productDao.countGood());
        return page;
    }

    public Page<Product> findAllPagesByCategory(final int pageNo,final int pageSize,String order,String category){
        Page<Product> page = new Page<Product>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        if(order.equals("new"))
            page.setList(productDao.findNewByCategory(page,category));
        else if(order.equals("hot"))
            page.setList(productDao.findHotByCategory(page,category));
        page.setTotalRecords(productDao.countGoodByCategory(category));
        return page;
    }

    public Page<Product> findAllPagesByName(final int pageNo,final int pageSize,String name){
        Page<Product> page = new Page<Product>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setList(productDao.findByName(page,name));
        page.setTotalRecords(productDao.countGoodByName(name));
        return page;
    }


    //查询等待卖出的商品
    public List<Product> sellProduct(String seller){
        return productDao.findProductsToSell(seller);
    }

    //查询已经卖出的商品
    public List<Product> soldProduct(String seller){
        return productDao.findProductsSold(seller);
    }

    //查询已经买到的商品
    public List<Product> boughtProduct(String buyer){
        return productDao.findProductsBought(buyer);
    }

    //查询我想买的商品
    public List<Product> buyProduct(String buyer){
        List<Product> products = new ArrayList<Product>();
        List<Trade> Trades = TradeDao.findMyTrades(buyer);
        Product product;
        for(Trade o:Trades){
            product = productDao.findByNumber(o.getProductNum());
            products.add(product);
        }
        return products;
    }

    //查询我下的订单
    public List<Trade> getMyTrades(String buyer){
        return TradeDao.findMyTrades(buyer);
    }

    //买家下订单
    public boolean placeTrade(String buyer,String productNum){
        if(TradeDao.findUniqueTrade(buyer,productNum)==null) {
            Trade Trade = new Trade();
            Product product = productDao.findByNumber(productNum);
            String buyerphone = userDao.findByName(buyer).getPhone();
            String sellerphone = userDao.findByName(product.getSeller()).getPhone();
            Trade.setBuyer(buyer);
            Trade.setProductNum(productNum);
            Trade.setTime(new Date());
            Trade.setState("waiting");
            Trade.setSeller(product.getSeller());
            Trade.setBuyerphone(buyerphone);
            Trade.setSellerphone(sellerphone);
            addBrowser(productNum);
            TradeDao.save(Trade);
        }
        return true;
    }

    //买家取消订单
    public boolean cancelTrade(String buyer,String productNum){
        Trade trade = TradeDao.findUniqueTrade(buyer,productNum);
        if(trade!=null) {
            subBrowser(productNum);
            TradeDao.delete(trade);
            return true;
        }
        return false;
    }

    //查询指定商品的所有订单
    public List<Trade> findTradesByNum(String productNum){
        return TradeDao.findByPnum(productNum);
    }

    //卖家确认,订单成交
    public boolean confirmTrade(String buyer,String productNum){
        List<Trade> Trades = TradeDao.findByPnum(productNum);
        for(Trade o :Trades){
            if(o.getBuyer().equals(buyer)){
                o.setState("got");
                Product product = productDao.findByNumber(productNum);
                product.setBuyer(buyer);
                product.setDealTime(new Date());
            }else {
                o.setState("missed");
            }
        }
        return true;
    }

    //增加商品的热度
    private void addBrowser(String productNum){
        Product product = productDao.findByNumber(productNum);
        int count = product.getBrowserCount();
        if(count>=0){
            product.setBrowserCount(count+1);
        }
    }

    //减少商品的热度
    private void subBrowser(String productNum){
        Product product = productDao.findByNumber(productNum);
        int count = product.getBrowserCount();
        if(count>=1){
            product.setBrowserCount(count-1);
        }
    }

    //订单id查询订单
    public Trade findTradeById(Integer id){
        return TradeDao.get(id);
    }

    //订单id删除订单
    public void deleteTradeById(Integer id){
        TradeDao.delete(id);
    }
}
