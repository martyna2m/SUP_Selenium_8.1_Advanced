package tests.basket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import pages.basket.BasketLinePage;
import pages.basket.BasketPage;
import pages.commons.TopGridPage;
import steps.Steps;

import java.util.List;

public class RemoveProductsTest extends Steps {

    @RepeatedTest(3)
    @Tag("yaml3")
    public void removeAddedProductsFromBasket() throws InterruptedException {
        int expectedQuantity = Integer.parseInt(testDataProvider.getTestData("quantity3"));
        int expectedNumberOfProducts = Integer.parseInt(testDataProvider.getTestData("numberOfProducts3"));

        openPage("homePage");
        for (int i = 0; i < expectedNumberOfProducts; i++) {
            addRandomProductToBasketAndReturnToHomePage(expectedQuantity);
        }
        openPage("basketPage");
        Assertions.assertThat(at(BasketPage.class).IsTotalPriceCorrect()).isTrue();


        for (int j = 0; j < expectedNumberOfProducts; j++) {
            List<BasketLinePage> basketLinePages = at(BasketPage.class).getBasketLinePages();

            if (!basketLinePages.isEmpty()) {
                basketLinePages.get(0).deleteBasketLine();
                Thread.sleep(1000);
                Assertions.assertThat(at(BasketPage.class).IsTotalPriceCorrect()).isTrue();

            }

        }
        Assertions.assertThat(at(TopGridPage.class).getNumberOfItemsInBasket()).isEqualTo(0);
    }

}
