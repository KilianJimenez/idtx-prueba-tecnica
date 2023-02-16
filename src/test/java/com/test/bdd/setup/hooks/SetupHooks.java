package com.test.bdd.setup.hooks;

import com.test.bdd.framework.Framework;
import com.test.bdd.utils.Utils;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.BasicConfigurator;

import java.util.List;

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
        Utils.logger.info("Running : Scenario  -> " + scenario.getName());
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
        Framework.customWebDriver.quit();
    }

}
