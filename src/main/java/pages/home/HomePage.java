package pages.home;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import pages.commons.TopGridPage;
import pages.commons.TopMenuPage;
import pages.categories.ProductMiniatureContainerPage;

@Getter
@Setter
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    TopGridPage topGridPage = new TopGridPage(driver);
    TopMenuPage topMenuPage = new TopMenuPage(driver);
    ProductMiniatureContainerPage productMiniatureContainerPage = new ProductMiniatureContainerPage(driver);

    }



