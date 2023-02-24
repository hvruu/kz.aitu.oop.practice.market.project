package org.example.products.vegetables;

import jakarta.persistence.Entity;

@Entity
public class potato extends Vegetables {
    public potato(String name,double price, int mass, int nutritionalValue, int bestBeforeDate, boolean isGreenhouse, boolean purified, String type) {
        super(name, price, mass, nutritionalValue, bestBeforeDate, isGreenhouse,purified, type);
    }

    public potato() {
    }


    @Override
    public void print() {
        System.out.println(getId() + "\t"+ getName() + "\t" + getPrice() + "\t" + getMass() + "\t\t\t" + getType() + "\t\t" + getBestBeforeDate() + "\t\t\t\t\t" + getisGreenhouse() + "\t\t\t\t\t" + getisPurified() + "\t");
    }
}
