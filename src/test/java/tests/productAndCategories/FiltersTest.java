package tests.productAndCategories;

import org.junit.jupiter.api.Test;
import pages.product.CategoryPage;
import pages.product.SideFilterMenuPage;
import tests.base.TestBase;

public class FiltersTest extends TestBase {
    @Test
    public void setFilters() {

        openPage("accessoriesPage");
        int initialNumberOfProducts = at(CategoryPage.class).getProductMiniatureContainerPage().getProductMiniatures().size();

//        at(SideFilterMenuPage.class).setPriceFilter(13, 15);


    }


}
