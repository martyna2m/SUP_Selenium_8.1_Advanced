package steps.prodcutSteps;

import helpers.RandomHelper;
import models.Basket;
import org.openqa.selenium.WebDriver;
import pages.basket.BasketSideGridPage;
import pages.categories.CategoryPage;
import pages.commons.TopMenuPage;
import pages.home.HomePage;
import pages.product.AddedToBasketPopUpPage;
import pages.product.ProductDetailsPage;

public class AddProductsSteps {

    WebDriver driver;
    HomePage homePage;
    ProductDetailsPage productDetailsPage;
    AddedToBasketPopUpPage addedToBasketPopUpPage;
    TopMenuPage topMenuPage;
    CategoryPage categoryPage;
    BasketSideGridPage basketSideGridPage;


    public AddProductsSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void addRandomProductToBasketAndReturnToHomePage(Basket basket, int quantity) {
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        addedToBasketPopUpPage = new AddedToBasketPopUpPage(driver);

        int randomIndex = RandomHelper.getRandomNumber(homePage.getProductMiniatureContainerPage().getProductMiniatures().size());

        homePage.getProductMiniatureContainerPage()
                .selectProductByIndex(randomIndex);

        productDetailsPage.selectQuantity(quantity)
                .addProductToBasket(basket);

        addedToBasketPopUpPage.clickContinueShopping();

        productDetailsPage.returnToHomePage();

    }

    public void chooseCategoryAndProduct(String categoryName, String productName) throws Exception {
        topMenuPage = new TopMenuPage(driver);
        categoryPage = new CategoryPage(driver);

        topMenuPage.goToCategoryPageWithName(categoryName);

        categoryPage.getProductMiniatureContainerPage()
                .selectProductByName(productName);
    }

    public void addProductAndProceedToCheckOut(Basket basket) {
        productDetailsPage = new ProductDetailsPage(driver);
        addedToBasketPopUpPage = new AddedToBasketPopUpPage(driver);
        basketSideGridPage = new BasketSideGridPage(driver);

        productDetailsPage.addProductToBasket(basket);
        addedToBasketPopUpPage.clickProceedToCheckout();
        basketSideGridPage.proceedToCheckout();
    }


}
