package com.test.bdd.app.web.pageObjects;

import com.test.bdd.app.web.elements.ResultElements;
import com.test.bdd.app.web.elements.SearchElements;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;

import static com.test.bdd.touchActions.web.actionMethodsWeb.*;

public class ResultPage {

    public ResultPage() {
        new ResultElements();
    }

    @Screenshots(afterEachStep=true)
    @Step("Open Wikipedia link from Google results")
    public void clickWikipediaLink() {
        waitForElementToBeVisible(ResultElements.wikipediaLink, 3);
        clickOn(ResultElements.wikipediaLink);
    }
}
