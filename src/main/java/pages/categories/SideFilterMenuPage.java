package pages.categories;

import helpers.PriceHelper;
import lombok.Getter;
import models.FilterRange;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SideFilterMenuPage extends BasePage {
    @Getter
    @FindBy(css = ".category-sub-menu>li")
    private List<WebElement> subCategories;

    @FindBy(css = "#search_filters>.facet")
    private List<WebElement> filterSections;

    @FindBy(css = "li>p")
    private WebElement priceRangeInfo;

    @FindBy(css = ".ui-slider")
    private WebElement priceSlider;

    @FindBy(css = ".ui-slider>a")
    private List<WebElement> sliderHandles;

    @FindBy(css = ".spinner")
    private WebElement spinner;

    private Map<String, WebElement> handlesMap;



    public void goToSubCategoryPage(WebElement element) {
        click(element);
    }

    public String getSubCategoryName(WebElement element) {
        return getText(element).toUpperCase();
    }

    public List<String> getSubcategoryNames(List<WebElement> subCategories) {
        List<String> subcategoryNameList = new ArrayList<>();

        for (WebElement subCategory : subCategories) {
            String subcategoryName = getSubCategoryName(subCategory);
            subcategoryNameList.add(subcategoryName);
        }

        return subcategoryNameList;
    }

    public SideFilterMenuPage(WebDriver driver) {
        super(driver);
    }


    public Boolean isSideFilterMenuDisplayed() {
        return filterSections.get(0).isDisplayed();
    }


    public String getPriceRangeText() {
        return getText(priceRangeInfo);
    }


    public FilterRange getPricesFromRangeFilter() {
        return extractTwoPrices(getPriceRangeText());

    }


    public void setPriceFilters(BigDecimal expectedLowerPrice, BigDecimal expectedHigherPrice) {
        setFilter("left", "from", expectedLowerPrice);
        setFilter("right", "to", expectedHigherPrice);

    }


    private void getDirectionAndClick(WebElement element, BigDecimal currentPrice, BigDecimal expectedPrice) {
        actions.clickAndHold(element);
        if (currentPrice.compareTo(expectedPrice) > 0) {
            element.sendKeys(Keys.ARROW_LEFT);
        }
        if (currentPrice.compareTo(expectedPrice) < 0) {
            element.sendKeys(Keys.ARROW_RIGHT);
        }
        defaultWait.until(ExpectedConditions.invisibilityOf(spinner));

    }

    private void setFilter(String leftOrRight, String fromOrTo, BigDecimal expectedPrice) {
        FilterRange currentPrices = getPricesFromRangeFilter();

        while (!(currentPrices.getValue(fromOrTo).equals(expectedPrice))) {
            getDirectionAndClick(getHandle(leftOrRight), currentPrices.getValue(fromOrTo), expectedPrice);
            currentPrices = getPricesFromRangeFilter();

        }

    }

    private FilterRange extractTwoPrices(String priceRange) {
        String[] separatedPrices = priceRange.split(" - ");
        String bottomPrice = separatedPrices[0];
        String topPrice = separatedPrices[1];
        return new FilterRange(PriceHelper.deleteCurrency(bottomPrice), PriceHelper.deleteCurrency(topPrice));
    }

    private WebElement getHandle(String name) {
        handlesMap = new HashMap<>();
        handlesMap.put("left", sliderHandles.get(0));
        handlesMap.put("right", sliderHandles.get(1));
        return handlesMap.getOrDefault(name.toLowerCase(), null);

    }

}

