package com.my.entity;

/**
 * @author hutf
 * @createTime 2021年04月08日 22:42:00
 */
public class Product {
    private String id;

    private String name;

    private Double price;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(String id, String name, double price,String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
