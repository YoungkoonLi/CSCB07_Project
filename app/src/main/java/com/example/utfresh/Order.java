package com.example.utfresh;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {
    //this class is for the getter method for the orders_activity


    private String Order_Status;
    private String Customer_Name;
    private String Store_Name;
    private ArrayList<OrderData> Item_List; //list of item that customer orders,
    //include product name, price, quantity, category


    public Order(){

    }

    public Order(String Customer_Name, String Store_Name, ArrayList<OrderData> Item_List){
        // the default Order_Status is Pending
        this.Order_Status = "Pending";
        this.Customer_Name = Customer_Name;
        this.Store_Name = Store_Name;
        this.Item_List = Item_List;
    }

    public void SetOrderStatus(String Order_Status) {
        // an Order_Status can only be Pending, Ready For Pick Up, or Complete
        String pending = "Pending";
        String ready = "Ready For Pick Up";
        String complete = "Complete";

        if (! Order_Status.equals(pending) && ! Order_Status.equals(ready) &&
                ! Order_Status.equals(complete)) {
            // here, an error message should pop up to the user
            return;
        }
        this.Order_Status = Order_Status;
    }

    public String getOrder_Status() {
        return Order_Status;
    }

    public void SetCustomer_Name(String Customer_Name) { this.Customer_Name = Customer_Name; }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void SetStore_Name(String Store_Name) { this.Store_Name = Store_Name; }

    public String getStore_Name() {
        return Store_Name;
    }

    public void SetItem_List(ArrayList<OrderData> Item_List) {this.Item_List = Item_List; }

    public ArrayList<OrderData> getItem_List() {
        return Item_List;
    }
}
