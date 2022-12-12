package com.example.footwearshopstb.model;

import java.io.Serializable;

public class MyOrderModel implements Serializable {
    private String productImage;
    private String productTitle;
    private String productqty;
    private String prductPrice;

    private String prductshoeId;
    private String prducttotalAmount;
    private String orderID;
    private String paymentMethod;
    private String paymentStatus;
    private String address;
    private String orderDateTime;
    private String deliverStatus;
    private String shoeSize;
    private String fullName;
    private String pinNo;

    private String packedDateTime;
    private String shippedDateTime;
    private String deliveredDateTime;
    private String cancelledDateTime;
    private String payment_status;

    public MyOrderModel(String productImage, String productTitle, String productqty, String prductPrice,
                        String prductshoeId, String prducttotalAmount, String orderID,
                        String paymentMethod, String paymentStatus,
                        String address, String orderDateTime,
                        String deliverStatus,
                        String shoeSize, String fullName,
                        String pinNo, String packedDateTime, String shippedDateTime, String deliveredDateTime,
                        String cancelledDateTime, String payment_status) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productqty = productqty;
        this.prductPrice = prductPrice;
        this.prductshoeId = prductshoeId;
        this.prducttotalAmount = prducttotalAmount;
        this.orderID = orderID;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.address = address;
        this.orderDateTime = orderDateTime;
        this.deliverStatus = deliverStatus;
        this.shoeSize = shoeSize;
        this.fullName = fullName;
        this.pinNo = pinNo;
        this.packedDateTime = packedDateTime;
        this.shippedDateTime = shippedDateTime;
        this.deliveredDateTime = deliveredDateTime;
        this.cancelledDateTime = cancelledDateTime;
        this.payment_status = payment_status;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getCancelledDateTime() {
        return cancelledDateTime;
    }

    public void setCancelledDateTime(String cancelledDateTime) {
        this.cancelledDateTime = cancelledDateTime;
    }

    public String getPackedDateTime() {
        return packedDateTime;
    }

    public void setPackedDateTime(String packedDateTime) {
        this.packedDateTime = packedDateTime;
    }

    public String getShippedDateTime() {
        return shippedDateTime;
    }

    public void setShippedDateTime(String shippedDateTime) {
        this.shippedDateTime = shippedDateTime;
    }

    public String getDeliveredDateTime() {
        return deliveredDateTime;
    }

    public void setDeliveredDateTime(String deliveredDateTime) {
        this.deliveredDateTime = deliveredDateTime;
    }

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrductshoeId() {
        return prductshoeId;
    }

    public void setPrductshoeId(String prductshoeId) {
        this.prductshoeId = prductshoeId;
    }

    public String getPrducttotalAmount() {
        return prducttotalAmount;
    }

    public void setPrducttotalAmount(String prducttotalAmount) {
        this.prducttotalAmount = prducttotalAmount;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(String deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public String getPrductPrice() {
        return prductPrice;
    }

    public void setPrductPrice(String prductPrice) {
        this.prductPrice = prductPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductqty() {
        return productqty;
    }

    public void setProductqty(String productqty) {
        this.productqty = productqty;
    }
}
