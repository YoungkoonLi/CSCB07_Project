package com.example.utfresh;

import java.io.Serializable;

public class OrderData implements Serializable {

    //this class is for the getter method in the DetailOrder_activity (after the owner click the
    //order in recyclerview to see the specific information about an order.

    private String Product_Name;
    private String Price;
    private String Category;
    private String Quantity;

    public OrderData(){}
    public OrderData(String Product_Name, String Price, String Category, String Quantity){
        this.Product_Name = Product_Name;
        this.Price = Price;
        this.Category = Category;
        this.Quantity = Quantity;
    }

    public void SetProduct_Name(String Product_Name) {this.Product_Name = Product_Name; }

    public String getProduct_name() {
        return Product_Name;
    }

    public void SetPrice(String Price) {this.Price = Price; }

    public String getPrice() {
        return Price;
    }

    public void SetCategory(String Category) {this.Category = Category; }

    public String getCategory() {
        return Category;
    }

    public void SetQuantity(String Quantity) {this.Quantity = Quantity; }

    public String getQuantity() {
        return Quantity;
    }

}
