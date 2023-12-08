package steps;

import pages.home.HomePage;
import pages.product.AddedToBasketPopUpPage;
import pages.product.ProductDetailsPage;
import tests.base.TestBase;

import java.util.Random;

public class Steps extends TestBase {

    public void addRandomProductToBasketAndReturnToHomePage(int quantity) throws InterruptedException {
        int randomIndex = new Random().nextInt(at(HomePage.class).getProductMiniatureContainerPage().getProductMiniatures().size());

        at(HomePage.class)
                .getProductMiniatureContainerPage()
                .selectProductByIndex(randomIndex);

        at(ProductDetailsPage.class)
                .selectQuantity(quantity)
                .addProductToBasket();

        at(AddedToBasketPopUpPage.class)
                .waitForPopUp()
                .clickContinueShopping();

        at(ProductDetailsPage.class).returnToHomePage();

    }

}
