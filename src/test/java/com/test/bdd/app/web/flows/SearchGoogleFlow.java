package com.test.bdd.app.web.flows;

import com.test.bdd.app.web.pageObjects.SearchPage;

public class SearchGoogleFlow {
    SearchPage searchPage;

    public SearchGoogleFlow() {
        this.searchPage = new SearchPage();
    }

    public void searchInGoogle(String text) {
        searchPage.searchInputText(text);
        searchPage.clickSearchButton();
    }
}
