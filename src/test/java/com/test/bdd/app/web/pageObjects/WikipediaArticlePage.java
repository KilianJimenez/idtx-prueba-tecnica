package com.test.bdd.app.web.pageObjects;

import com.test.bdd.app.web.elements.WikipediaArticleElements;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import static com.test.bdd.touchActions.web.actionMethodsWeb.*;

public class WikipediaArticlePage {

    public WikipediaArticlePage() {
        new WikipediaArticleElements();
    }

    @Screenshots(afterEachStep=true)
    @Step("Check First year automation process")
    public void checkFirstYearAutomationProcess(String year) {
        scrollTo(WikipediaArticleElements.firstAutomationProcessEvidenceText);
        Assert.assertTrue("The first automatic process was not developed in ["+ year +"] year",
                StringUtils.containsIgnoreCase(WikipediaArticleElements.firstAutomationProcessEvidenceText.getText(), year));
    }
}
