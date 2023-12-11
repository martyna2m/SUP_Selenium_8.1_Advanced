package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderConfirmedPage extends BasePage {


    @FindBy(xpath = "//*[@id = 'order-details']//li[contains(text(), 'reference')]")
    private WebElement referenceNumber;

    @FindBy(css = ".price>strong")
    private WebElement paymentAmount;


    public OrderConfirmedPage(WebDriver driver) {
        super(driver);
    }

    public String getReferenceNumber() {
        return getText(referenceNumber).replace("Order reference: ", "");
    }

    public String getPaymentAmount() {
        return getText(paymentAmount);
    }

    public String getOrderDate(){
        LocalDate orderDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return orderDate.format(formatter);
    }

    public String getOrderDetails(){
        String orderInfo = "Reference number: " + getReferenceNumber() + ", totalPrice: " + getPaymentAmount() + ", orderDate: " + getOrderDate();
        return orderInfo;
    }

}
