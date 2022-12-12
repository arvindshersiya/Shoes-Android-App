package com.example.footwearshopstb.model;

import java.io.Serializable;
import java.util.Objects;

public class CartItemModel implements Serializable {

    public static final int CART_ITEM_ = 0;

    public static final int TOTAL_AMOUNT = 1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //cart item

    private String productimage;
    private String productid;
    private String productsize;
    private String producttitle;
    private String productprice;
    private String productquantity;
    private String totalAmount;
    private String getID;


    public CartItemModel(String productid, String productimage, String producttitle, String productprice, String productsize, String productquantity, String totalAmount, String getID) {

        //this.type = type;
        this.productimage = productimage;
        this.productid = productid;
        this.productsize = productsize;
        this.producttitle = producttitle;
        this.productprice = productprice;
        this.productquantity = productquantity;
        this.totalAmount = totalAmount;
        this.getID = getID;

    }

    public String getGetID() {
        return getID;
    }

    public void setGetID(String getID) {
        this.getID = getID;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProductsize() {
        return productsize;
    }

    public void setProductsize(String productsize) {
        this.productsize = productsize;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
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


    //cart item


    //cart amount


    private String totalitem;
    private String totalitemprice;
    private String totalamount;

    public CartItemModel(int type, String totalitem, String totalitemprice, String totalamount) {
        this.type = type;
        this.totalitem = totalitem;
        this.totalitemprice = totalitemprice;
        this.totalamount = totalamount;
    }


    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getTotalitem() {
        return totalitem;
    }

    public void setTotalitem(String totalitem) {
        this.totalitem = totalitem;
    }

    public String getTotalitemprice() {
        return totalitemprice;
    }

    public void setTotalitemprice(String totalitemprice) {
        this.totalitemprice = totalitemprice;
    }


    //cart amount


}
