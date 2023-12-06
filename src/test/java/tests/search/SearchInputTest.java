package tests.search;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.home.HomePage;
import pages.home.SearchResultsPage;
import tests.base.TestBase;

import java.util.List;
import java.util.Random;

public class SearchInputTest extends TestBase {

    @Test
    public void searchItem() {
        openPage("homePage");

        String productName = at(HomePage.class).getProductMiniatureContainerPage().getProductName(new Random().nextInt(8));

        at(HomePage.class)
                .getTopMenuPage()
                .typeInSearchInputAndSubmit(productName);

        List<String> resultsNames = at(SearchResultsPage.class).getProductMiniatureContainerPage().getProductNamesList();
        for (String name : resultsNames) {
            Assertions.assertThat(name.contains(productName)).isTrue();
        }

    }


    @Test
    public void verifySearchDropdown() {
        String searchedProduct = "HUMMINGBIRD";
        openPage("homePage");

        List<String> suggestedProductNames = at(HomePage.class)
                .getTopMenuPage()
                .typeInSearchInput(searchedProduct)
                .getSuggestedProductNames();

        for (String name : suggestedProductNames) {
            System.out.println(name);
            Assertions.assertThat(name.contains(searchedProduct)).isTrue();
        }

    }


}
