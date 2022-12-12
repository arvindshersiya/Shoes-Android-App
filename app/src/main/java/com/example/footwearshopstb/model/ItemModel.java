package com.example.footwearshopstb.model;

public class ItemModel {

    Integer id;
    String image, name, price, category, text;

    public ItemModel(Integer id, String image, String name, String price, String category, String text) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.category = category;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
