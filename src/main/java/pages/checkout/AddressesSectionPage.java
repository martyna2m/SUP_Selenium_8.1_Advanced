package pages.checkout;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Getter
public class AddressesSectionPage extends BasePage {

    AddressesSectionFormPage addressesSectionFormPage = new AddressesSectionFormPage(driver);

    @FindBy(xpath = "//*[contains(text(), 'differs')]")
    private WebElement addressDiffersBtn;

    @FindBy(xpath = "//*[@id='delivery-addresses']/following-sibling::p/a")
    private List<WebElement> addNewAddressBtns;

    @FindBy(css = "#delivery-addresses>article")
    private List<WebElement> shippingAddresses;


    public AddressesSectionPage(WebDriver driver) {
        super(driver);
    }

    private void clickAddressDiffers() {
        click(addressDiffersBtn);

    }

    public AddressesSectionPage clickAddNewInvoiceAddress() {
        clickAddressDiffers();
        deleteRedundantAddresses();
        if (shippingAddresses.size() > 1) {
            click(addNewAddressBtns.get(1));
        }

        return this;
    }

    public void deleteRedundantAddresses() {
        if (shippingAddresses.size() > 2) {
            shippingAddresses.get(shippingAddresses.size() - 1).findElement(By.cssSelector(".delete-address")).click();
        }
    }

}
