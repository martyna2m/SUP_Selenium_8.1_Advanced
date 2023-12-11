package pages.account;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
@Getter

public class OrderDetails extends BasePage {

    @FindBy(css = "#delivery-address>address")
    private WebElement deliveryAddressSection;

      @FindBy(css = "#invoice-address>address")
    private WebElement invoiceAddressSection;

    public OrderDetails(WebDriver driver) {
        super(driver);
    }


    public String getNameFromDeliveryAddress(){
       return getText(deliveryAddressSection);
    }
     public String getNameFromInvoiceAddress(){
       return getText(invoiceAddressSection);
    }


}
