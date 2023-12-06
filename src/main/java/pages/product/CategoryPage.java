package pages.product;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Getter
@Setter
public class CategoryPage extends BasePage {

    @FindBy(css = ".h1")
    private WebElement header;

    @FindBy(css = ".category-sub-menu>li")
    private List<WebElement> subCategoriesOptions;

    @FindBy (css = "#search_filters")
    WebElement sideFilterMenuPage;

    @FindBy (css = "#products")
    ProductMiniatureContainerPage productMiniatureContainerPage;


    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getCategoryName(){
        return getText(header);
    }

    public Boolean isSideFilterMenuDisplayed(){
        return sideFilterMenuPage.isDisplayed();
    }

}
