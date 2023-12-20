package pages.basket;

import helpers.PriceHelper;
import models.Basket;
import models.BasketLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class BasketPage extends BasePage {

    @FindBy(css = ".cart-item")
    List<WebElement> basketLinePages;

    BasketSideGridPage basketSideGridPage = new BasketSideGridPage(driver);


    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public List<BasketLinePage> getBasketLinePages() {
        return basketLinePages.stream().map(basketLine -> new BasketLinePage(driver, basketLine)).collect(Collectors.toList());
    }

    public List<BasketLine> getBasketLinesInBasket() {
        return getBasketLinePages().stream().map(BasketLinePage::toBasketLine).collect(Collectors.toList());
    }

    public BigDecimal getTotalSumOfBasketLines() {
        BigDecimal totalSum = PriceHelper.convertIntToBigDecimal(0);

        for (BasketLine basketLine : getBasketLinesInBasket()) {
            totalSum = totalSum.add(basketLine.getTotalPrice());
        }
        return totalSum;
    }

    public void deleteBasketLine(Basket basket, BasketLinePage basketLinePage) {
        int initialNumberOfBasketLines = basketLinePages.size();
        if (initialNumberOfBasketLines > 0) {
            basketLinePage.deleteLine(basket);
            defaultWait.until(((WebDriver drv) -> basketLinePages.size() < initialNumberOfBasketLines));
        }
    }
}
