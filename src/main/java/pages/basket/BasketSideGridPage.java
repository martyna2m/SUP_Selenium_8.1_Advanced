package pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;

public class BasketSideGridPage extends BasePage {

    @FindBy(css = "#cart-subtotal-products>.value")
    private WebElement totalOfProducts;
    @FindBy(css = "#cart-subtotal-shipping>.value")
    private WebElement totalOfShipping;
    @FindBy(css = ".cart-total>.value")
    private WebElement basketTotal;

    @FindBy(xpath = "//*[contains(@class,'checkout')]//a")
    private WebElement proceedToCheckoutBtn;


    public void proceedToCheckout() {
        click(proceedToCheckoutBtn);
    }

    public BasketSideGridPage(WebDriver driver) {
        super(driver);

    }

    public BigDecimal getBasketTotalSum() {
        return getPriceFromElement(basketTotal);
    }

    public BigDecimal getProductsTotalSum() {
        return getPriceFromElement(totalOfProducts);
    }

    public BigDecimal getShippingTotal() {
        return getPriceFromElement(totalOfShipping);
    }

}
