package com.test.bdd.setup.hooks;

import com.test.bdd.drivers.CustomWebDriver;
import com.test.bdd.utils.Utils;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriverException;

import java.util.List;

import static com.test.bdd.utils.Utils.logWebScreenShot;

public class ScenarioHooks {

    private static final long NANO_TO_SECONDS = 1000000000;

    private long timeExecutingTask = 0;

    @Before(order = 1)
    public void deleteCookiesBeforeScenario() {
        //CustomWebDriver().deletecookies;
    }

    @Before(order = 2)
    public void logBeforeScenario(Scenario scenario) {
        timeExecutingTask = System.nanoTime();
        Utils.logger.info("Entering in scenario |" + scenario.getName() + "|");
    }

    @After(order = 2)
    public void logAfterScenario(Scenario scenario) {
        long taskTimeInSeconds = (System.nanoTime() - timeExecutingTask) / NANO_TO_SECONDS;
        String msg = "Exiting scenario |" + scenario.getName() + "| with status \"" + scenario.getStatus() + "\" in " + taskTimeInSeconds + " seconds";
        if (scenario.isFailed()) {
            Utils.logger.error(msg);
        } else {
            Utils.logger.info(msg);
        }
    }

    @After(order = 3)
    public void embedScreenshotOnFailure(Scenario scenario) {
        List<String> scenarioTags = (List<String>) scenario.getSourceTagNames();
        if (scenarioTags.contains("@searchWiki")) {
            try {
                logWebScreenShot("Snapshot of scenario: " + scenario.getName(), CustomWebDriver.driver);
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            }
        }
    }

    @After(order = 1)
    public void deleteCookiesAfterScenario() {

    }


}
