package com.test.bdd.initElements;

import com.test.bdd.drivers.CustomiOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class IOSElements {
    public IOSElements() {
        PageFactory.initElements(new AppiumFieldDecorator(CustomiOSDriver.driver), this);
    }
}
