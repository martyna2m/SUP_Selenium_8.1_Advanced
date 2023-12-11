package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class MyAccountPage extends BasePage {

    @FindBy(css = "#history-link")
    private WebElement orderHistoryLink;
    @FindBy(css = "#addresses-link")
    private WebElement addressesLink;

    @FindBy(css = "#identity-link")
    private WebElement infoLink;

 @FindBy(css = "#order-slips-link")
    private WebElement orderSlipsLink;

 @FindBy(css = "#psgdpr-link")
    private WebElement gdprLink;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public void goToOrderHistory(){
        click(orderHistoryLink);

    }


}
