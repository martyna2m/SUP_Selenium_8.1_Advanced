package tests.basket;

import models.Basket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.basket.BasketLinePage;
import pages.basket.BasketPage;
import pages.basket.BasketSideGridPage;
import pages.commons.TopGridPage;
import steps.Steps;

import java.math.BigDecimal;
import java.util.List;

public class RemoveProductsTest extends Steps {

    @Test
    public void removeAddedProductsFromBasket() throws InterruptedException {
        int expectedQuantity = Integer.parseInt(testDataProvider.getTestData("quantity"));
        int expectedNumberOfProducts = 2;
                //Integer.parseInt(testDataProvider.getTestData("numberOfProducts"));

        openPage("homePage");
        for (int i = 0; i < expectedNumberOfProducts; i++) {
            addRandomProductToBasketAndReturnToHomePage(expectedQuantity);
        }
        openPage("basketPage");
        Assertions.assertThat(at(BasketPage.class).checkIfTotalPriceIsCorrect()).isTrue();


        for (int j = 0; j < expectedNumberOfProducts; j++) {
            List<BasketLinePage> basketLinePages = at(BasketPage.class).getBasketLinePages();

            if (!basketLinePages.isEmpty()) {
                basketLinePages.get(0).deleteBasketLine();
                Thread.sleep(1000);
                Assertions.assertThat(at(BasketPage.class).checkIfTotalPriceIsCorrect()).isTrue();

            }

        }
        Assertions.assertThat(at(TopGridPage.class).getNumberOfItemsInBasket()).isEqualTo(0);
    }

}
