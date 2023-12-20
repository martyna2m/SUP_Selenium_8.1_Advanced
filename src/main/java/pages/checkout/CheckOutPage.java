package pages.checkout;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;


public class CheckOutPage extends BasePage {

    @Getter
    private AddressesSectionPage addressesSectionPage = new AddressesSectionPage(driver);

    @FindBy(css = ".cart-grid-body>section")
    private List<WebElement> sections;


    public CheckOutPage(WebDriver driver) {
        super(driver);
    }


}
