package com.test.bdd.app.web.elements;

import com.test.bdd.initElements.WebElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchElements extends WebElements {

    public SearchElements() {
        super();
    }

    @FindBy(xpath = "//div[contains(@class,'FPdoLc')]//input[@class='gNO89b']")
    public static WebElement searchButton;

    @FindBy(xpath = "//input[@type='text']")
    public static WebElement textInput;
}
