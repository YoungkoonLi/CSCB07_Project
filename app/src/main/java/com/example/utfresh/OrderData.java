package com.example.utfresh;

import java.io.Serializable;

public class OrderData implements Serializable {

    //this class is for the getter method in the DetailOrder_activity (after the owner click the
    //order in recyclerview to see the specific information about an order.

    private String product_name;
    private String price;
    private String category;
    private String quantity;

    public OrderData(){}
    public OrderData(String product_name, String price, String category, String quantity){
        this.product_name = product_name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public void SetProduct_Name(String product_name) {this.product_name = product_name; }

    public String getProduct_name() {
        return product_name;
    }

    public void SetPrice(String price) {this.price = price; }

    public String getPrice() {
        return price;
    }

    public void SetCategory(String category) {this.category = category; }

    public String getCategory() {
        return category;
    }

    public void SetQuantity(String quantity) {this.quantity = quantity; }

    public String getQuantity() {
        return quantity;
    }

}
