package com.example.nicapp.Models;

import java.util.Collection;

public class DataBaseMobelSQLite {

    String id;
    String  name;
    String  totalAmount;
    String reaminngAmount;
    String saleAmount;


    public DataBaseMobelSQLite() {

    }

    public DataBaseMobelSQLite(String id, String name, String totalAmount, String reaminngAmount, String saleAmount) {
        this.id = id;
        this.name = name;
        this.totalAmount = totalAmount;
        this.reaminngAmount = reaminngAmount;
        this.saleAmount = saleAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getReaminngAmount() {
        return reaminngAmount;
    }

    public void setReaminngAmount(String reaminngAmount) {
        this.reaminngAmount = reaminngAmount;
    }

    public String getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(String saleAmount) {
        this.saleAmount = saleAmount;
    }
}
