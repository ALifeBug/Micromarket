package club.fallenstar.entity;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {

    private List<Product> products = new LinkedList<Product>();

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(String productNum){
        for(Product product1:products){
            if(product1.getNumber().equals(productNum)){
                products.remove(product1);
                break;
            }
        }
    }

    public Integer totalMoney(){
        int money = 0;
        for(Product product:products){
            money+=product.getPrice();
        }
        return money;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean inCart(String productNum){
        boolean inCart = false;
        for(Product product1:products){
            if(product1.getNumber().equals(productNum)){
                inCart=true;
                break;
            }
        }
        return inCart;
    }
}
