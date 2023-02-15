package com.test.bdd.drivers;

import com.test.bdd.framework.Framework;
import com.test.bdd.setup.hooks.SetupHooks;
import com.test.bdd.setup.properties.ProfileManager;
import com.test.bdd.setup.properties.PropertyManager;
import com.test.bdd.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CustomAndroidDriver extends CustomDriver {

    private final String APP_PACKAGE = "";
    private final String APP_ACTIVITY = "";
    private final String AUTOMATION_NAME_CAPABILITY = "automationName";
    private final String PLATFORM_NAME = "Android";
    private final String AUTOMATION_NAME_VALUE = "uiautomator2";
    private static final String BS = "browserstack";
    private static final String BS_APP = "bsAppAndroid";
    private static final String BS_USER = "bsUser";
    private static final String BS_KEY = "bsKey";

    public static AppiumDriver driver;
    private static final String LOCAL_URL = "http://0.0.0.0:4723/wd/hub";

    public CustomAndroidDriver() {
        if (ProfileManager.getProfileProperties(Framework.profile, "location").equals(BS)) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserstack.user", PropertyManager.getProperty(BS_USER));
            caps.setCapability("browserstack.key", PropertyManager.getProperty(BS_KEY));
            caps.setCapability("app", PropertyManager.getProperty(BS_APP));
            caps.setCapability("os_version", "9.0");
            caps.setCapability("device", "Google Pixel 3");
            caps.setCapability("projectName", "projectName");
            caps.setCapability("buildName", "buildName - Android");
            caps.setCapability("sessionName", SetupHooks.sessionName);
            caps.setCapability("browserstack.geoLocation", "AT");
            caps.setCapability("browserstack.acceptInsecureCerts", "true");
            caps.setCapability("browserstack.appium_version", "1.21.0");
            caps.setCapability("browserstack.idleTimeout", "20");
            caps.setCapability("autoAcceptAlerts", "true");
            try {
                Utils.logger.info("Starting android driver - Browserstack");
                driver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hub"), caps);
                driver.rotate(ScreenOrientation.PORTRAIT);
                Utils.logger.info("Device Orientation: " + driver.getOrientation());
                Utils.logger.info("BrowserStack ID Sesion " + driver.getSessionId());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else {
            //Loads all capabilities
            Map<String, String> loadedCaps = profileCapabilities();

            URL url = null;
            try {
                url = new URL(LOCAL_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, loadedCaps.get("device_name"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, loadedCaps.get("platform_version"));
            capabilities.setCapability(MobileCapabilityType.UDID, loadedCaps.get("udid"));
            capabilities.setCapability(AndroidMobileCapabilityType.AVD, loadedCaps.get("avd"));
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
            capabilities.setCapability(AUTOMATION_NAME_CAPABILITY, AUTOMATION_NAME_VALUE);
            driver = new AndroidDriver<MobileElement>(url, capabilities);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
        }
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

}
