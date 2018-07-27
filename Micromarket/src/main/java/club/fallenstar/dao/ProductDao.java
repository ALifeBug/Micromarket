package club.fallenstar.dao;

import club.fallenstar.entity.Page;
import club.fallenstar.entity.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product>{

    /**
     *
     * @return 以热度(浏览次数)排序列出所有的商品
     */
     List<Product> findHot(Page<Product> page);

    /**
     *
     * @return 以时间排序列出所有的商品
     */
     List<Product> findNew(Page<Product> page);


    /**
     *
     * @param page
     * @param category
     * @return 根据类别查询最热的商品
     */
    List<Product> findHotByCategory(Page<Product> page,String category);

    /**
     *
     * @param page
     * @param category
     * @return 根据类别查询最新的商品
     */
     List<Product> findNewByCategory(Page<Product> page,String category);

    /**
     *
     * @return 关键词查询
     */
     List<Product> findByName(Page<Product> page,String name);

    /**
     *
     * @return 编号查询
     */
     Product findByNumber(String number);


    /**
     *
     * @param seller
     * @param buyer
     * @return 买家和卖家联合查询,已经卖出的指定商品
     */
     List<Product> findByUser(String seller, String buyer);

    /**
     *
     * @param seller
     * @return 买家为空,等待卖出的商品
     */
     List<Product> findProductsToSell(String seller);

    /**
     *
     * @param seller
     * @return 买家不为空,所有已经卖出的商品
     */
     List<Product> findProductsSold(String seller);


    /**
     *
     * @param buyer
     * @return 指定买家的已经买到的商品
     */
     List<Product> findProductsBought(String buyer);

    /**
     *
     * @param category
     * @return 种类查询
     */
     List<Product> findByCategory(Integer category);

    /**
     *
     * @return 商品总数
     */
     Integer countGood();

    /**
     *
     * @return 根据商品名称查询商品总数
     */
    Integer countGoodByName(String name);

    /**
     *
     * @return 根据商品类型查询商品总数
     */
    Integer countGoodByCategory(String category);

    /**
     *
     * @param minPrice
     * @param maxPrice
     * @return 价格区间查询
     */
     List<Product> findByPrice(double minPrice, double maxPrice);

    /**
     *
     * @param productNum
     * @param name
     * @param category
     * @param description
     * @param price
     * @param images
     */
     void update(String productNum,String name,String category,String description,Integer price,String[] images);
}
