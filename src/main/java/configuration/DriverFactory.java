package configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.Map;

public class DriverFactory {
    private WebDriver driver;


    public WebDriver setDriver(Browser activeBrowser) {
        switch (activeBrowser.name().toLowerCase()) {
            case "chrome":
                driver = setChromeDriver();
                break;
            case "edge":
                driver = setEdgeDriver();
                break;
            case "firefox":
                driver = setFireFoxDriver();
                break;
            case "ie":
                driver = setIeDriver();
        }

        return driver;
    }

    public Browser getActiveBrowser(Map<String, Object> data) {
        String browserName = (String) data.get("browser");
        return Browser.getBrowserByName(browserName);
    }


    private WebDriver setChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        return driver = new ChromeDriver(chromeOptions);
    }

    private WebDriver setEdgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("start-maximized");
        return driver = new EdgeDriver(edgeOptions);
    }

    private WebDriver setFireFoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("start-maximized");
        return driver = new FirefoxDriver(firefoxOptions);
    }


    private WebDriver setIeDriver() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.addCommandSwitches(("start-maximized"));
        return driver = new InternetExplorerDriver(internetExplorerOptions);
    }


}
