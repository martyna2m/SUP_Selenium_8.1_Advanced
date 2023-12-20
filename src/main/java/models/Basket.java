package models;

import helpers.PriceHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketLine> basketLines;

    public Basket() {
        basketLines = new ArrayList<>();
    }


    public List<BasketLine> getExpectedBasketLines() {
        return basketLines;
    }

    public BasketLine addBasketLineToBasket(String productName, BigDecimal productPrice, int quantity) {
        if (isProductInBasket(productName)) {
            BasketLine exisitingBasketLine = getBasketLineFromBasket(productName);
            exisitingBasketLine.increaseQuantityAndTotalPrice(quantity, PriceHelper.getTotalPriceByQuantity(productPrice, quantity));
            return exisitingBasketLine;
        } else {
            BasketLine expectedBasketLine = new BasketLine(new Product(productName, productPrice), quantity, PriceHelper.getTotalPriceByQuantity(productPrice, quantity));
            basketLines.add(expectedBasketLine);
            return expectedBasketLine;

        }
    }


    public boolean isProductInBasket(String productName) {
        return getBasketLineFromBasket(productName) != null;
    }


    public BasketLine getBasketLineFromBasket(String name) {
        for (BasketLine basketLine : basketLines) {
            if ((basketLine.getProduct().getName()).equals(name)) {
                return basketLine;
            }
        }
        return null;
    }

    public void removeBasketLine(String name) {
        getExpectedBasketLines().remove(getBasketLineFromBasket(name));
    }

//    public void clearBasket(){
//        getExpectedBasketLines().clear();
//    }

}