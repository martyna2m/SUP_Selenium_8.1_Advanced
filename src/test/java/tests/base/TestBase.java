package tests.base;

import configuration.Browser;
import configuration.DriverFactory;
import configuration.PropertiesFromYaml;
import lombok.SneakyThrows;
import models.Basket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import providers.TestDataProvider;
import providers.UrlProvider;

import java.math.BigDecimal;

public class TestBase {

    protected WebDriver driver;
    protected Browser activeBrowser;
    protected DriverFactory driverFactory = new DriverFactory();

    protected TestDataProvider testDataProvider = new TestDataProvider();


    @BeforeEach
    void setUpDriver() {
        activeBrowser = driverFactory.getActiveBrowser(PropertiesFromYaml.config);
        driver = driverFactory.setDriver(activeBrowser);

    }

    @AfterEach
    void tearDown() {
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

    public BigDecimal convertIntToBigDecimal(int intValue) {
        return BigDecimal.valueOf(intValue).setScale(2);

    }

    public int parseInt(String text) {
        return Integer.parseInt(text);

    }

    public BigDecimal parseBigDecimal(String text){
        return new BigDecimal(text).setScale(2);
    }


}
