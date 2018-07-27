package club.fallenstar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Trade {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String productNum; //商品编号

    @Column(nullable = false)
    private String seller;//卖家

    @Column(nullable = false)
    private String buyer;//买家,下订单,但得等卖家回应

    @Column(nullable = false)
    private String sellerphone;//卖家电话

    @Column(nullable = false)
    private String buyerphone;//买家电话

    @Column(nullable = false)
    private String state;//状态,包括等待中,抢到,没抢到

    @Column(nullable = false)
    private Date time;//下单时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSellerphone() {
        return sellerphone;
    }

    public void setSellerphone(String sellerphone) {
        this.sellerphone = sellerphone;
    }

    public String getBuyerphone() {
        return buyerphone;
    }

    public void setBuyerphone(String buyerphone) {
        this.buyerphone = buyerphone;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", productNum='" + productNum + '\'' +
                ", seller='" + seller + '\'' +
                ", buyer='" + buyer + '\'' +
                ", sellerphone='" + sellerphone + '\'' +
                ", buyerphone='" + buyerphone + '\'' +
                ", state='" + state + '\'' +
                ", time=" + time +
                '}';
    }
}
