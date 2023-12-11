package tests.checkout;

import models.Address;
import models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.account.*;
import pages.basket.BasketSideGridPage;
import pages.checkout.CheckOutPage;
import pages.checkout.OrderConfirmedPage;
import pages.checkout.PaymentSectionPage;
import pages.checkout.ShippingSectionPage;
import pages.product.AddedToBasketPopUpPage;
import pages.product.ProductDetailsPage;
import providers.UserFactory;
import steps.Steps;

public class CheckOutTest extends Steps {
    @Test
    public void checkOutTest() throws InterruptedException {

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

        Address usedAddress = at(CheckOutPage.class)
                .getAddressesSectionPage()
                .clickAddNewInvoiceAddress()
                .getAddressesSectionFormPage()
                .fillTheFormWithFakeAddress();


        at(ShippingSectionPage.class)
                .chooseShippingMethod()
                .clickContinue();


        at(PaymentSectionPage.class)
                .selectPaymentOption()
                .agreeToTerms()
                .placeOrder();

        String expectedOrderDetails = at(OrderConfirmedPage.class).getOrderDetails();

        openPage("myAccountPage");
        at(MyAccountPage.class).goToOrderHistory();
        OrderRowPage recentOrder = at(OrderHistoryPage.class).getRows().get(0);

        String actualOrderDetails = recentOrder.getHistoricalOrderDetails();
        String invoiceStatus = recentOrder.getInvoiceStatus();

        recentOrder.goToDetails();


        String deliveryAddressDetails = at(OrderDetails.class).getNameFromDeliveryAddress();
        String invoiceAddressDetails = at(OrderDetails.class).getNameFromInvoiceAddress();


        Assertions.assertThat(expectedOrderDetails).isEqualTo(actualOrderDetails);
        Assertions.assertThat(invoiceStatus).isEqualTo(testDataProvider.getTestData("paymentStatus"));

        Assertions.assertThat(deliveryAddressDetails).contains(user1.getFullName());
        Assertions.assertThat(invoiceAddressDetails).contains(user1.getFullName());

        Assertions.assertThat(invoiceAddressDetails).contains(usedAddress.getAddress(), usedAddress.getCity(), usedAddress.getPostalCode(), usedAddress.getCountry());


    }
}
