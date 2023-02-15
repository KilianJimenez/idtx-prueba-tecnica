package com.test.bdd.drivers;

import com.test.bdd.setup.hooks.SetupHooks;
import com.test.bdd.setup.properties.ProfileManager;
import com.test.bdd.setup.properties.PropertyManager;
import com.test.bdd.framework.Framework;
import com.test.bdd.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CustomiOSDriver extends CustomDriver {

    private static final String BUNDLEID = "";
    private static final String PLATFORM_NAME = "ios";
    private static final String AUTOMATION_NAME = "XCUITest";
    private static final String APPLICATION_NAME = "test";
    private static final String BS = "browserstack";
    private static final String BS_USER = "bsUser";
    private static final String BS_KEY = "bsKey";
    private static final String BS_APP = "bsAppiOS";


    public static AppiumDriver driver;
    private static final String LOCAL_URL = "http://127.0.0.1:4723/wd/hub";


    public CustomiOSDriver() {
        if (ProfileManager.getProfileProperties(Framework.profile, "location").equals(BS)) {

            String profileVersion = ProfileManager.getProfileProperties(Framework.profile, "version");
            String profileDevice = ProfileManager.getProfileProperties(Framework.profile, "device");
            Utils.logger.info("Executing test on: " + profileDevice + " OS version: " + profileVersion);
            DesiredCapabilities caps = new DesiredCapabilities();

            // Set your access credentials
            caps.setCapability("browserstack.user", PropertyManager.getProperty(BS_USER));
            caps.setCapability("browserstack.key", PropertyManager.getProperty(BS_KEY));

            // Set URL of the application under test
            caps.setCapability("app", PropertyManager.getProperty(BS_APP));

            caps.setCapability("noReset", true);
            caps.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);

            // Specify device and os_version for testing
            caps.setCapability("os_version", profileVersion);
            caps.setCapability("device", profileDevice);
            caps.setCapability("projectName", "projectName");
            caps.setCapability("buildName", "Test - IOS");
            caps.setCapability("sessionName", SetupHooks.sessionName);
            caps.setCapability("browserstack.geoLocation", "AT");

            // General Capabilities
            caps.setCapability("browserstack.acceptInsecureCerts", "true");
            caps.setCapability("browserstack.appium_version", "1.21.0");
            caps.setCapability("browserstack.idleTimeout", "20");
            caps.setCapability("autoAcceptAlerts", "true");
            try {
                Utils.logger.info("Starting iOS driver - Browserstack");
                driver = new IOSDriver(new URL("http://hub.browserstack.com/wd/hub"), caps);
                //driver.setSetting("snapshotMaxDepth", 61);
                Utils.logger.info("Device Orientation: " + driver.getOrientation());
                Utils.logger.info("BrowserStack ID Sesion: " + driver.getSessionId());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else {
            URL url = null;
            try {
                url = new URL(LOCAL_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            DesiredCapabilities iOScapabilities = new DesiredCapabilities();
            Map<String, String> loadedCaps = Framework.profileCapabilities();
            iOScapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
            iOScapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, loadedCaps.get("platform_version"));
            iOScapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, loadedCaps.get("device_name"));
            iOScapabilities.setCapability(MobileCapabilityType.UDID, loadedCaps.get("udid"));
            iOScapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, BUNDLEID);
            iOScapabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, true);
            iOScapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
            iOScapabilities.setCapability("noReset", true);
            iOScapabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, APPLICATION_NAME);
            driver = new IOSDriver<MobileElement>(url, iOScapabilities);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
        }
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

}
