package org.example.products.milkProduct;

import jakarta.persistence.Entity;
import org.example.products.milkProduct.MilkProduct;

@Entity
public class SourCream extends MilkProduct {

    public SourCream(  String name, double price, int mass, double percOfFat, int bestBeforeDate, double calciumContent, double lactoseContent, int nutritionalValue, String manufacturer) {
        super(price, bestBeforeDate, calciumContent, percOfFat, lactoseContent, nutritionalValue, manufacturer, mass, name);
    }

    public SourCream() {
    }

    public SourCream(double price, int bestBeforeDate, double calciumContent, double percOfFat, double lactoseContent, int nutritionalValue, String manufacturer, int mass, String name) {
        super(price, bestBeforeDate, calciumContent, percOfFat, lactoseContent, nutritionalValue, manufacturer, mass, name);
    }

    @Override
    public void print() {

    }
}
