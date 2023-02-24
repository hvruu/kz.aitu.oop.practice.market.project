package org.example.products.vegetables;

import jakarta.persistence.MappedSuperclass;
import org.example.products.Products;
@MappedSuperclass
abstract public class Vegetables extends Products {
    boolean Greenhouse;
    boolean purified;
    String type;

    public Vegetables(String name, double price, int bestBeforeDate, int nutritionalValue, int mass, boolean purified, boolean Greenhouse, String type) {
        super(price, bestBeforeDate, nutritionalValue, mass, name);
        this.Greenhouse = Greenhouse;
        this.purified = purified;
        this.type = type;
    }

    public Vegetables(double price, int bestBeforeDate, int nutritionalValue, int mass, String name) {
        super(price, bestBeforeDate, nutritionalValue, mass, name);
    }

    public String getType() {
        return type;
    }

    public boolean getisGreenhouse(){
        return Greenhouse;
    }

    public boolean getisPurified(){
        return purified;
    }

    public void setPurified(boolean purified){
        this.purified = purified;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setisGreenhouee(boolean Greenhouse) {
        this.Greenhouse = Greenhouse;
    }

    public Vegetables() {
    }
}
