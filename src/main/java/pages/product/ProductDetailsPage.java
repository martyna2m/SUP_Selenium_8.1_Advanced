package pages.product;

import lombok.Getter;
import models.Basket;
import models.BasketLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;


public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

   @Getter
    private AddedToBasketPopUpPage addedToBasketPopUpPage = new AddedToBasketPopUpPage(driver);

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

    public void returnToHomePage() {
        click(homeBtn);
    }

    public ProductDetailsPage selectQuantity(int expectedQuantity) {
        for (int i = 1; i < expectedQuantity; i++) {
            click(getQuantityDirection(expectedQuantity));
        }
        return this;

    }

    public BasketLine addProductToBasket(Basket basket){
        BasketLine basketLine = basket.addBasketLineToBasket(getText(this.nameLabel), getPriceFromElement(currentPrice),getIntNumberFromValue(quantityInput));
        click(addToBasketBtn);
        waitForPopUp();
        return basketLine;
    }
    private WebElement getQuantityDirection(int expectedQuantity) {
        int currentQuantity = getIntNumberFromValue(this.quantityInput);
        if (currentQuantity < expectedQuantity) {
            return this.quantityUpBtn;
        } else return this.quantityDownBtn;
    }

    private void waitForPopUp(){
        defaultWait.until(ExpectedConditions.visibilityOf(addedToBasketPopUpPage.getPopUpWindow()));
    }

//    public BasketLine addProductToBasket(Basket basket) {
////        if (isProductInBasket(getText(this.nameLabel), basket)) {
////            BasketLine exisitingBasketLine = findProductInBasket(getText(this.nameLabel), basket);
////            exisitingBasketLine.increaseQuantityAndTotalPrice(getIntNumberFromValue(quantityInput), getTotalPriceByQuantity(getPriceFromElement(currentPrice), getIntNumberFromValue(quantityInput)));
////            click(addToBasketBtn);
////            waitForPopUp();
////            return exisitingBasketLine;
////
////        } else {
////            BasketLine expectedBasketLine = new BasketLine(new Product(getText(nameLabel), getPriceFromElement(currentPrice)), getIntNumberFromValue(quantityInput), getTotalPriceByQuantity(getPriceFromElement(currentPrice), getIntNumberFromValue(quantityInput)));
////            basket.addBasketLineToBasket(expectedBasketLine);
////            click(addToBasketBtn);
////            waitForPopUp();
////            return expectedBasketLine;
//        }
//    }


//    public boolean isProductInBasket(String productName, Basket basket) {
//        return findProductInBasket(productName, basket) != null;
//    }


//    public BasketLine findProductInBasket(String productName, Basket basket) {
//        for (BasketLine basketLine : basket.getExpectedBasketLines()) {
//            if ((basketLine.getProduct().getName()).equals(productName)) {
//                return basketLine;
//            }
//        }
//        return null;
//
//    }



//    public BasketLine createExpectedBasketLine(){
//       return new BasketLine(new Product(getText(nameLabel), getPriceFromElement(currentPrice)), getIntNumberFromValue(quantityInput), PriceHelper.getTotalPriceByQuantity(getPriceFromElement(currentPrice), getIntNumberFromValue(quantityInput)));
//    }
}
