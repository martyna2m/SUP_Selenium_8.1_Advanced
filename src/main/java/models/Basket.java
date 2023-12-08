package models;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private static Basket instance;
    private List<BasketLine> basketLines;

    private Basket() {
        basketLines = new ArrayList<>();
    }

    public static Basket getInstance() {
        if (instance == null) {
            instance = new Basket();
        }
        return instance;
    }

    public List<BasketLine> getExpectedBasketLines() {
        return basketLines;
    }

    public void addBasketLineToBasket(BasketLine basketLine) {
        basketLines.add(basketLine);
    }

    public BasketLine getBasketLineFromExpectedBasket(String name) {
        for (BasketLine basketLine : getExpectedBasketLines()) {
            if ((basketLine.getProduct().getName()).equals(name)) {
                return basketLine;
            }
        }
        return null;
    }

    public void removeBasketLine(String name) {
        getExpectedBasketLines().remove(getBasketLineFromExpectedBasket(name));
    }

}