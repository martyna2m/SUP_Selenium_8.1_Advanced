package tests.productAndCategories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import pages.categories.CategoryPage;
import pages.categories.ProductMiniaturePage;
import tests.base.TestBase;

import java.util.List;

public class FiltersTest extends TestBase {
    @RepeatedTest(1)
    @Tag("productAndCategories")
    @Tag("yaml5")
    public void setFilters() {
        int lowerPriceFilter = parseInt(testDataProvider.getTestData("lowerPriceFilter"));
        int higherFilterPrice = parseInt(testDataProvider.getTestData("higherPriceFilter"));
        openPage("accessoriesPage");

        int initialNumberOfProducts = at(CategoryPage.class)
                .getProductMiniatureContainerPage()
                .getProductMiniatures().size();

        at(CategoryPage.class)
                .getSideFilterMenuPage()
                .setPriceFilters(13, 15);

        List<ProductMiniaturePage> filteredProductMiniatures = at(CategoryPage.class)
                .getProductMiniatureContainerPage()
                .getProductMiniatures();


        for (ProductMiniaturePage productMiniature : filteredProductMiniatures) {
            Assertions.assertThat(productMiniature.getCurrentProductPrice())
                    .isGreaterThan(convertIntToBigDecimal(lowerPriceFilter))
                    .isLessThan(convertIntToBigDecimal(higherFilterPrice));
        }

        at(CategoryPage.class).deleteFilter("price");
        Assertions.assertThat(at(CategoryPage.class)
                        .getProductMiniatureContainerPage()
                        .getProductMiniatures()
                        .size())
                .isEqualTo(initialNumberOfProducts);

    }


}
