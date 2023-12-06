package pages.product;

import lombok.Getter;
import lombok.Setter;
import org.bouncycastle.crypto.paddings.TBCPadding;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pages.base.BasePage;

import java.util.List;
@Getter
@Setter
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


}
