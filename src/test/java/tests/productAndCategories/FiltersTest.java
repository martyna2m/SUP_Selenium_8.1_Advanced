package tests.productAndCategories;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import pages.categories.CategoryPage;
import pages.categories.ProductMiniaturePage;
import tests.base.TestBase;

import java.math.BigDecimal;
import java.util.List;

public class FiltersTest extends TestBase {
    @RepeatedTest(1)
    @Tag("productAndCategories")
    public void setFilters() {
        SoftAssertions softAssertions = new SoftAssertions();

        BigDecimal lowerPriceFilter = parseBigDecimal(testDataProvider.getTestData("lowerPriceFilter"));
        BigDecimal higherFilterPrice = parseBigDecimal(testDataProvider.getTestData("higherPriceFilter"));
        openPage("accessoriesPage");

        int initialNumberOfProducts = at(CategoryPage.class)
                .getProductMiniatureContainerPage()
                .getProductMiniatures().size();

        at(CategoryPage.class)
                .getSideFilterMenuPage()
                .setPriceFilters(lowerPriceFilter, higherFilterPrice);

        List<ProductMiniaturePage> filteredProductMiniatures = at(CategoryPage.class)
                .getProductMiniatureContainerPage()
                .getProductMiniatures();


        for (ProductMiniaturePage productMiniature : filteredProductMiniatures) {

            softAssertions.assertThat(productMiniature.getCurrentProductPrice())
                    .as("Checking that price of " + productMiniature.getName() + " matches filters.")
                    .isGreaterThan(lowerPriceFilter)
                    .isLessThan(higherFilterPrice);

        }

        at(CategoryPage.class).deleteFilter("price");
        softAssertions.assertThat(at(CategoryPage.class).getProductMiniatureContainerPage().getProductMiniatures().size())
                .as("Checking the number of products after clearing filters.")
                .isEqualTo(initialNumberOfProducts);

        softAssertions.assertAll();
    }


}
