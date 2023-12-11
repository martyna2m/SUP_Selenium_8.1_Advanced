package tests.checkout;

import models.User;
import org.junit.jupiter.api.Test;
import pages.account.LogInPage;
import pages.basket.BasketSideGridPage;
import pages.checkout.CheckOutPage;
import pages.checkout.ShippingSectionPage;
import pages.product.AddedToBasketPopUpPage;
import pages.product.ProductDetailsPage;
import providers.UserFactory;
import steps.Steps;

public class CheckOutTest extends Steps {
    @Test
    public void checkOutTest() {

        String categoryName = "Art";
        String productName = "THE BEST IS YET POSTER";

        UserFactory userFactory = new UserFactory();
        User user1 = userFactory.getExisitingUser("user1");

        openPage("loginPage");

        at(LogInPage.class)
                .logIn(user1.getEmail(), user1.getPassword());

        chooseCategoryAndProduct(categoryName, productName);

        at(ProductDetailsPage.class)
                .addProductToBasket();

        at(AddedToBasketPopUpPage.class)
                .clickProceedToCheckout();

        at(BasketSideGridPage.class)
                .proceedToCheckout();

        at(CheckOutPage.class)
                .getAddressesSectionPage()
                .clickAddressDiffers()
                .fillTheForm();

    at(ShippingSectionPage.class)
            .chooseShippingMethod()
            .clickContinue();











    }
}
