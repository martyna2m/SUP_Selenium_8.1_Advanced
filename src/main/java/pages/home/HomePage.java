package pages.home;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import pages.categories.ProductMiniatureContainerPage;
import pages.commons.TopGridPage;
import pages.commons.TopMenuPage;

@Getter
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private TopGridPage topGridPage = new TopGridPage(driver);
    private TopMenuPage topMenuPage = new TopMenuPage(driver);
   private ProductMiniatureContainerPage productMiniatureContainerPage = new ProductMiniatureContainerPage(driver);

}



