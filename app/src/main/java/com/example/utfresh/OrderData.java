package com.example.utfresh;

public class OrderData {

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

    public String getProduct_name() {
        return Product_Name;
    }

    public String getPrice() {
        return Price;
    }

    public String getCategory() {
        return Category;
    }

    public String getQuantity() {
        return Quantity;
    }
}
