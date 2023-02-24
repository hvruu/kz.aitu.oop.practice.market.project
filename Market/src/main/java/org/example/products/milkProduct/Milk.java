package org.example.products.milkProduct;

import jakarta.persistence.Entity;

@Entity
public class Milk extends MilkProduct {
    String mpackage;


    public Milk(String name, double price, int mass,  int bestBeforeDate, double percOfFat, String mpackage, double calciumContent, double lactoseContent, int nutritionalValue, String manufacturer) {
        super(price, bestBeforeDate, calciumContent, percOfFat, lactoseContent, nutritionalValue, manufacturer, mass, name);
        this.mpackage = mpackage;
    }

    public Milk() {
    }

    public String getMpackage(){
        return mpackage;
    }

    public void setMpackage(String mpackage){
        this.mpackage = mpackage;
    }


    @Override
    public void print() {

    }
}


