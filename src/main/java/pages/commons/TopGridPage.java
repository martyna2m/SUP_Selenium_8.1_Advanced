package pages.commons;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Getter
@Setter
public class TopGridPage extends BasePage {
    public TopGridPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#contact-link")
    private WebElement contactUsBtn;

    @FindBy(css = ".user-info>a")
    private WebElement signInBtn;

    @FindBy(css = "#_desktop_cart")
    private WebElement basketBtn;

    @FindBy(css = ".cart-products-count")
    private WebElement numberOfProductsInBasketIcon;


    public void goToContactUsPage() {
        click(contactUsBtn);
    }

    public void goToSignInPage() {
        click(signInBtn);
    }

    public void goToBasketPage() {
        click(basketBtn);
    }

    public int getNumberOfItemsInBasket() {
        return parseInt(getText(numberOfProductsInBasketIcon).replaceAll("\\(([^)]*)\\)", "$1"));
    }

}
