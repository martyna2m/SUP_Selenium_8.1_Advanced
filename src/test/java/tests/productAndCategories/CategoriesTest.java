package tests.productAndCategories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.WebElement;
import pages.categories.CategoryPage;
import pages.categories.SideFilterMenuPage;
import pages.commons.TopMenuPage;
import tests.base.TestBase;

import java.util.List;

public class CategoriesTest extends TestBase {

    @RepeatedTest(1)
    @Tag("productAndCategories")
    public void iterateThroughCategories() {
        openPage("homePage");
        List<String> categoryNames = at(TopMenuPage.class).getCategoryNames();
        for (String categoryName : categoryNames) {
            at(TopMenuPage.class).goToCategoryPageWithName(categoryName);
            assertCategoryPage(categoryName);
        }
    }

    @RepeatedTest(1)
    @Tag("productAndCategories")
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

        int numberOfDisplayedProducts =
                at(CategoryPage.class)
                        .getProductMiniatureContainerPage()
                        .getProductMiniatures().size();

        String actualNumberOfProductsInfo = at(CategoryPage.class).getTotalNumberOfProductsInfo();
        String expectedNumberOfProductsInfo = getExpectedNumberOfProductsInfo(numberOfDisplayedProducts);

        Assertions.assertThat(expectedNumberOfProductsInfo).isEqualTo(actualNumberOfProductsInfo);

    }

private String getExpectedNumberOfProductsInfo(int number){
        if(number ==1){
            return "There is 1 product.";
        }
        return "There are " + number + " products.";
}

}






