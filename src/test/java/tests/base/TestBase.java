package tests.base;

import configuration.Browser;
import configuration.PropertiesFromYaml;
import configuration.DriverFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import providers.TestDataProvider;
import providers.UrlProvider;

import java.util.Objects;

public class TestBase {

    protected static WebDriver driver;
    protected static Browser activeBrowser;
    protected static DriverFactory driverFactory = new DriverFactory();

    protected static TestDataProvider testDataProvider = new TestDataProvider();


    @BeforeAll
    static void setUpDriver() {
        activeBrowser = driverFactory.getActiveBrowser(PropertiesFromYaml.config);
        driver = driverFactory.setDriver(activeBrowser);

    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }


    @SneakyThrows
    public <T extends BasePage> T at(Class<T> pageType) {
        return pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }


    public void openPage(String urlKey) {
      UrlProvider urlProvider = new UrlProvider();
       String selectedUrl = urlProvider.getUrl(urlKey);
        driver.get(selectedUrl);
    }


}
