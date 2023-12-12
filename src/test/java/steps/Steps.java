package steps;

import pages.categories.CategoryPage;
import pages.commons.TopMenuPage;
import pages.home.HomePage;
import pages.product.AddedToBasketPopUpPage;
import pages.product.ProductDetailsPage;
import tests.base.TestBase;

import java.util.Random;

public class Steps extends TestBase {

    public void addRandomProductToBasketAndReturnToHomePage(int quantity)  {
        int randomIndex = new Random().nextInt(at(HomePage.class).getProductMiniatureContainerPage().getProductMiniatures().size());

        at(HomePage.class)
                .getProductMiniatureContainerPage()
                .selectProductByIndex(randomIndex);

        at(ProductDetailsPage.class)
                .selectQuantity(quantity)
                .addProductToBasket();

        at(AddedToBasketPopUpPage.class)
                .clickContinueShopping();

        at(ProductDetailsPage.class).returnToHomePage();

    }

    public void chooseCategoryAndProduct(String categoryName, String productName) {
        at(TopMenuPage.class)
                .goToCategoryPageWithName(categoryName);

        at(CategoryPage.class)
                .getProductMiniatureContainerPage()
                .selectProductByName(productName);
    }
}
