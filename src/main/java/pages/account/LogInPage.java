package pages.account;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Getter
@Setter
public class LogInPage extends BasePage {
    public LogInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='content']//*[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@id='content']//*[@name='password']")
    private WebElement passwordInput;

    @FindBy(css = "#submit-login")
    private WebElement signInBtn;

    @FindBy(css = ".forgot-password>a")
    private WebElement forgotPasswordBtn;

    @FindBy(css = ".no-account>a")
    private WebElement noAccountBtn;

    public void logIn(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        click(signInBtn);
    }
}
