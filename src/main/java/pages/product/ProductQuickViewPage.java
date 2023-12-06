package pages.product;

import com.fasterxml.jackson.core.io.BigDecimalParser;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import lombok.Getter;
import lombok.Setter;
import models.BasketLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductQuickViewPage extends BasePage {
    public ProductQuickViewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='modal-body']//h1")
    private WebElement nameLabel;

    @FindBy(xpath = "//*[@class='modal-body']//*[@class='regular-price']")
    private WebElement regularPrice;

    @FindBy(xpath = "//*[@class='modal-body']//*[@itemprop='price']")
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


    public ProductQuickViewPage selectSize(String size) {
        Select sizeOptions = new Select(sizeSelectBtn);
        sizeOptions.selectByValue(size.toUpperCase());
        return this;
    }

    public void selectQuantity(int expectedQuantity) {
        for (int i = 1; i < expectedQuantity; i++) {
            click(getQuantityDirection(expectedQuantity));
        }

    }

    private WebElement getQuantityDirection(int expectedQuantity) {
        int currentQuantity = getIntNumber(quantityInput);
        if (currentQuantity < expectedQuantity) {
            return quantityUpBtn;
        } else return quantityDownBtn;
    }

    public BasketLine addProductToBasket(){
       click(addToBasketBtn);
       return new BasketLine(new Product(nameLabel.getText(), getPrice(currentPrice)), getIntNumber(quantityInput), getTotalPrice(getPrice(currentPrice),getIntNumber(quantityInput)));

    }

}
