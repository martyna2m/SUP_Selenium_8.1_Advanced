package models;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BasketLine {
    private Product product;
    private int quantity;
    private BigDecimal totalPrice;

    public BasketLine(Product product, int quantity, BigDecimal totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;

    }

    @Override
    public String toString() {
        return "BasketLine{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public void increaseQuantityAndTotalPrice(int newQuantity, BigDecimal newTotalPrice) {
        this.quantity += newQuantity;
        this.totalPrice = this.totalPrice.add(newTotalPrice);
    }


}

