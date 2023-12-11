package pages.checkout;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Getter

public class PaymentSectionPage extends BasePage {

    @FindBy(css = "#payment-option-1")
    private WebElement paymentOption;


    @FindBy(css = ".custom-checkbox>input")
    private WebElement acceptTermsCheckBox;

    @FindBy(css = "#payment-confirmation>div>button")
    private WebElement placeOrderBtn;

    public PaymentSectionPage(WebDriver driver) {
        super(driver);
    }

    public PaymentSectionPage selectPaymentOption() {
        click(paymentOption);
        return this;
    }


    public PaymentSectionPage agreeToTerms() {
        click(acceptTermsCheckBox);
        return this;
    }

    public void placeOrder() {
        click(placeOrderBtn);

    }


}
