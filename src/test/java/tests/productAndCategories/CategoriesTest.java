package tests.productAndCategories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.commons.TopMenuPage;
import pages.categories.CategoryPage;
import pages.categories.SideFilterMenuPage;
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
            Assertions.assertThat(at(SideFilterMenuPage.class).isSideFilterMenuDisplayed()).isTrue();

            int actualNumberOfProducts = at(CategoryPage.class).getProductMiniatureContainerPage().getProductMiniatures().size();

            String actualNumberOfProductsInfo = at(CategoryPage.class).getTotalNumberOfProductsInfo();
            String expectedNumberOfProductsInfo = "There are " + actualNumberOfProducts + " products.";

            Assertions.assertThat(expectedNumberOfProductsInfo).isEqualTo(actualNumberOfProductsInfo);
        }
    }

    @Test
    public void iterateThroughSubCategories() {
        openPage("homePage");


    }
}

