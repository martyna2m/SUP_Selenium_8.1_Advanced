package models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    private String name;
    private String attribute;
    private BigDecimal price;
    private String size;

    public Product(String name, String attribute, BigDecimal price, String size) {
        this.name = name;
        this.attribute = attribute;
        this.price = price;
        this.size = size;
    }

    public Product(String name, String attribute, BigDecimal price) {
        this.name = name;
        this.attribute = attribute;
        this.price = price;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
