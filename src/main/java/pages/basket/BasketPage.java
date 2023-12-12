package pages.basket;

import lombok.Getter;
import lombok.Setter;
import models.BasketLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class BasketPage extends BasePage {

    @FindBy(css = ".cart-item")
    List<WebElement> basketLines;

    BasketSideGridPage basketSideGridPage = new BasketSideGridPage(driver);


    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public List<BasketLinePage> getBasketLinePages() {
        List<BasketLinePage> basketLinePages = new ArrayList<>();
        for (WebElement basketLine : basketLines) {
            basketLinePages.add(new BasketLinePage(driver, basketLine));
        }
        return basketLinePages;
    }

    public List<BasketLine> getBasketLinesInBasket() {
        List<BasketLine> basketLinesInBasket = new ArrayList<>();
        for (BasketLinePage basketLinePage : getBasketLinePages()) {
            basketLinesInBasket.add(basketLinePage.toBasketLine());
        }
        return basketLinesInBasket;
    }

    public BigDecimal getTotalSumOfBasketLines() {
        BigDecimal totalSum = convertIntToBigDecimal(0);

        for (BasketLine basketLine : getBasketLinesInBasket()) {
            totalSum = totalSum.add(basketLine.getTotalPrice());
        }
        return totalSum;
    }


    public boolean IsTotalPriceCorrect() {
        return Objects.equals(getTotalSumOfBasketLines(), basketSideGridPage.getProductsTotalSum());
    }


}
