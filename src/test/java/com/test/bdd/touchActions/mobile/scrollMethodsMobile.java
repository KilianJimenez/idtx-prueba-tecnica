package com.test.bdd.touchActions.mobile;

import com.test.bdd.drivers.CustomAndroidDriver;
import com.test.bdd.drivers.CustomDriver;
import com.test.bdd.drivers.CustomiOSDriver;
import com.test.bdd.framework.Framework;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class scrollMethodsMobile extends CustomDriver {
    //public static MobileDriver driver;

    public static MobileDriver getDriver() {
        switch (Framework.getPlatform()) {
            case ANDROID:
            default:
                return CustomAndroidDriver.driver;

            case IOS:
                return CustomiOSDriver.driver;
        }
    }

    public static void scrollDown() {
        Dimension dimension = getDriver().manage().window().getSize();
        int vertical_anchor = (int) (dimension.getWidth() * 0.5);
        int scrollStart = (int) (dimension.getHeight() * 0.7);
        int scrollEnd = (int) (dimension.getHeight() * 0.3);
        new TouchAction(getDriver())
                .press(PointOption.point(vertical_anchor, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(vertical_anchor, scrollEnd))
                .release().perform();
    }

    public static void scrollDownIOS() {
        Dimension dimension = getDriver().manage().window().getSize();
        int vertical_anchor = (int) (dimension.getWidth() * 0.5);
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.1);
        new TouchAction(getDriver())
                .press(PointOption.point(vertical_anchor, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(vertical_anchor, scrollEnd))
                .release().perform();
    }

}
