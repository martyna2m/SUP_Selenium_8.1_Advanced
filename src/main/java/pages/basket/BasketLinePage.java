package pages.basket;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Getter
@Setter
public class BasketLinePage extends BasePage {
    public BasketLinePage(WebDriver driver) {
    super(driver);
}

    @FindBy(css = ".product-line-info>a")
    private WebElement productName;

    @FindBy(css = ".current-price")
    private WebElement productCurrentPrice;

    @FindBy(css = ".size")
    private WebElement size;

    @FindBy(css = ".color")
    private WebElement color;

    @FindBy(css = ".type")
    private WebElement attribute;


}
