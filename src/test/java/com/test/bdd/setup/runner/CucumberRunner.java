package com.test.bdd.setup.runner;

import io.cucumber.junit.CucumberOptions;
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
        tags = "@tests"
)

public class CucumberRunner {
}
