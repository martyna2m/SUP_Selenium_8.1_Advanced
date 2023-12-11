package tests.productAndCategories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.categories.CategoryPage;
import pages.categories.ProductMiniaturePage;
import tests.base.TestBase;

import java.util.List;

public class FiltersTest extends TestBase {
    @Test
    public void setFilters() throws InterruptedException {
        int lowerPriceFilter = Integer.parseInt(testDataProvider.getTestData("lowerPriceFilter"));
        int higherFilterPrice = Integer.parseInt(testDataProvider.getTestData("higherPriceFilter"));
        openPage("accessoriesPage");

        int initialNumberOfProducts = at(CategoryPage.class).getProductMiniatureContainerPage().getProductMiniatures().size();

        at(CategoryPage.class).getSideFilterMenuPage().setPriceFilter(13, 15);

        List<ProductMiniaturePage> filteredProductMiniatures = at(CategoryPage.class).getProductMiniatureContainerPage().getProductMiniatures();
        for (ProductMiniaturePage productMiniature : filteredProductMiniatures) {
            Assertions.assertThat(productMiniature.getCurrentProductPrice())
                    .isGreaterThan(convertIntToBigDecimal(lowerPriceFilter))
                    .isLessThan(convertIntToBigDecimal(higherFilterPrice));
        }

        at(CategoryPage.class).deleteFilter("price");
        Assertions.assertThat(at(CategoryPage.class).getProductMiniatureContainerPage().getProductMiniatures().size()).isEqualTo(initialNumberOfProducts);
    }


}
