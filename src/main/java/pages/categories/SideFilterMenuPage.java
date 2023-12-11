package pages.categories;

import lombok.Getter;
import lombok.Setter;
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

@Getter
@Setter
public class SideFilterMenuPage extends BasePage {

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


    public SideFilterMenuPage(WebDriver driver) {
        super(driver);
    }


    public Boolean isSideFilterMenuDisplayed() {
        return filterSections.get(0).isDisplayed();
    }



    public String getPriceRangeText() {
        return getText(priceRangeInfo);
    }

    public List<String> extractTwoPrices(String priceRange) {
        Pattern pattern = Pattern.compile("\\$\\d+\\.\\d{2}");
        Matcher matcher = pattern.matcher(priceRange);

        List<String> pricesList = new ArrayList<>();

        while (matcher.find()) {
            pricesList.add(matcher.group());
        }
        return pricesList;
    }

    public List<BigDecimal> getPricesFromRangeFilter() {
        List<BigDecimal> prices = new ArrayList<>();
        List<String> pricesStrings = extractTwoPrices(getPriceRangeText());
        prices.add(deleteCurrency(pricesStrings.get(0)));
        prices.add(deleteCurrency(pricesStrings.get(1)));
        return prices;
    }

    public void setPriceFilter(int expectedLowerPrice, int expectedHigherPrice) {
        List<BigDecimal> prices = getPricesFromRangeFilter();

        while (!Objects.equals(prices.get(0), convertIntToBigDecimal(expectedLowerPrice))) {
            WebElement leftHandle = sliderHandles.get(0);
            actions.clickAndHold(leftHandle);
            leftHandle.sendKeys(Keys.ARROW_RIGHT);
            defaultWait.until(ExpectedConditions.invisibilityOf(spinner));
            prices = getPricesFromRangeFilter();

            while (!Objects.equals(prices.get(1), convertIntToBigDecimal(expectedHigherPrice))) {
                WebElement rightHandle = sliderHandles.get(1);
                actions.clickAndHold(rightHandle);
                rightHandle.sendKeys(Keys.ARROW_LEFT);
                defaultWait.until(ExpectedConditions.invisibilityOf(spinner));
                prices = getPricesFromRangeFilter();
            }

        }
    }


}

