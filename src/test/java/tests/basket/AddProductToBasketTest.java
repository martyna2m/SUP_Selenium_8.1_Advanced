package tests.basket;

import models.BasketLine;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import pages.categories.CategoryPage;
import pages.commons.TopGridPage;
import pages.product.AddedToBasketPopUpPage;
import pages.product.ProductDetailsPage;
import tests.base.TestBase;

public class AddProductToBasketTest extends TestBase {

    @RepeatedTest(3)
    @Tag("yaml2")
    public void addProductToBasket() {
        String expectedProductName = testDataProvider.getTestData("productName2");
        int expectedQuantity = parseInt(testDataProvider.getTestData("quantity2"));

        openPage("artPage");

        int initialNumberOfItemsInBasket = at(TopGridPage.class).getNumberOfItemsInBasket();

        at(CategoryPage.class)
                .getProductMiniatureContainerPage()
                .selectProductByName(expectedProductName);

        BasketLine expectedBasketLine =
                at(ProductDetailsPage.class)
                        .selectQuantity(expectedQuantity)
                        .addProductToBasket();

        BasketLine popupBasketLine =
                at(AddedToBasketPopUpPage.class).toBasketLine();


        Assertions.assertThat(popupBasketLine)
                .usingRecursiveComparison()
                .isEqualTo(expectedBasketLine);

        at(AddedToBasketPopUpPage.class).clickContinueShopping();

        int currentNumberOfItemsInBasket = at(TopGridPage.class).getNumberOfItemsInBasket();
        Assertions.assertThat(currentNumberOfItemsInBasket > initialNumberOfItemsInBasket).isTrue();
    }
}
