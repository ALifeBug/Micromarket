package club.fallenstar.dao.impl;

import club.fallenstar.dao.ProductDao;
import club.fallenstar.entity.Page;
import club.fallenstar.entity.Product;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
    public List<Product> findHot(Page<Product> page) {
        String hql = "from Product g  where g.buyer is null order by g.browserCount desc";
        Query query = this.getCurrentSession().createQuery(hql);
        int start = page.getOffset();
        int max = page.getPageSize();
        return query.setFirstResult(start).setMaxResults(max).list();
    }


    public List<Product> findNew(Page<Product> page) {
        String hql = "from Product g where g.buyer is null order by g.upTime desc";
        Query query = this.getCurrentSession().createQuery(hql);
        int start = page.getOffset();
        int max = page.getPageSize();
        return query.setFirstResult(start).setMaxResults(max).list();
    }

    public List<Product> findHotByCategory(Page<Product> page, String category) {
        String hql = "from Product g  where g.buyer is null and g.category = ? order by g.browserCount desc";
        Query query = this.getCurrentSession().createQuery(hql);
        int start = page.getOffset();
        int max = page.getPageSize();
        return query.setParameter(0,category).setFirstResult(start).setMaxResults(max).list();
    }

    public List<Product> findNewByCategory(Page<Product> page, String category) {
        String hql = "from Product g  where g.buyer is null and g.category = ? order by g.upTime desc";
        Query query = this.getCurrentSession().createQuery(hql);
        int start = page.getOffset();
        int max = page.getPageSize();
        return query.setParameter(0,category).setFirstResult(start).setMaxResults(max).list();
    }

    public List<Product> findByName(Page<Product> page,String name) {
        String hql = "from Product g  where g.buyer is null and g.name like ? order by g.upTime desc";
        Query query = this.getCurrentSession().createQuery(hql);
        int start = page.getOffset();
        int max = page.getPageSize();
        return query.setParameter(0,"%"+name+"%").setFirstResult(start).setMaxResults(max).list();
    }

    public Product findByNumber(String number) {
        String hql = "from Product g where g.number = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,number);
        return (Product) query.uniqueResult();
    }


    public List<Product> findByUser(String seller, String buyer) {
        String hql = "from Product g where g.seller = ? and g.buyer = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,seller);
        query.setParameter(1,buyer);
        return query.list();
    }
    //将要卖出的商品
    public List<Product> findProductsToSell(String seller) {
        String hql = "from Product g where g.seller = ? and g.buyer is null order by g.upTime desc";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,seller);
        return query.list();
    }

    //已经卖出的商品
    public List<Product> findProductsSold(String seller) {
        String hql = "from Product g where g.seller = ? and g.buyer is not null order by g.dealTime desc";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,seller);
        return query.list();
    }

    //已经买到的商品
    public List<Product> findProductsBought(String buyer) {
        String hql = "from Product g where g.buyer = ? order by g.dealTime desc";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,buyer);
        return query.list();
    }

    public List<Product> findByCategory(Integer category) {
        String hql = "from Product g where g.category = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,category);
        return query.list();
    }

    public Integer countGood() {
        String hql = "Select count(*) from Product g where g.buyer is null";
        Query query = this.getCurrentSession().createQuery(hql);
        List cc = query.list();
        Long a = (Long) cc.get(0);
        return a.intValue();
    }

    public Integer countGoodByName(String name) {
        String hql = "Select count(*) from Product g where g.buyer is null and g.name like ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,"%"+name+"%");
        List cc = query.list();
        Long a = (Long) cc.get(0);
        return a.intValue();
    }

    public Integer countGoodByCategory(String category) {
        String hql = "Select count(*) from Product g where g.buyer is null and g.category = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,category);
        List cc = query.list();
        Long a = (Long) cc.get(0);
        return a.intValue();
    }

    public List<Product> findByPrice(double minPrice, double maxPrice) {
        String hql = "from Product g where g.price>="+minPrice+" and g.price<="+maxPrice;
        return find(hql);
    }

    public void update(String productNum, String name, String category, String description, Integer price, String[] images) {
        String hql = "update Product p set p.name = ?,p.category = ?,p.description = ?,p.price = ?,p.picture = ? where p.number = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,name).setParameter(1,category).setParameter(2,description).setParameter(3,price).setParameter(4,images).setParameter(5,productNum);
        query.executeUpdate();
    }
}
