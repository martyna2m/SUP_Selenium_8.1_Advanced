package pages.home;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import pages.categories.ProductMiniatureContainerPage;
import pages.commons.TopGridPage;
import pages.commons.TopMenuPage;

@Getter
public class SearchResultsPage extends BasePage {

    private TopGridPage topGridPage;
    private TopMenuPage topMenuPage;
    private ProductMiniatureContainerPage productMiniatureContainerPage = new ProductMiniatureContainerPage(driver);

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }


}
