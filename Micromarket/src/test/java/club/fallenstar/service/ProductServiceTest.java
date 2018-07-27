package club.fallenstar.service;

import club.fallenstar.dao.ProductDao;
import club.fallenstar.entity.Page;
import club.fallenstar.entity.Product;
import club.fallenstar.entity.User;
import club.fallenstar.util.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mvc.xml"})
@WebAppConfiguration("src/main/resources")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void save() {
        Product product = new Product();
        product.setName("Linux系统");
        product.setPrice(200);
        product.setNumber(UUIDUtils.getUUID());
        product.setDescription("世界上最好的系统");
        product.setCategory("操作系统");
        product.setSeller("hqs2018");
        product.setUpTime(new Date());
        productService.save(product);
    }

    @Test
    public void sellProduct() {
        List<Product> products = productService.sellProduct("hqs2018");
        System.out.println(products);
    }

    @Test
    public void findAllPages(){
        Page<Product> page = productService.findAllPages(3,2,"new");
        System.out.println(page);
    }

    @Test
    public void deleteTradeById() {
        productService.deleteTradeById(6);
    }
}