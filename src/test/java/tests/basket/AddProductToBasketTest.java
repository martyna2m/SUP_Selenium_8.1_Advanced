package tests.basket;

import models.BasketLine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.commons.TopGridPage;
import pages.product.AddedToBasketPopUpPage;
import pages.categories.CategoryPage;
import pages.product.ProductDetailsPage;
import tests.base.TestBase;

public class AddProductToBasketTest extends TestBase {

    @Test
    public void addProductToBasket() throws InterruptedException {
        String expectedProductName = testDataProvider.getTestData("posterName");
        int expectedQuantity = 3;

        openPage("artPage");

        int initialNumberOfItemsInBasket = at(TopGridPage.class).getNumberOfItemsInBasket();

        at(CategoryPage.class)
                .getProductMiniatureContainerPage()
                .selectProductByName(expectedProductName);

        BasketLine expectedBasketLine =
                at(ProductDetailsPage.class)
                        .selectQuantity(expectedQuantity)
                        .addProductToBasket();

        Thread.sleep(1000);
        at(AddedToBasketPopUpPage.class).waitForPopUp();

        BasketLine popupBasketLine =
                at(AddedToBasketPopUpPage.class).toBasketLine();


        Assertions.assertThat(popupBasketLine)
                .usingRecursiveComparison()
                .isEqualTo(expectedBasketLine);

        at(AddedToBasketPopUpPage.class).clickContinueShopping();

        int currentNumberOfItemsInBasket = at(TopGridPage.class).getNumberOfItemsInBasket();
        Assertions.assertThat(currentNumberOfItemsInBasket>initialNumberOfItemsInBasket).isTrue();
    }
}
