package pages.categories;

import helpers.PriceHelper;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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


    public List<BigDecimal> getPricesFromRangeFilter() {
        List<BigDecimal> prices = new ArrayList<>();
        List<String> pricesStrings = extractTwoPrices(getPriceRangeText());
        prices.add(PriceHelper.deleteCurrency(pricesStrings.get(0)));
        prices.add(PriceHelper.deleteCurrency(pricesStrings.get(1)));
        return prices;
    }


    public void setPriceFilters(int expectedLowerPrice, int expectedHigherPrice) {
        BigDecimal expectedLowerPriceBD = PriceHelper.convertIntToBigDecimal(expectedLowerPrice);
        BigDecimal expectedHigherPriceBD = PriceHelper.convertIntToBigDecimal(expectedHigherPrice);
        setFilter(0, 0, expectedLowerPriceBD);
        setFilter(1, 1, expectedHigherPriceBD);

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

    private void setFilter(int handleIndex, int pricesIndex, BigDecimal expectedPrice) {
        List<BigDecimal> prices = getPricesFromRangeFilter();
        while (!Objects.equals(prices.get(pricesIndex), expectedPrice)) {
            WebElement handle = sliderHandles.get(handleIndex);
            getDirectionAndClick(handle, prices.get(pricesIndex), expectedPrice);
            prices = getPricesFromRangeFilter();

        }

    }

    private List<String> extractTwoPrices(String priceRange) {
        Pattern pattern = Pattern.compile("\\$\\d+\\.\\d{2}");
        Matcher matcher = pattern.matcher(priceRange);

        List<String> pricesList = new ArrayList<>();

        while (matcher.find()) {
            pricesList.add(matcher.group());
        }
        return pricesList;
    }
}

