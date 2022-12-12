package com.example.footwearshopstb.model;

public class PlaceOrderModel {
    private String productimage;
    private String productid;
    private String productsize;
    private String producttitle;
    private String productprice;
    private String productquantity;
    private String totalAmount;
    private String getID;

    public PlaceOrderModel(String productid, String productimage, String producttitle, String productprice, String productsize, String productquantity, String totalAmount, String getID) {
        this.productimage = productimage;
        this.productid = productid;
        this.productsize = productsize;
        this.producttitle = producttitle;
        this.productprice = productprice;
        this.productquantity = productquantity;
        this.totalAmount = totalAmount;
        this.getID = getID;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductsize() {
        return productsize;
    }

    public void setProductsize(String productsize) {
        this.productsize = productsize;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(String productquantity) {
        this.productquantity = productquantity;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getGetID() {
        return getID;
    }

    public void setGetID(String getID) {
        this.getID = getID;
    }
}