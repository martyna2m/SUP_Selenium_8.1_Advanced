package tests.basket;

import lombok.extern.slf4j.Slf4j;
import models.Basket;
import models.BasketLine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.basket.BasketPage;
import steps.Steps;

import java.util.List;
import java.util.Random;
@Slf4j
public class GenericBasketTest extends Steps {
    @Test
    public void addRandomProductsToBasket() throws InterruptedException {
        openPage("homePage");

        for (int i = 0; i < 10; i++) {
            int randomExpectedQuantity = (new Random().nextInt(5)) + 1;
            addRandomProductToBasketAndReturnToHomePage(randomExpectedQuantity);
        }

        openPage("basketPage");

        List<BasketLine> actualBasketLines = at(BasketPage.class).getBasketLinesInBasket();
        List<BasketLine> expectedBasketLines = Basket.getInstance().getExpectedBasketLines();

        for (int i = 0; i < actualBasketLines.size(); i++) {
            Assertions.assertThat(expectedBasketLines.get(i)).usingRecursiveComparison().isEqualTo(actualBasketLines.get(i));
        }

        Assertions.assertThat(at(BasketPage.class).checkIfTotalPriceIsCorrect()).isTrue();
    }
}


