package tests;

import configuration.Browser;
import configuration.ConfigProperties;
import configuration.DriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class TestBase {
    protected static WebDriver driver;
    private static Browser activeBrowser;

    @BeforeAll
    static void setUpDriver() {
        DriverFactory driverFactory = new DriverFactory();
        activeBrowser = driverFactory.getActiveBrowser(ConfigProperties.config);
        driver = driverFactory.setDriver(activeBrowser);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
