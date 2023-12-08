package pages.base;

import lombok.Getter;
import lombok.Setter;
import models.Basket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class BasePage {


    public WebDriver driver;
    public Actions actions;
    public WebDriverWait defaultWait;
    public Basket basket;

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
        this.defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.basket = Basket.getInstance();
        // get 10 from yaml
    }


    public void click(WebElement element) {
        waitToBeClickable(element);
        element.click();
    }

    public void sendKeys(WebElement element, String phrase) {
        // add conditions
        waitToBeClickable(element);
        element.clear();
        sendKeysWithoutClear(element, phrase);
    }

    public void sendKeysWithoutClear(WebElement element, String phrase) {
        element.sendKeys(phrase);
    }


    public String getText(WebElement element) {
        return element.getText();
    }

    protected void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void waitToBeClickable(WebElement element) {
        defaultWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public void executeInFrame(WebElement frame, Runnable runnable) {
        driver.switchTo().frame(frame);
        runnable.run();
        driver.switchTo().defaultContent();
        //if not used, delete
    }

    public static BigDecimal deleteCurrency(String priceWithCurrency) {
        Pattern pattern = Pattern.compile("\\$\\d+\\.\\d{2}");
        Matcher matcher = pattern.matcher(priceWithCurrency);

        if (matcher.find()) {
            String priceString = matcher.group().substring(1);
            return new BigDecimal(priceString);
        } else {

            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPrice(WebElement element) {
        return deleteCurrency(getText(element));
    }

    public int getIntNumberFromValue(WebElement element) {
        return Integer.parseInt(element.getAttribute("value"));
    }

    public int getIntNumberFromText(WebElement element) {
        return Integer.parseInt(getText(element));
    }

    public BigDecimal getTotalPrice(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }


}
