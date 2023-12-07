package pages.commons;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.account.SignInPage;
import pages.base.BasePage;
import pages.contact.ContactUsPage;
import pages.basket.BasketPage;
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


    public ContactUsPage goToContactUsPage(){
        click(contactUsBtn);
        return new ContactUsPage(driver);
    }
     public SignInPage goToSignInPage(){
        click(signInBtn);
        return new SignInPage(driver);
    }
     public BasketPage goToBasketPage(){
        click(basketBtn);
        return new BasketPage(driver);
    }
    public int getNumberOfItemsInBasket(){
       return Integer.parseInt(getText(numberOfProductsInBasketIcon).replaceAll("\\(([^)]*)\\)", "$1"));
    }

}
