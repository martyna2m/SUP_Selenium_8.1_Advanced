package pages.categories;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.util.List;

@Getter
@Setter
public class CategoryPage extends BasePage {

    ProductMiniatureContainerPage productMiniatureContainerPage = new ProductMiniatureContainerPage(driver);

    SideFilterMenuPage sideFilterMenuPage = new SideFilterMenuPage(driver);


    @FindBy(css = ".h1")
    private WebElement header;

    @FindBy(css = ".category-sub-menu>li")
    private List<WebElement> subCategoriesOptions;

    @FindBy(css = ".total-products")
    private WebElement totalNumberOfProductsInfo;

    @FindBy(css = "#js-active-search-filters>ul>li")
    private List<WebElement> activeFilters;

    @FindBy(css = ".spinner")
    private WebElement spinner;


    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getCategoryPageName() {
        return getText(header).toUpperCase();
    }

    public String getTotalNumberOfProductsInfo() {
        String actualInfo = getText(totalNumberOfProductsInfo);
        if (actualInfo.equals("There is 1 product.")) {
            return "There are 1 products.";
        }
        return actualInfo;
    }

    public CategoryPage deleteFilter(String filterName) {
        for (WebElement filter : activeFilters) {
            if (getText(filter).toLowerCase().contains(filterName.toLowerCase())) {
                click(filter.findElement(By.cssSelector("a>i")));
            }
            defaultWait.until(ExpectedConditions.invisibilityOf(spinner));
        }
        return this;
    }


}
