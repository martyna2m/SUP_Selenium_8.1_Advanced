package pages.commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.products.CategoryPage;

import java.util.List;

public class TopMenuPage extends BasePage {
    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#_desktop_logo")
    private WebElement myStoreBtn;
    @FindBy(css = "#top-menu>.category")
    private List<WebElement> categories;

    @FindBy(css = "input[name = 's']")
    private WebElement searchInput;


    public void typeInSearchInput(String phrase){
        sendKeys(searchInput, phrase);
    }


    public void hoverOverCategory(int index) {
        actions.moveToElement(categories.get(index)).perform();
    }

    public List<WebElement> getSubCategories(int index) {
        hoverOverCategory(index);
        return categories.get(index).findElements(By.cssSelector(".dropdown-submenu"));
    }

    public CategoryPage goToCategoryPage(int index) {
        click(categories.get(index));
        return new CategoryPage(driver);
    }
    public CategoryPage goToSubCategoryPage(int catIndex, int subCatIndex) {
        List<WebElement> subCategories = getSubCategories(catIndex);
        click(subCategories.get(subCatIndex));
        return new CategoryPage(driver);
    }

}
