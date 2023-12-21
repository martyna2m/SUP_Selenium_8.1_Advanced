package tests.basket;

import helpers.RandomHelper;
import lombok.extern.slf4j.Slf4j;
import models.Basket;
import models.BasketLine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import pages.basket.BasketPage;
import pages.basket.BasketSideGridPage;
import steps.prodcutSteps.AddProductsSteps;
import tests.base.TestBase;

import java.util.List;

@Slf4j
public class GenericBasketTest extends TestBase {
    @RepeatedTest(1)
    @Tag("basket")
    public void addRandomProductsToBasket() {
        AddProductsSteps addProductsSteps = new AddProductsSteps(driver);
        Basket basket = new Basket();

        openPage("homePage");
        int numberOfRepetitions = parseInt(testDataProvider.getTestData("numberOfRepetitions"));

        for (int i = 0; i < numberOfRepetitions; i++) {
            int randomExpectedQuantity = RandomHelper.getRandomNumber(5) + 1;
            addProductsSteps.addRandomProductToBasketAndReturnToHomePage(basket, randomExpectedQuantity);
        }

        openPage("basketPage");

        List<BasketLine> actualBasketLines = at(BasketPage.class).getBasketLinesInBasket();
        List<BasketLine> expectedBasketLines = basket.getExpectedBasketLines();

        Assertions.assertThat(expectedBasketLines).usingRecursiveComparison().isEqualTo(actualBasketLines);
        Assertions.assertThat(basket.getTotalSumOfBasketLines()).isEqualTo(at(BasketSideGridPage.class).getProductsTotalSum());


    }
}


