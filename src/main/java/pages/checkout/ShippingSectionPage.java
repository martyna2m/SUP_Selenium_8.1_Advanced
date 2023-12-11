package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class ShippingSectionPage extends BasePage {

    @FindBy(css = "#delivery_option_2")
    private WebElement myCarrierOption;

    @FindBy(css = "[name='confirmDeliveryOption']")
    private WebElement continueBtn;


    public ShippingSectionPage chooseShippingMethod() {
        if (!myCarrierOption.isSelected()){
            click(myCarrierOption);
        }
        return this;
    }

    public void clickContinue() {
        click(continueBtn);
    }


    public ShippingSectionPage(WebDriver driver) {
        super(driver);
    }


}
