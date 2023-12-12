package tests.checkout;

import models.Address;
import models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.account.*;
import pages.basket.BasketSideGridPage;
import pages.checkout.CheckOutPage;
import pages.checkout.OrderConfirmedPage;
import pages.checkout.PaymentSectionPage;
import pages.checkout.ShippingSectionPage;
import pages.product.AddedToBasketPopUpPage;
import pages.product.ProductDetailsPage;
import providers.AddressFactory;
import providers.UserFactory;
import steps.Steps;

public class CheckOutTest extends Steps {
    @RepeatedTest(3)
    @Tag("yaml4")
    public void checkOutTest() {

        String categoryName = testDataProvider.getTestData("categoryName4");
        String productName = testDataProvider.getTestData("posterName4");

        UserFactory userFactory = new UserFactory();
        AddressFactory addressFactory = new AddressFactory();

        Address address1 = addressFactory.getExistingAddress("address1");


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
                .clickAddNewInvoiceAddress()
                .getAddressesSectionFormPage()
                .fillTheAddressForm(address1);


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

        Assertions.assertThat(invoiceAddressDetails).contains(address1.getStreet(), address1.getCity(), address1.getPostalCode(), address1.getCountry());

        System.out.println(addressFactory.getExistingAddress("address1"));

    }
}
