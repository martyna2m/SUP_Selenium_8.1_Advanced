package tests;

import org.junit.jupiter.api.Test;
import pages.commons.TopMenuPage;
import tests.base.TestBase;

public class SampleTest extends TestBase {


    @Test
    public void getTitle (){
        openPage("homePage");
        TopMenuPage topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToSubCategoryPage(0,0);


    }
}
