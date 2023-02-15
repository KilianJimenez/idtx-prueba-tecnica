package com.test.bdd.initElements;

import com.test.bdd.drivers.CustomAndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class AndroidElements {
    public AndroidElements() {
        PageFactory.initElements(new AppiumFieldDecorator(CustomAndroidDriver.driver), this);
    }
}
