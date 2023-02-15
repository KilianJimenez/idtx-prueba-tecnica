package com.test.bdd.initElements;

import com.test.bdd.drivers.CustomWebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class WebElements {
    public WebElements() {
        PageFactory.initElements(CustomWebDriver.driver, this);
    }
}
