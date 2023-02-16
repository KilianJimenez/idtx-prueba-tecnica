package com.test.bdd.drivers;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.test.bdd.framework.Framework.browserOptions;

public class CustomWebDriver {

    private static final String URL = "https://www.google.es/";
    private static final String GOOGLE_COOKIES_BANNER_ID = "L2AGLb";
    private static final String DRIVERS_PATH = "src//test//resources//drivers//";
    private static final String BROWSER_OPTIONS = "browser_options";
    private static final Boolean IS_WINDOWS = System.getProperty("os.name").startsWith("Windows");

    public static WebDriver driver;

    public CustomWebDriver() {
        Map<String, String> loadedOptions = browserOptions();
        List<String> options = new ArrayList<>(Arrays.asList(loadedOptions.get(BROWSER_OPTIONS).split("\"([^\"]*)\"")));
        System.setProperty("webdriver.chrome.driver",
                DRIVERS_PATH + (IS_WINDOWS ? "chromedriver.exe" : "chromedriver_mac"));
        ChromeOptions chromeOptions = new ChromeOptions();
        for (String option : options) {
            chromeOptions.addArguments(option);
        }
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.get(URL);
        acceptGoogleCookies();
    }

    public static void acceptGoogleCookies() {
        try {
            driver.findElement(By.id(GOOGLE_COOKIES_BANNER_ID)).click();
        } catch (WebDriverException ignored) {} // Cookies are already accepted
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
