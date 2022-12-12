package com.example.footwearshopstb.model;

import java.io.Serializable;
import java.util.Objects;

public class HorizontalProductScrollModel implements Serializable {

    private String productimage;
    private String product_ID;
    private String producttitle, productcategory, productdescription;
    private Object productprice;

    public HorizontalProductScrollModel(String product_ID, String productimage, String producttitle, String productcategory, Object productprice, String productdescription) {
        this.productimage = productimage;
        this.producttitle = producttitle;
        this.productcategory = productcategory;
        this.productprice = productprice;
        this.product_ID = product_ID;
        this.productdescription = productdescription;
    }


    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
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

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public Object getProductprice() {
        return productprice;
    }

    public void setProductprice(Object productprice) {
        this.productprice = productprice;
    }
}
