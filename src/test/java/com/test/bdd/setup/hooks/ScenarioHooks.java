package com.test.bdd.setup.hooks;

import com.test.bdd.utils.Utils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriverException;

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

    @After(order = 3)
    public void embedScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                //	byte[] a = ((TakesScreenshot) CustomWebDriver.driver.getScreenshot());
                //	scenario.embed(a, "image/png");
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            }
        }
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

    @After(order = 1)
    public void deleteCookiesAfterScenario() {

    }


}
