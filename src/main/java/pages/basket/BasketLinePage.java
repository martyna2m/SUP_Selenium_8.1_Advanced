package pages.basket;

import helpers.PriceHelper;
import models.Basket;
import models.BasketLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;


public class BasketLinePage extends BasePage {
    public BasketLinePage(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @FindBy(css = ".product-line-info>a")
    private WebElement nameLabel;

    @FindBy(css = ".current-price>span")
    private WebElement currentPrice;

    @FindBy(xpath = "//*[@class='row']//*[@class='product-price']")
    private WebElement totalPrice;

    @FindBy(css = ".size")
    private WebElement size;

    @FindBy(css = ".color")
    private WebElement color;

    @FindBy(css = ".type")
    private WebElement attribute;

    @FindBy(css = "[name = 'product-quantity-spin']")
    private WebElement quantityInput;


    @FindBy(css = ".remove-from-cart>i")
    private WebElement deleteBtn;


    public BasketLine toBasketLine() {
        return new BasketLine(new Product(nameLabel.getText(), getPriceFromElement(currentPrice)), getIntNumberFromValue(quantityInput), PriceHelper.getTotalPriceByQuantity(getPriceFromElement(currentPrice), getIntNumberFromValue(quantityInput)));

    }

    public void deleteLine(Basket basket) {
        click(deleteBtn);
        basket.removeBasketLine(nameLabel.getText());

    }

    public BigDecimal getTotalPrice(){
     return getPriceFromElement(totalPrice);
    }
}
