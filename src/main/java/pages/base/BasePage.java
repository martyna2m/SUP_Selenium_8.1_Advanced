package pages.base;

import helpers.PriceHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import providers.TestDataProvider;

import java.math.BigDecimal;
import java.time.Duration;


public class BasePage {

    public WebDriver driver;
    public Actions actions;
    public WebDriverWait defaultWait;
    public TestDataProvider testDataProvider;


    public BasePage(WebDriver driver) {
        init(driver);
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver, WebElement parent) {
        init(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(parent), this);
    }


    private void init(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.testDataProvider = new TestDataProvider();
        this.defaultWait = new WebDriverWait(driver, Duration.ofSeconds(parseInt(testDataProvider.getTestData("defaultWait"))));
    }


    public void click(WebElement element) {
        waitToBeClickable(element);
        element.click();
    }

    public void clickWithoutWait(WebElement element) {
        element.click();
    }


    public void sendKeys(WebElement element, String phrase) {
        waitToBeClickable(element);
        element.clear();
        sendKeysWithoutClear(element, phrase);
    }

    public void sendKeysWithoutClear(WebElement element, String phrase) {
        element.sendKeys(phrase);
    }


    public String getText(WebElement element) {
        waitToBeVisible(element);
        return element.getText();
    }

    protected void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void waitToBeClickable(WebElement element) {
        defaultWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeVisible(WebElement element) {
        defaultWait.until(ExpectedConditions.visibilityOf(element));
    }


    public int parseInt(String text) {
        return Integer.parseInt(text);

    }

    public BigDecimal getPriceFromElement(WebElement element) {
        waitToBeVisible(element);
        return PriceHelper.deleteCurrency(getText(element));
    }

    public int getIntNumberFromValue(WebElement element) {
        return parseInt(element.getAttribute("value"));
    }

    public int getIntNumberFromText(WebElement element) {
        return parseInt(getText(element));
    }

    public void selectByVisibleText(WebElement selectElement, String text) {
        new Select(selectElement).selectByVisibleText(text);
    }


}
