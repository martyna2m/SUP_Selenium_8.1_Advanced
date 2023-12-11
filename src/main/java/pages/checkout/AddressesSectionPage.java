package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class AddressesSectionPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'differs')]")
    private WebElement addressDiffersBtn;

    public AddressesSectionPage(WebDriver driver) {
        super(driver);
    }


    public AddressesSectionPage clickAddressDiffers() {
        click(addressDiffersBtn);
        return this;
    }
}
