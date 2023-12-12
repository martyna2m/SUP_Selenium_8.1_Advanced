package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryPage extends BasePage {

    @FindBy(css = "tbody tr")
    private List<WebElement> tableRows;

    public List<OrderRowPage> getRows() {
        List<OrderRowPage> tableOrderRows = new ArrayList<>();
        for (WebElement tableOrderRow : tableRows) {
            tableOrderRows.add(new OrderRowPage(driver, tableOrderRow));
        }
        return tableOrderRows;
    }

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }


}
