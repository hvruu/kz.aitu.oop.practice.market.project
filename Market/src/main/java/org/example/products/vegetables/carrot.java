package org.example.products.vegetables;

import jakarta.persistence.Entity;

@Entity
public class carrot extends Vegetables {
    public carrot(String name,double price, int mass, int nutritionalValue, int bestBeforeDate, boolean isGreenhouse, boolean purified, String type) {
        super(name, price, mass, nutritionalValue, bestBeforeDate, isGreenhouse,purified, type);
    }

    public carrot() {
    }

    @Override
    public void print() {
        System.out.println(getId() + "\t" + getPrice() + "\t" + getType() + "\t" + getisGreenhouse() + "\t" + getisPurified());
    }
}
