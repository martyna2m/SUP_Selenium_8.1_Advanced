package steps.checkoutSteps;

import org.openqa.selenium.WebDriver;
import pages.checkout.PaymentSectionPage;
import pages.checkout.ShippingSectionPage;
import tests.base.TestBase;

public class CheckoutSteps {

    WebDriver driver;
    ShippingSectionPage shippingSectionPage;
    PaymentSectionPage paymentSectionPage;

    public CheckoutSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void fillPaymentAndShippingSection() {

        shippingSectionPage = new ShippingSectionPage(driver);
        paymentSectionPage = new PaymentSectionPage(driver);

        shippingSectionPage.chooseShippingMethod()
                .clickContinue();

        paymentSectionPage.selectPaymentOption()
                .agreeToTerms()
                .placeOrder();
    }

}
