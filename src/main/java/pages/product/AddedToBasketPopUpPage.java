package pages.product;

import helpers.PriceHelper;
import lombok.Getter;
import models.BasketLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;


public class AddedToBasketPopUpPage extends BasePage {
    public AddedToBasketPopUpPage(WebDriver driver) {
        super(driver);
    }

    @Getter
    @FindBy(xpath = "//*[@id='blockcart-modal']//*[@class='modal-dialog']//*[@class='modal-content']")
    private WebElement popUpWindow;

    @FindBy(css = "#myModalLabel")
    private WebElement successAlert;

    @FindBy(css = ".product-name")
    private WebElement addedProductName;


    @FindBy(xpath = "//*[@class='modal-body']//*[@class='product-price']")
    private WebElement addedProductPrice;

    @FindBy(xpath = "//*[@class='modal-body']//*[@class= 'product-quantity']/strong")
    private WebElement addedProductQuantity;


    @FindBy(css = ".cart-content>.cart-products-count")
    private WebElement productsCount;


    @FindBy(css = ".size>strong")
    private WebElement addedProductSize;


    @FindBy(css = ".product-total>.value")
    private WebElement productsTotalPrice;

    @FindBy(css = ".cart-content-btn>.btn-secondary")
    private WebElement continueShoppingBtn;

    @FindBy(css = ".cart-content-btn>a")
    private WebElement proceedToCheckOutBtn;

    public BasketLine toBasketLine() {
        return new BasketLine(new Product(this.addedProductName.getText(), getPriceFromElement(this.addedProductPrice)), getIntNumberFromText(this.addedProductQuantity), PriceHelper.getTotalPriceByQuantity(getPriceFromElement(this.addedProductPrice), getIntNumberFromText(this.addedProductQuantity)));

    }

    public void clickProceedToCheckout() {
        click(proceedToCheckOutBtn);
    }


    public void clickContinueShopping() {
        click(continueShoppingBtn);
    }

}
