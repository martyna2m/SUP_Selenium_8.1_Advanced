package tests.checkout;

import models.Address;
import models.Basket;
import models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import pages.account.*;
import pages.checkout.CheckOutPage;
import pages.checkout.OrderConfirmedPage;
import providers.AddressFactory;
import providers.UserFactory;
import steps.Steps;

public class CheckOutTest extends Steps {
    @RepeatedTest(1)
    @Tag("checkout")
    public void checkOutTest() throws Exception {

        Basket basket = new Basket();

        String categoryName = testDataProvider.getTestData("categoryName");
        String productName = testDataProvider.getTestData("posterName");

        UserFactory userFactory = new UserFactory();
        AddressFactory addressFactory = new AddressFactory();

        Address address = addressFactory.getExistingAddress("address1");
        User user = userFactory.getExisitingUser("user1");

        openPage("loginPage");

        at(LogInPage.class)
                .logIn(user.getEmail(), user.getPassword());

        chooseCategoryAndProduct(categoryName, productName);
        addProductAndProceedToCheckOut(basket);

        at(CheckOutPage.class)
                .getAddressesSectionPage()
                .clickAddNewInvoiceAddress()
                .getAddressesSectionFormPage()
                .fillTheAddressForm(address);

        fillPaymentAndShippingSection();

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

        Assertions.assertThat(deliveryAddressDetails).contains(user.getFullName());
        Assertions.assertThat(invoiceAddressDetails).contains(user.getFullName());
        Assertions.assertThat(invoiceAddressDetails).contains(address.getStreet(), address.getCity(), address.getPostalCode(), address.getCountry());

    }
}
