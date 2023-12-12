package tests.productAndCategories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.WebElement;
import pages.categories.CategoryPage;
import pages.categories.SideFilterMenuPage;
import pages.commons.TopMenuPage;
import tests.base.TestBase;

import java.util.List;

public class CategoriesTest extends TestBase {

    @RepeatedTest(3)
    public void iterateThroughCategories() {
        openPage("homePage");
        List<String> categoryNames = at(TopMenuPage.class).getCategoryNames();
        for (String categoryName : categoryNames) {
            at(TopMenuPage.class).goToCategoryPageWithName(categoryName);
            assertCategoryPage(categoryName);
        }
    }

    @RepeatedTest(3)
    public void iterateThroughSubCategories() {
        openPage("homePage");
        List<String> categoryNames = at(TopMenuPage.class).getCategoryNames();

        for (String categoryName : categoryNames) {
            at(TopMenuPage.class).goToCategoryPageWithName(categoryName);
            List<WebElement> subCategories = at(SideFilterMenuPage.class).getSubCategories();
            List<String> subCategoryNames = at(SideFilterMenuPage.class).getSubcategoryNames(subCategories);

            for (int i = 0; i < subCategories.size(); i++) {
                at(SideFilterMenuPage.class).goToSubCategoryPage(subCategories.get(i));
                assertCategoryPage(subCategoryNames.get(i));
                driver.navigate().back();
            }


        }

    }

    private void assertCategoryPage(String categoryName) {
        Assertions.assertThat(at(CategoryPage.class)
                        .getCategoryPageName())
                .isEqualTo(categoryName);

        Assertions.assertThat(at(SideFilterMenuPage.class)
                        .isSideFilterMenuDisplayed())
                .isTrue();

        int actualNumberOfProducts =
                at(CategoryPage.class)
                        .getProductMiniatureContainerPage()
                        .getProductMiniatures().size();

        String actualNumberOfProductsInfo = at(CategoryPage.class).getTotalNumberOfProductsInfo();
        String expectedNumberOfProductsInfo = "There are " + actualNumberOfProducts + " products.";

        Assertions.assertThat(expectedNumberOfProductsInfo)
                .isEqualTo(actualNumberOfProductsInfo);

    }


}






