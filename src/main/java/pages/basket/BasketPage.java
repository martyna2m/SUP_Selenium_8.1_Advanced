package pages.basket;

import lombok.Getter;
import lombok.Setter;
import models.BasketLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.product.ProductMiniaturePage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BasketPage extends BasePage {

    @FindBy(css = ".cart-item")
    List<WebElement> basketLines;

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public List<BasketLinePage> getBasketLines() {
        List<BasketLinePage> basketLinePages = new ArrayList<>();
        for (WebElement basketLine : basketLines){
            basketLinePages.add(new BasketLinePage(driver, basketLine));
        }
        return basketLinePages;
    }


}
