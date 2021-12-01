package com.example.utfresh;

public class OrderInfo {
    // this is a general pattern for an order
    // this contains information of the order
    private String Product_Name;
    private String Order_Status;
    private String Customer_Name;
    private String Store_Name;
    private String Category;
    private String Price;
    private String Quantity;

    public OrderInfo() {

    }

    public OrderInfo(String Product_Name, String Customer_Name, String Store_Name,
                 String Category, String Price, String Quantity) {
        // Construct an Order and the default Order_Status is Pending
        this.Product_Name = Product_Name;
        this.Order_Status = "Pending";
        this.Customer_Name = Customer_Name;
        this.Store_Name = Store_Name;
        this.Category = Category;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    public void SetStoreName(String Store_Name) {        this.Store_Name = Store_Name;    }

    public String GetStoreName() {        return Store_Name;    }

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

    public String GetOrderStatus() { return Order_Status; }

    public void SetProductName(String Product_Name) { this.Product_Name = Product_Name; }

    public String GetProductName() { return Product_Name; }

    public void SetCustomerName(String Customer_Name) { this.Customer_Name = Customer_Name; }

    public String GetCustomerName() { return Customer_Name; }

    public void SetCategory(String Category) {this.Category = Category; }

    public String GetCategory() {return Category; }

    public void SetQuantity(String Quantity) {this.Quantity = Quantity; }

    public String GetQuantity() {return Quantity; }

    public void SetPrice(String Price) {this.Price = Price; }

    public String GetPrice() {return Price; }


}
