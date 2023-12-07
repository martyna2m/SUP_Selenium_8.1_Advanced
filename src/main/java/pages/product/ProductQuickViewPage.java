package pages.product;

import lombok.Getter;
import lombok.Setter;
import models.Basket;
import models.BasketLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

@Getter
@Setter
public class ProductQuickViewPage extends BasePage {
    public ProductQuickViewPage(WebDriver driver) {
        super(driver);
    }
//
//    @FindBy(xpath = "//*[@class='modal-body']//h1")
//    private WebElement nameLabel;
//
//    @FindBy(xpath = "//*[@class='modal-body']//*[@class='regular-price']")
//    private WebElement regularPrice;
//
//    @FindBy(xpath = "//*[@class='modal-body']//*[@itemprop='price']")
//    private WebElement currentPrice;
//
//    @FindBy(css = "#group_1")
//    private WebElement sizeSelectBtn;
//
//    @FindBy(css = "#quantity_wanted")
//    private WebElement quantityInput;
//
//    @FindBy(css = ".touchspin-up")
//    private WebElement quantityUpBtn;
//
//    @FindBy(css = ".touchspin-down")
//    private WebElement quantityDownBtn;
//
//    @FindBy(css = ".add-to-cart")
//    private WebElement addToBasketBtn;
//
//
//    public ProductQuickViewPage selectSize(String size) {
//        Select sizeOptions = new Select(sizeSelectBtn);
//        sizeOptions.selectByValue(size.toUpperCase());
//        return this;
//    }
//
//    public ProductQuickViewPage selectQuantity(int expectedQuantity) {
//        for (int i = 1; i < expectedQuantity; i++) {
//            click(getQuantityDirection(expectedQuantity));
//        }
//        return this;
//
//    }
//
//    private WebElement getQuantityDirection(int expectedQuantity) {
//        int currentQuantity = getIntNumber(this.quantityInput);
//        if (currentQuantity < expectedQuantity) {
//            return this.quantityUpBtn;
//        } else return this.quantityDownBtn;
//    }
//
//    public BasketLine addProductToBasket() {
//        click(this.addToBasketBtn);
//        BasketLine newBasketLine = new BasketLine(new Product(this.nameLabel.getText(), getPrice(this.currentPrice)), getIntNumber(this.quantityInput), getTotalPrice(getPrice(this.currentPrice), getIntNumber(this.quantityInput)));
//        Basket basket = new Basket();
//        basket.addBasketLine(newBasketLine);
//        return newBasketLine;
//    }

}
