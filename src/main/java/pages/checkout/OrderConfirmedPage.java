package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class OrderConfirmedPage extends BasePage {


    @FindBy(xpath = "//*[@id = 'order-details']//li[contains(text(), 'reference')]")
    private WebElement referenceNumber;

    public OrderConfirmedPage(WebDriver driver) {
        super(driver);
    }

    public String getReferenceNumber() {
        return getText(referenceNumber);
    }

}
