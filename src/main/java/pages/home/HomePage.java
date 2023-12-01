package pages.home;

import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import pages.commons.TopGridPage;
import pages.commons.TopMenuPage;

public class HomePage extends BasePage {
    TopGridPage topGridPage;
    TopMenuPage topMenuPage;


    public HomePage(WebDriver driver) {
        super(driver);
    }
}
