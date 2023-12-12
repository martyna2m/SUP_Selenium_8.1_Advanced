package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class OrderRowPage extends BasePage {

    @FindBy(css = "th")
    private WebElement orderNumber;

    @FindBy(xpath = "./td[1]")
    private WebElement orderDate;
    @FindBy(xpath = "./td[2]")
    private WebElement totalPrice;

    @FindBy(xpath = "./td/span")
    private WebElement invoiceStatus;
    @FindBy(xpath = "./td/a[contains(text(), 'Details')]")
    private WebElement orderDetailsBtn;
    @FindBy(xpath = "./td/a[contains(text(), 'Reorder')]")
    private WebElement reorderBtn;


    public OrderRowPage(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    public String getOrderNumber() {
        return getText(orderNumber);
    }

    public String getOrderDate() {
        return getText(orderDate);
    }

    public String getOrderTotalPrice() {
        return getText(totalPrice);
    }

    public String getInvoiceStatus() {
        return getText(invoiceStatus);
    }

    public String getHistoricalOrderDetails() {
        return "Reference number: " + getOrderNumber() + ", totalPrice: " + getOrderTotalPrice() + ", orderDate: " + getOrderDate();
    }

    public void goToDetails() {
        click(orderDetailsBtn);
    }

}
