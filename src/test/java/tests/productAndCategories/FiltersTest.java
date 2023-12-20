package tests.productAndCategories;

import org.assertj.core.api.Assertions;
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
            Assertions.assertThat(productMiniature.getCurrentProductPrice())
                    .isGreaterThan(lowerPriceFilter)
                    .isLessThan(higherFilterPrice);
        }

        at(CategoryPage.class).deleteFilter("price");
        Assertions.assertThat(at(CategoryPage.class)
                        .getProductMiniatureContainerPage()
                        .getProductMiniatures()
                        .size())
                .isEqualTo(initialNumberOfProducts);

    }


}
