package com.test.bdd.drivers;

import com.test.bdd.framework.Framework;

public class CustomDriver extends Framework {

    public static CustomDriver loadDriverByPlatform() {
        switch (Framework.getPlatform()) {
            case ANDROID:
                return new CustomAndroidDriver();
            case IOS:
                return new CustomiOSDriver();
            case WEB:
            default:
                return new CustomWebDriver();

        }
    }

    public void quit() {

    }


}

