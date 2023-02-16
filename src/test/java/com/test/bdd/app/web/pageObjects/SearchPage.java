package com.test.bdd.app.web.pageObjects;

import com.test.bdd.app.web.elements.SearchElements;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;

import static com.test.bdd.touchActions.web.actionMethodsWeb.*;

public class SearchPage {
    private final String URL = "https://www.google.es/";

    public SearchPage() {
        new SearchElements();
    }

    @Screenshots(afterEachStep=true)
    @Step("Click search button")
    public void clickSearchButton() {
        clickOn(SearchElements.searchButton);
    }

    @Screenshots(afterEachStep=true)
    @Step("Search some text in Google")
    public void searchInputText(String text) {
        waitForElementToBeVisible(SearchElements.textInput);
        sendKeysTo(text, SearchElements.textInput);
    }
}
