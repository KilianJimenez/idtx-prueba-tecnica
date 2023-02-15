package com.test.bdd.setup.hooks;

import com.test.bdd.framework.Framework;
import com.test.bdd.utils.Utils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.BasicConfigurator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetupHooks {

    public static final String ANSI_MAGENTA_BOLD = "\u001b[35;1m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static String sessionName = "";


    public SetupHooks() {
        BasicConfigurator.configure();
    }

    @Before(order = 0)
    public void initDriver(Scenario scenario) {
        Utils.logger.info("Starting Driver");
        String rawFeatureName = scenario.getId();
        String rawScenarioName = scenario.getName();
        rawFeatureName = rawFeatureName.replace("%5B", "[");
        rawFeatureName = rawFeatureName.replace("%5D", "]");
        Pattern pattern = Pattern.compile("(\\[.*):");
        Matcher newRawFeatureName = pattern.matcher(rawFeatureName);
        //Set feature name
        if (newRawFeatureName.find()) {
            Utils.logger.info(ANSI_MAGENTA_BOLD + "Running : Feature  -> " + newRawFeatureName.group(1) + ANSI_RESET);
        } else {
            Utils.logger.info(ANSI_MAGENTA_BOLD + "Running : Feature  -> " + rawFeatureName.substring(28) + ANSI_RESET);
        }
        sessionName = newRawFeatureName.group(1) + " - " + rawScenarioName;
        Framework.init();
    }

    @Before(order = 1)
    public void logBeforeTestRun(Scenario scenario) {
        List<String> scenarioTags = (List<String>) scenario.getSourceTagNames();
        if (scenarioTags.size() > 0) {
            String message = "Executing scenarios with tag(s):";
            for (String scenarioTag : scenarioTags) {
                message += " " + scenarioTag;
            }
            Utils.logger.info(message);
        }
    }

    @After(order = 1)
    public void quitDriver() {
        Utils.logger.info("[Driver] Quit driver Appium");
        Framework.customDriver.quit();
    }

}
