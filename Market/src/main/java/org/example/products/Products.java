package org.example.products;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
abstract public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected double price;
    protected int bestBeforeDate;
    protected int nutritionalValue;
    protected int mass;
    String name;

    public Products(double price, int bestBeforeDate, int nutritionalValue,int mass, String name) {
        this.price = price;
        this.name = name;
        this.mass = mass;
        this.bestBeforeDate = bestBeforeDate;
        this.nutritionalValue = nutritionalValue;
    }


    public int getBestBeforeDate(){
        return bestBeforeDate;
    }

    public void setBestBeforeDate(int bestBeforeDate){
        this.bestBeforeDate = bestBeforeDate;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getMass(){
        return mass;
    }

    public void setMass(int mass){
        this.mass = mass;
    }

    public Products() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public abstract void print();
}
