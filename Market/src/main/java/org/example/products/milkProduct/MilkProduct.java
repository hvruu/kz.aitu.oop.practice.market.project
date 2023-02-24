package org.example.products.milkProduct;

import jakarta.persistence.MappedSuperclass;
import org.example.products.Products;
@MappedSuperclass
abstract public class MilkProduct extends Products {
    String manufacturer;
    double percOfFat;
    double lactoseContent;
    double calciumContent;
    public MilkProduct(double price, int bestBeforeDate, double calciumContent, double percOfFat,double lactoseContent, int nutritionalValue, String manufacturer, int mass, String name) {
        super(price, bestBeforeDate, nutritionalValue, mass, name);
        this.nutritionalValue = nutritionalValue;
        this.manufacturer = manufacturer;
        this.lactoseContent = lactoseContent;
        this.percOfFat = percOfFat;
        this.calciumContent = calciumContent;
    }

    public MilkProduct(double price, int bestBeforeDate, int nutritionalValue, int mass, String name) {
        super(price, bestBeforeDate, nutritionalValue, mass, name);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPercOfFat(){
        return percOfFat;
    }

    public void setPercOfFat(double percOfFat){
        this.percOfFat = percOfFat;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public MilkProduct() {
    }
}
