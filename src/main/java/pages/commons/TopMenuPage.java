package pages.commons;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;
import pages.home.SearchResultsPage;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    @FindBy(css = "button[type = 'submit']")
    private WebElement submitSearchBtn;

    @FindBy(css = ".ui-autocomplete")
    private WebElement autoComplete;


    public TopMenuPage typeInSearchInput(String phrase) {
        sendKeys(searchInput, phrase);
        return this;
    }


    public SearchResultsPage typeInSearchInputAndSubmit(String phrase) {
        typeInSearchInput(phrase);
        submitSearchBtn.click();
        return new SearchResultsPage(driver);
    }


    private void hoverOverCategory(int index) {
        actions.moveToElement(categories.get(index)).perform();
    }

    private List<WebElement> getSubCategories(int index) {
        hoverOverCategory(index);
        return categories.get(index).findElements(By.cssSelector(".dropdown-submenu"));
    }

    public void goToCategoryPage(int index) {
        click(categories.get(index));

    }

    public void goToSubCategoryPage(int categoryIndex, int subCategoryIndex) {
        List<WebElement> subCategories = getSubCategories(categoryIndex);
        click(subCategories.get(subCategoryIndex));

    }

    public List<String> getSuggestedProductNames() {
        List<String> suggestedProductNames = new ArrayList<>();

        List<WebElement> suggestedProducts = defaultWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".ui-menu-item")));

        for (WebElement suggestedProduct : suggestedProducts) {
            String itemName = suggestedProduct.findElement(By.cssSelector(".product")).getText();
            suggestedProductNames.add(itemName);
        }
        return suggestedProductNames;

    }
}
