package com.test.bdd.setup.hooks;

import com.test.bdd.framework.Framework;
import com.test.bdd.utils.Utils;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.BasicConfigurator;

import java.util.List;

public class SetupHooks {

    public SetupHooks() {
        BasicConfigurator.configure();
    }

    @Before(order = 0)
    public void initDriver(Scenario scenario) {
        Utils.logger.info("Running : Scenario  -> " + scenario.getName());
        List<String> scenarioTags = (List<String>) scenario.getSourceTagNames();
        if (scenarioTags.size() > 0) {
            String message = "Executing scenarios with tag(s):";
            for (String scenarioTag : scenarioTags) {
                message += " " + scenarioTag;
            }
            Utils.logger.info(message);
        }

        if(scenarioTags.contains("@searchWiki"))
            Framework.init();
    }

    @After(order = 1)
    public void quitDriver(Scenario scenario) {
        List<String> scenarioTags = (List<String>) scenario.getSourceTagNames();
        if(scenarioTags.contains("@searchWiki")) {
            Utils.logger.info("[Driver] Quit driver Appium");
            Framework.customWebDriver.quit();
        }
    }

}
