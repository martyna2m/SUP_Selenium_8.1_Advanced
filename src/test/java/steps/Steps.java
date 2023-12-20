package steps;

import models.Basket;
import pages.basket.BasketSideGridPage;
import pages.categories.CategoryPage;
import pages.checkout.PaymentSectionPage;
import pages.checkout.ShippingSectionPage;
import pages.commons.TopMenuPage;
import pages.home.HomePage;
import pages.product.AddedToBasketPopUpPage;
import pages.product.ProductDetailsPage;
import tests.base.TestBase;

import java.util.Random;

public class Steps extends TestBase {

    public void addRandomProductToBasketAndReturnToHomePage(Basket basket, int quantity)  {
        int randomIndex = new Random().nextInt(at(HomePage.class).getProductMiniatureContainerPage().getProductMiniatures().size());

        at(HomePage.class)
                .getProductMiniatureContainerPage()
                .selectProductByIndex(randomIndex);

        at(ProductDetailsPage.class)
                .selectQuantity(quantity)
                .addProductToBasket(basket);

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

    public void addProductAndProceedToCheckOut(Basket basket){

        at(ProductDetailsPage.class)
                .addProductToBasket(basket);

        at(AddedToBasketPopUpPage.class)
                .clickProceedToCheckout();

        at(BasketSideGridPage.class)
                .proceedToCheckout();
    }

    public void fillPaymentAndShippingSection(){
        at(ShippingSectionPage.class)
                .chooseShippingMethod()
                .clickContinue();

        at(PaymentSectionPage.class)
                .selectPaymentOption()
                .agreeToTerms()
                .placeOrder();
    }
}
