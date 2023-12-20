package pages.checkout;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;


public class PaymentSectionPage extends BasePage {

    @FindBy(css = "#payment-option-1")
    private WebElement payByCheckOptionBtn;


    @FindBy(css = ".custom-checkbox>input")
    private WebElement acceptTermsCheckBox;

    @FindBy(css = "#payment-confirmation>div>button")
    private WebElement placeOrderBtn;

    public PaymentSectionPage(WebDriver driver) {
        super(driver);
    }

    public PaymentSectionPage selectPaymentOption() {
        clickWithoutWait(payByCheckOptionBtn);
        return this;
    }


    public PaymentSectionPage agreeToTerms() {
        clickWithoutWait(acceptTermsCheckBox);
        return this;
    }

    public void placeOrder() {
        click(placeOrderBtn);

    }


}
