package com.test.bdd.app.web.elements;

import com.test.bdd.initElements.WebElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaArticleElements extends WebElements {

    public WikipediaArticleElements() {
        super();
    }

    @FindBy(xpath = "//p[contains(.,'primer proceso industrial completamente automatizado')]")
    //@FindBy(xpath = "//p//text()[contains(.,'primer proceso industrial completamente automatizado')]")
    public static WebElement firstAutomationProcessEvidenceText;
}
