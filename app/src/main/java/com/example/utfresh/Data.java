package com.example.utfresh;

public class Data {
    private String product_name;
    private String price;
    private String category;
    public Data(){}
    public Data(String product_name, String price, String category){
        this.product_name = product_name;
        this.price = price;
        this.category = category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
