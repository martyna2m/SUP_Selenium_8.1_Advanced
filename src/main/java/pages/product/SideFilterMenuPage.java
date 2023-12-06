package pages.product;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
@Getter
@Setter
public class SideFilterMenuPage extends BasePage {

    public SideFilterMenuPage(WebDriver driver) {
        super(driver);
    }
}
