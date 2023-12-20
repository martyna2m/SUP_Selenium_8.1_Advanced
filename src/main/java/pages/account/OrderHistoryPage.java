package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class OrderHistoryPage extends BasePage {

    @FindBy(css = "tbody tr")
    private List<WebElement> tableRows;

    public List<OrderRowPage> getRows() {
        return tableRows.stream()
                .map(tableOrderRow -> new OrderRowPage(driver, tableOrderRow))
                .collect(Collectors.toList());
    }

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }


}
