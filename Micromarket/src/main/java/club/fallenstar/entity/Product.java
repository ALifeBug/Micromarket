package club.fallenstar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(name="ID",updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String name; //名称

    @Column(nullable = false,unique = true)
    private String number; //编号

    @Column(nullable = false)
    private String category; //种类

    @Column(nullable = false)
    private  int price; //价格

    private String description; //描述

    private String[] picture = new String[4]; //图片

    @Column(nullable = false)
    private String seller; //卖家

    @Column(nullable = false)
    private Date upTime; //上架时间

    private String buyer; //买家

    private Date dealTime; //成交时间

    private int browserCount; //浏览次数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPicture() {
        return picture;
    }

    public void setPicture(String[] picture) {
        this.picture = picture;
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

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBrowserCount() {
        return browserCount;
    }

    public void setBrowserCount(int browserCount) {
        this.browserCount = browserCount;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", picture='" + picture[0] + picture[1] + picture[2] + picture[3] + '\'' +
                ", seller='" + seller + '\'' +
                ", upTime=" + upTime +
                ", buyer='" + buyer + '\'' +
                ", dealTime=" + dealTime +
                ", browserCount=" + browserCount +
                '}';
    }
}
