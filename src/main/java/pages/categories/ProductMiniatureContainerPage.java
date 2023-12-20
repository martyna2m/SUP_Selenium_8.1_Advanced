package pages.categories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMiniatureContainerPage extends BasePage {

    public ProductMiniatureContainerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-miniature")
    List<WebElement> productMiniatures;


    public List<ProductMiniaturePage> getProductMiniatures() {
        List<ProductMiniaturePage> productMiniaturePages = new ArrayList<>();
        for (WebElement productMiniaturePage : productMiniatures) {
            productMiniaturePages.add(new ProductMiniaturePage(driver, productMiniaturePage));
        }
        return productMiniaturePages;
    }


    public List<String> getProductNamesList() {
        return getProductMiniatures().stream()
                .map(ProductMiniaturePage::getName)
                .collect(Collectors.toList());

    }


    public void selectProductByName(String expectedName) throws Exception {
        for (ProductMiniaturePage productMiniature : getProductMiniatures()) {
            if (productMiniature.getName() != null && expectedName.equalsIgnoreCase(productMiniature.getName())) {
                productMiniature.clickOnMiniature();
                return;
            }
        }
        throw new Exception("Product with name '" + expectedName + "' not found");

    }

    public void selectProductByIndex(int index) {
        try {
            getProductMiniatures().get(index).clickOnMiniature();
        } catch (Exception e) {
            System.out.println("No product found by index '" + index + "'");
        }
    }


    public String getProductName(int index) {
        return getProductMiniatures().get(index).getName();
    }
}


