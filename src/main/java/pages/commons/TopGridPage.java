package pages.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.account.SignInPage;
import pages.base.BasePage;
import pages.contact.ContactUsPage;
import pages.basket.BasketPage;

public class TopGridPage extends BasePage {
    public TopGridPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#contact-link")
    private WebElement contactUsBtn;

    @FindBy(css = ".user-info")
    private WebElement signInBtn;

    @FindBy(css = "._desktop_cart")
    private WebElement cartBtn;


    public ContactUsPage goToContactUsPage(){
        click(contactUsBtn);
        return new ContactUsPage(driver);
    }
     public SignInPage goToSignInPage(){
        click(signInBtn);
        return new SignInPage(driver);
    }
     public BasketPage goToCartPage(){
        click(cartBtn);
        return new BasketPage(driver);
    }

}
