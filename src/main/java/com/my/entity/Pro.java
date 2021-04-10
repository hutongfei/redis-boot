package com.my.entity;

/**
 * @author hutf
 * @createTime 2021年04月10日 00:09:00
 */
public class Pro {
    private String name;
    private String price;
    private String shopName;
    private String title;
    private Integer id;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Pro{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", shopName='" + shopName + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
