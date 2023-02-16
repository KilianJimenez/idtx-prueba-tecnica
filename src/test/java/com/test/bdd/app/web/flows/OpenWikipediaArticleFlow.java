package com.test.bdd.app.web.flows;

import com.test.bdd.app.web.pageObjects.ResultPage;

public class OpenWikipediaArticleFlow {
    ResultPage resultPage;

    public OpenWikipediaArticleFlow() {
        this.resultPage = new ResultPage();
    }

    public void openWikipediaArticle() {
        resultPage.clickWikipediaLink();
    }
}
