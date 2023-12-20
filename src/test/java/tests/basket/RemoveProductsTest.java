package tests.basket;

import helpers.PriceHelper;
import models.Basket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import pages.basket.BasketLinePage;
import pages.basket.BasketPage;
import pages.basket.BasketSideGridPage;
import pages.commons.TopGridPage;
import steps.Steps;

import java.util.List;

public class RemoveProductsTest extends Steps {

    @RepeatedTest(1)
    @Tag("basket")
    public void removeAddedProductsFromBasket()  {
        Basket basket = new Basket();
        BasketPage basketPage = new BasketPage(driver);
        BasketSideGridPage basketSideGridPage = new BasketSideGridPage(driver);

        int expectedQuantity = parseInt(testDataProvider.getTestData("quantity"));
        int expectedNumberOfProducts = parseInt(testDataProvider.getTestData("numberOfProducts"));

        openPage("homePage");
        for (int i = 0; i < expectedNumberOfProducts; i++) {
            addRandomProductToBasketAndReturnToHomePage(basket, expectedQuantity);
        }
        openPage("basketPage");
        Assertions.assertThat(basket.getTotalSumOfBasketLines()).isEqualTo(basketSideGridPage.getProductsTotalSum());


        for (int j = 0; j < expectedNumberOfProducts; j++) {
            List<BasketLinePage> basketLinePages = basketPage.getBasketLinePages();

            if (!basketLinePages.isEmpty()) {
                at(BasketPage.class).deleteBasketLine(basket, basketLinePages.get(0));
                Assertions.assertThat(basket.getTotalSumOfBasketLines()).isEqualTo(basketSideGridPage.getProductsTotalSum());
            }

        }
        Assertions.assertThat(at(TopGridPage.class).getNumberOfItemsInBasket()).isEqualTo(0);

    }

}
