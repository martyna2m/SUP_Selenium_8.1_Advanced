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
import java.util.stream.Collectors;


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

    public List<String> getCategoryNames() {
        return categories.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

    }

    private WebElement chooseCategoryWithName(String categoryName) {
        for (WebElement category : categories) {
            if (getText(category).equals(categoryName.toUpperCase())) {
                return category;
            }
        }
        return null;
    }


    public List<WebElement> getSubCategories(String categoryName) {
        WebElement chosenCategory = chooseCategoryWithName(categoryName);
        hoverOverElement(chosenCategory);
        assert chosenCategory != null;
        return chosenCategory.findElements(By.cssSelector(".dropdown-submenu"));
    }


    public void goToCategoryPageWithName(String categoryName) {
        click(chooseCategoryWithName(categoryName));
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
