package pages.product;

import lombok.Getter;
import lombok.Setter;
import models.BasketLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

import java.util.List;

@Getter
@Setter
public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@id='main']//h1")
    private WebElement nameLabel;

    @FindBy(xpath = "//*[@class='modal-body']//*[@class='regular-price']")
    private WebElement regularPrice;

    @FindBy(css = ".current-price>span")
    private WebElement currentPrice;

    @FindBy(css = "#group_1")
    private WebElement sizeSelectBtn;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = ".touchspin-up")
    private WebElement quantityUpBtn;

    @FindBy(css = ".touchspin-down")
    private WebElement quantityDownBtn;

    @FindBy(css = ".add-to-cart")
    private WebElement addToBasketBtn;

    @FindBy(xpath = "(//a[@itemprop ='item'])[1]")
    private WebElement homeBtn;

    public ProductDetailsPage selectSize(String size) {
        Select sizeOptions = new Select(sizeSelectBtn);
        sizeOptions.selectByValue(size.toUpperCase());
        return this;
    }

    public void returnToHomePage(){
        click(homeBtn);
    }

    public ProductDetailsPage selectQuantity(int expectedQuantity) {
        for (int i = 1; i < expectedQuantity; i++) {
            click(getQuantityDirection(expectedQuantity));
        }
        return this;

    }

    private WebElement getQuantityDirection(int expectedQuantity) {
        int currentQuantity = getIntNumberFromValue(this.quantityInput);
        if (currentQuantity < expectedQuantity) {
            return this.quantityUpBtn;
        } else return this.quantityDownBtn;
    }

    public BasketLine addProductToBasket() {
        if (isProductInBasket()) {
            for (BasketLine basketLine : basket.getExpectedBasketLines()) {
                if ((basketLine.getProduct().getName()).equals(getText(nameLabel))) {
                    basketLine.increaseQuantityAndTotalPrice(getIntNumberFromValue(quantityInput),getTotalPrice(getPrice(currentPrice), getIntNumberFromValue(quantityInput)) );
                    click(addToBasketBtn);
                    return basketLine;
                }
            }
        } else {
            BasketLine expectedBasketLine = new BasketLine(new Product(getText(nameLabel), getPrice(currentPrice)), getIntNumberFromValue(quantityInput), getTotalPrice(getPrice(currentPrice), getIntNumberFromValue(quantityInput)));
            basket.addBasketLineToBasket(expectedBasketLine);
            click(addToBasketBtn);
            return expectedBasketLine;
        }
        return null;
    }


    public boolean isProductInBasket() {
        boolean isAdded = false;
        for (BasketLine basketLine : basket.getExpectedBasketLines()) {
            if ((basketLine.getProduct().getName()).equals(getText(this.nameLabel))) {
                isAdded = true;
            }
        }
        return isAdded;
    }


}
