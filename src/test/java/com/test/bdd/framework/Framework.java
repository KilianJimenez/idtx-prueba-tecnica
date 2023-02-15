package com.test.bdd.framework;

import com.test.bdd.drivers.CustomDriver;
import com.test.bdd.setup.properties.Platform;
import com.test.bdd.setup.properties.ProfileManager;
import com.test.bdd.setup.properties.PropertyManager;

import java.util.HashMap;
import java.util.Map;

public class Framework {

    private static final String PROFILE = "profile";
    private static final String PLATFORM = "platform";
    private static final String UDID = "udid";
    private static final String DEVICE_NAME = "device_name";
    private static final String PLATFORM_VERSION = "platform_version";
    private static final String AVD = "avd";
    private static final String CHROME_OPTIONS = "chrome_options";
    private static final String IPHONE_SIMULATOR = "iphone_simulator";
    private static final String PIXEL_SIMULATOR = "pixel_simulator";
    private static final String CHROME_WEB_LOCAL = "chrome_web_local";

    public static CustomDriver customDriver;
    private static Platform platform;
    public static String profile;

    public static Map<String, String> profileCapabilities() {
        String udid = "";
        String device_name = "";
        String platform_version = "";
        String avd = "";
        String chrome_options = "";
        Map<String, String> capabilities = new HashMap<>();

        switch (platform) {
            case IOS:
                if (profile.equals(IPHONE_SIMULATOR)) {
                    udid = ProfileManager.getProfileProperties(profile, UDID);
                    device_name = ProfileManager.getProfileProperties(profile, DEVICE_NAME);
                    platform_version = ProfileManager.getProfileProperties(profile, PLATFORM_VERSION);
                }
                capabilities.put(UDID, udid);
                capabilities.put(DEVICE_NAME, device_name);
                capabilities.put(PLATFORM_VERSION, platform_version);
                break;
            case ANDROID:
                if (profile.equals(PIXEL_SIMULATOR)) {
                    udid = ProfileManager.getProfileProperties(profile, UDID);
                    device_name = ProfileManager.getProfileProperties(profile, DEVICE_NAME);
                    platform_version = ProfileManager.getProfileProperties(profile, PLATFORM_VERSION);
                    avd = ProfileManager.getProfileProperties(profile, AVD);
                }
                capabilities.put(UDID, udid);
                capabilities.put(DEVICE_NAME, device_name);
                capabilities.put(PLATFORM_VERSION, platform_version);
                capabilities.put(AVD, avd);
                break;
            case WEB:
                chrome_options = ProfileManager.getProfileProperties(profile, CHROME_OPTIONS);
                chrome_options = chrome_options.substring(0, chrome_options.length() - 2);
                chrome_options = chrome_options.substring(2);
                capabilities.put(CHROME_OPTIONS, chrome_options);
                break;
            default:
                throw new IllegalArgumentException("platform " + platform + " does not exist");
        }
        return capabilities;
    }


    public static void init() {
        profile = PropertyManager.getProperty(PROFILE);
        platform = Platform.valueOf(ProfileManager.getProfileProperties(profile, PLATFORM));
        customDriver = CustomDriver.loadDriverByPlatform();
    }

    public static Platform getPlatform() {
        return platform;
    }


}
