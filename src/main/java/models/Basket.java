package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Basket {

    private List<BasketLine> basketLines = new ArrayList<>();

    public void addBasketLineToBasket(BasketLine basketLine) {
       basketLines.add(basketLine);
    }


}
