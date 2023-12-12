package pages.home;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;
import pages.commons.TopGridPage;
import pages.commons.TopMenuPage;
import pages.categories.ProductMiniatureContainerPage;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchResultsPage extends BasePage {

    TopGridPage topGridPage;
    TopMenuPage topMenuPage;
    ProductMiniatureContainerPage productMiniatureContainerPage = new ProductMiniatureContainerPage(driver);

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }



}
