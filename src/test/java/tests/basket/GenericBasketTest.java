package tests.basket;

import lombok.extern.slf4j.Slf4j;
import models.Basket;
import models.BasketLine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import pages.basket.BasketPage;
import pages.basket.BasketSideGridPage;
import steps.Steps;

import java.util.List;
import java.util.Random;

@Slf4j
public class GenericBasketTest extends Steps {
    @RepeatedTest(1)
    @Tag("basket")
    public void addRandomProductsToBasket() {
        Basket basket = new Basket();

        openPage("homePage");
        int numberOfRepetitions = parseInt(testDataProvider.getTestData("numberOfRepetitions"));

        for (int i = 0; i < numberOfRepetitions; i++) {
            int randomExpectedQuantity = (new Random().nextInt(5)) + 1;
            addRandomProductToBasketAndReturnToHomePage(basket, randomExpectedQuantity);
        }

        openPage("basketPage");

        List<BasketLine> actualBasketLines = at(BasketPage.class).getBasketLinesInBasket();
        List<BasketLine> expectedBasketLines = basket.getExpectedBasketLines();

        Assertions.assertThat(expectedBasketLines).usingRecursiveComparison().isEqualTo(actualBasketLines);
        Assertions.assertThat(at(BasketPage.class).getTotalSumOfBasketLines()).isEqualTo(at(BasketSideGridPage.class).getProductsTotalSum());


    }
}


