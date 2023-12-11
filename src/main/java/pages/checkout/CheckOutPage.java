package pages.checkout;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Getter
@Setter
public class CheckOutPage extends BasePage {


    AddressesSectionPage addressesSectionPage = new AddressesSectionPage(driver);

    @FindBy(css = ".cart-grid-body>section")
    private List<WebElement> sections;


    public CheckOutPage(WebDriver driver) {
        super(driver);
    }


}
