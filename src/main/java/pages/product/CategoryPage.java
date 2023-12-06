package pages.product;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
@Getter
@Setter
public class CategoryPage extends BasePage{

    SideFilterMenuPage sideFilterMenuPage = new SideFilterMenuPage(driver);


    public CategoryPage(WebDriver driver) {
        super(driver);
    }
}
