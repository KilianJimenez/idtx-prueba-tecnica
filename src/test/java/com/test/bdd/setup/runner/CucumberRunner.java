package com.test.bdd.setup.runner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {
                "com.test.bdd",
        },
        strict = true,
        monochrome = true,
        tags = "@dummy"
)

public class CucumberRunner {
}
