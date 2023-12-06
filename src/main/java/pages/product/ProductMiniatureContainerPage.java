package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.basket.BasketLinePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductMiniatureContainerPage extends BasePage {

    public ProductMiniatureContainerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-miniature")
    List<WebElement> productMiniatures;

    public List<ProductMiniaturePage> getProductMiniatures() {
        List<ProductMiniaturePage> productMiniaturePages = new ArrayList<>();
        for (WebElement productMiniaturePage : productMiniatures){
            productMiniaturePages.add(new ProductMiniaturePage(driver, productMiniaturePage));
        }
        return productMiniaturePages;
    }


    public List<String> getProductNamesList() {
        List<String> productNames = new ArrayList<>();
        List<ProductMiniaturePage> miniatures = getProductMiniatures();
        for (ProductMiniaturePage miniature : miniatures) {
            productNames.add(miniature.getNameLabel().getText());
        }
        return productNames;
    }

    public String getProductName(int index) {
        return getProductNamesList().get(index);
    }

}
