package com.example.utfresh;

import java.util.ArrayList;

public class Order {
    //this class is for the getter method for the orders_activity


    private String Order_Status;
    private String Customer_Name;
    private String Store_Name;
    private ArrayList<OrderData> item_list; //list of item that customer orders,
    //include product name, price, quantity, category


    public Order(){

    }

    public Order(String Customer_Name, String Store_Name){

        this.Order_Status = "Pending";
        this.Customer_Name = Customer_Name;
        this.Store_Name = Store_Name;
    }

    public String getOrder_Status() {
        return Order_Status;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public String getStore_Name() {
        return Store_Name;
    }

    public ArrayList<OrderData> getItem_list() {
        return item_list;
    }
}
