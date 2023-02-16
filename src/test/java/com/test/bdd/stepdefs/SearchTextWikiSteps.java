package com.test.bdd.stepdefs;

import com.test.bdd.app.web.flows.CheckFirstYearAutomationFlow;
import com.test.bdd.app.web.flows.OpenWikipediaArticleFlow;
import com.test.bdd.app.web.flows.SearchGoogleFlow;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.test.bdd.utils.Utils.logger;

public class SearchTextWikiSteps {

    private final SearchGoogleFlow searchGoogleFlow;
    private final OpenWikipediaArticleFlow openWikipediaArticleFlow;
    private final CheckFirstYearAutomationFlow checkFirstYearAutomationFlow;

    public SearchTextWikiSteps() {
        this.searchGoogleFlow = new SearchGoogleFlow();
        this.openWikipediaArticleFlow = new OpenWikipediaArticleFlow();
        this.checkFirstYearAutomationFlow = new CheckFirstYearAutomationFlow();
    }

    @Given("I search {string} in Google")
    public void iSearchInGoogle(String text) {
        searchGoogleFlow.searchInGoogle(text);
        logger.info("Text ["+text+"] searched in Google");
    }

    @When("I open the article in Wikipedia")
    public void iOpenWikipediaArticle() {
        openWikipediaArticleFlow.openWikipediaArticle();
        logger.info("Wikipedia article opened");
    }

    @Then("I check the year the first automatic process was developed was {string}")
    public void iCheckYearOfFirstAutomaticProcess(String year) {
        checkFirstYearAutomationFlow.checkFirstYearAutomationProcess(year);
        logger.info("Assert: Check first automatic process developed in year ["+year+"]");
    }
}
