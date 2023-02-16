package com.test.bdd.app.web.elements;

import com.test.bdd.initElements.WebElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultElements extends WebElements {

    public ResultElements() {
        super();
    }

    @FindBy(xpath = "//div[@class='yuRUbf']//a[contains(@href,'wikipedia.org')]")
    public static WebElement wikipediaLink;
}
