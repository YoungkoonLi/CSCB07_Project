package com.example.utfresh;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {
    //this class is for the getter method for the orders_activity


    private String order_Status;
    private String customer_Name;
    private String store_Name;
//    private ArrayList<OrderData> Item_List; //list of item that customer orders,
    //include product name, price, quantity, category


    public Order(){

    }

    public Order(String customer_Name, String store_Name){
        // the default Order_Status is Pending
        this.order_Status = "Pending";
        this.customer_Name = customer_Name;
        this.store_Name = store_Name;
//        this.Item_List = Item_List;
    }

    public void SetOrderStatus(String order_Status) {
        // an Order_Status can only be Pending, Ready For Pick Up, or Complete
        String pending = "Pending";
        String ready = "Ready For Pick Up";
        String complete = "Complete";

//        if (! order_Status.equals(pending) && ! order_Status.equals(ready) &&
//                ! order_Status.equals(complete)) {
//            // here, an error message should pop up to the user
//            return;
//        }
        this.order_Status = order_Status;
    }

    public String getOrder_Status() {
        return order_Status;
    }

    public void SetCustomer_Name(String customer_Name) { this.customer_Name = customer_Name; }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void SetStore_Name(String store_Name) { this.store_Name = store_Name; }

    public String getStore_Name() {
        return store_Name;
    }

//    public void SetItem_List(ArrayList<OrderData> Item_List) {this.Item_List = Item_List; }
//
//    public ArrayList<OrderData> getItem_List() {
//        return Item_List;
//    }
}
