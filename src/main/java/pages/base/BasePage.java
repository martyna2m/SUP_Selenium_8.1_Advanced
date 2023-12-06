package pages.base;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Getter
@Setter
public class BasePage {


    public WebDriver driver;
    public Actions actions;
    public WebDriverWait defaultWait;

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

    public void waitToBeClickable(WebElement element) {
        defaultWait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public void executeInFrame(WebElement frame, Runnable runnable) {
        driver.switchTo().frame(frame);
        runnable.run();
        driver.switchTo().defaultContent();
        //if not used, delete
    }


    public BigDecimal getPrice(WebElement element) {
        // delete currency
        return new BigDecimal(getText(element));
    }

    public int getIntNumber(WebElement element) {
        return Integer.parseInt(element.getText());
    }

    public BigDecimal getTotalPrice(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getBigDecNumber(WebElement element) {
        return BigDecimal.valueOf(Long.parseLong(element.getText()));
    }
}
