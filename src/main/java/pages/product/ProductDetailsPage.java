package pages.product;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
@Getter
@Setter
public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    ProductQuickViewPage productQuickViewPage = new ProductQuickViewPage(driver);

}
