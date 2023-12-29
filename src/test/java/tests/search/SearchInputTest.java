package tests.search;

import helpers.PriceHelper;
import helpers.RandomHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import pages.categories.ProductMiniaturePage;
import pages.home.HomePage;
import pages.home.SearchResultsPage;
import providers.DataFaker;
import tests.base.TestBase;

import java.util.List;
import java.util.Random;

public class SearchInputTest extends TestBase {

    @RepeatedTest(1)
    @Tag("search")
    public void searchRandomProduct() {
        openPage("homePage");
        HomePage homePage = new HomePage(driver);

        List<ProductMiniaturePage> productsList = homePage.getProductMiniatureContainerPage().getProductMiniatures();
        int index = RandomHelper.getRandomIndex(productsList);
        String productName = homePage.getProductMiniatureContainerPage().getProductName(index);

        homePage.getTopMenuPage().typeInSearchInputAndSubmit(productName);

        List<String> resultsNames = at(SearchResultsPage.class).getProductMiniatureContainerPage().getProductNamesList();
        if (resultsNames.isEmpty()) {
            throw new IllegalArgumentException("The list of product names is empty");
        }
        for (String name : resultsNames) {
            Assertions.assertThat(name.contains(productName)).isTrue();
        }

    }

    @RepeatedTest(1)
    @Tag("search")
    public void verifySearchDropdown() {
        String searchedProduct = testDataProvider.getTestData("productName");
        openPage("homePage");

        List<String> suggestedProductNames = at(HomePage.class)
                .getTopMenuPage()
                .typeInSearchInput(searchedProduct)
                .getSuggestedProductNames();

        for (String name : suggestedProductNames) {
            Assertions.assertThat(name.contains(searchedProduct)).isTrue();
        }

    }


}
