package com.example.nicapp.Models;

public class FamilyMemberModel {
    String name;
    String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public FamilyMemberModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FamilyMemberModel(String name) {
        this.name = name;
    }
}
