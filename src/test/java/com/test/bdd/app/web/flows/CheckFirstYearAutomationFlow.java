package com.test.bdd.app.web.flows;

import com.test.bdd.app.web.pageObjects.WikipediaArticlePage;

public class CheckFirstYearAutomationFlow {
    WikipediaArticlePage wikipediaArticlePage;

    public CheckFirstYearAutomationFlow() {
        this.wikipediaArticlePage = new WikipediaArticlePage();
    }

    public void checkFirstYearAutomationProcess(String year) {
        wikipediaArticlePage.checkFirstYearAutomationProcess(year);
    }
}
