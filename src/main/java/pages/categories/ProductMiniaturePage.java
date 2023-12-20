package pages.categories;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;


public class ProductMiniaturePage extends BasePage {

    WebDriver driver;

    public ProductMiniaturePage(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @FindBy(css = ".product-title")
    private WebElement nameLabel;

    @FindBy(css = ".product-price-and-shipping>.price")
    private WebElement currentPrice;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".product-miniature>div>a")
    private WebElement icon;

    @FindBy(css = ".quick-view")
    WebElement quickViewButton;

    @FindBy(css = "div>.color")
    List<WebElement> colorOptions;

    public String getName() {
        return getText(this.nameLabel);
    }

    public BigDecimal getCurrentProductPrice() {
        return getPriceFromElement(currentPrice);
    }

    public void clickOnMiniature(){
        click(icon);
    }
}
