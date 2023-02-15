package com.test.bdd.drivers;

import com.test.bdd.framework.Framework;
import com.test.bdd.setup.hooks.SetupHooks;
import com.test.bdd.setup.properties.ProfileManager;
import com.test.bdd.setup.properties.PropertyManager;
import com.test.bdd.stepdefs.LoginMainSteps;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CustomWebDriver extends CustomDriver {
    static Logger logger = Logger.getLogger(LoginMainSteps.class.getName());

    private static final String DRIVERS_PATH = "src//test//resources//drivers//";
    private static final String CHROME_OPTIONS = "chrome_options";
    private static final Boolean IS_WINDOWS = System.getProperty("os.name").startsWith("Windows");
    private static final String BS = "browserstack";
    private static final String BS_USER = "bsUser";
    private static final String BS_KEY = "bsKey";

    public static WebDriver driver;

    public CustomWebDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();

        if (ProfileManager.getProfileProperties(Framework.profile, "location").equals(BS)) {

            String profileBrowser = ProfileManager.getProfileProperties(Framework.profile, "browser");
            String profileResolution = ProfileManager.getProfileProperties(Framework.profile, "resolution");
            if (profileBrowser.equals("safari")) {
                caps.setCapability("platform", "Mac");
            } else {
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
            }
            caps.setCapability("browser_version", "latest");
            caps.setCapability("browserstack.local", "false");
            caps.setCapability("browserstack.selenium_version", "3.141.59");
            caps.setCapability("resolution", profileResolution);
            caps.setCapability("browserName", profileBrowser);
            caps.setCapability("browserstack.user", PropertyManager.getProperty(BS_USER));
            caps.setCapability("browserstack.key", PropertyManager.getProperty(BS_KEY));
            caps.setCapability("projectName", "Onboarding");
            caps.setCapability("buildName", "Onboarding Test - Web");
            caps.setCapability("sessionName", SetupHooks.sessionName);
            //TODO: Select country --> for now austria hardcoded
            //caps.setCapability("browserstack.geoLocation", "AT");
            String URL = "https://" + BS_USER + ":" + BS_KEY + "@hub.browserstack.com/wd/hub";

            try {
                java.net.URL browserStackUrl = new URL(URL);
                driver = new RemoteWebDriver(browserStackUrl, caps);
            } catch (Exception e) {
                logger.info(e);
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } else {
            Map<String, String> loadedCaps = profileCapabilities();
            List<String> chrome_options = new ArrayList<>(Arrays.asList(loadedCaps.get(CHROME_OPTIONS).split("\"([^\"]*)\"")));
            System.setProperty("webdriver.chrome.driver",
                    DRIVERS_PATH + (IS_WINDOWS ? "chromedriver.exe" : "chromedriver_mac"));
            ChromeOptions chromeOptions = new ChromeOptions();
            for (String option : chrome_options) {
                chromeOptions.addArguments(option);
            }
            this.driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
        }
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
