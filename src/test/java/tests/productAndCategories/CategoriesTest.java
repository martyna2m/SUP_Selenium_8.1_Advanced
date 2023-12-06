package tests.productAndCategories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.commons.TopMenuPage;
import pages.home.HomePage;
import pages.product.CategoryPage;
import pages.product.SideFilterMenuPage;
import tests.base.TestBase;

import java.util.List;

public class CategoriesTest extends TestBase {

    @Test
    public void iterateThroughCategories() {
        openPage("homePage");

        List<String> categoryNames = at(TopMenuPage.class).getCategoryNames();

        for (String categoryName : categoryNames) {
            at(TopMenuPage.class).goToCategoryPage(categoryName);
            Assertions.assertThat(at(CategoryPage.class).getCategoryName()).isEqualTo(categoryName);
            System.out.println(at(CategoryPage.class).getCategoryName());
            Assertions.assertThat(at(CategoryPage.class).isSideFilterMenuDisplayed()).isTrue();
        }
    }
}
