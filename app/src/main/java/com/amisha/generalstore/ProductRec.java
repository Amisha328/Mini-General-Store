package com.amisha.generalstore;

public class ProductRec {
    int id;
    int quantity;
    int price;
    String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }






    public ProductRec(int id, String name, int price,int quantity) {

        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }
    public ProductRec(Integer o, Object name, Object o1, Object o2){

    }



}
