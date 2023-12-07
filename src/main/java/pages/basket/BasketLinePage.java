package pages.basket;

import lombok.Getter;
import lombok.Setter;
import models.Basket;
import models.BasketLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pages.base.BasePage;

@Getter
@Setter
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

    public BasketLine toBasketLine() {
        return new BasketLine(new Product(this.nameLabel.getText(), getPrice(this.currentPrice)), getIntNumberFromValue(this.quantityInput), getTotalPrice(getPrice(this.currentPrice), getIntNumberFromValue(this.quantityInput)));

    }
}
