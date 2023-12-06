package models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Basket {

    private List<BasketLine> basketLines;
}
