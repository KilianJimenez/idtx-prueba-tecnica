package com.test.bdd.framework;

import com.test.bdd.drivers.CustomWebDriver;
import com.test.bdd.setup.properties.ProfileManager;

import java.util.HashMap;
import java.util.Map;

public class Framework {

    private static final String BROWSER_OPTIONS = "browser_options";
    public static final String profile = "chrome_web_local";

    public static CustomWebDriver customWebDriver;

    public static Map<String, String> browserOptions() {
        String browserOptions = "";
        Map<String, String> options = new HashMap<>();

        browserOptions = ProfileManager.getProfileProperties(profile, BROWSER_OPTIONS);
        browserOptions = browserOptions.substring(0, browserOptions.length() - 2);
        browserOptions = browserOptions.substring(2);
        options.put(BROWSER_OPTIONS, browserOptions);
        return options;
    }


    public static void init() {
        customWebDriver = new CustomWebDriver();
    }


}
