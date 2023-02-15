package com.test.bdd.app.ios.elements;

import com.test.bdd.initElements.IOSElements;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginElements extends IOSElements {

    public LoginElements() {
        super();
    }

    @iOSXCUITFindBy(id = "EmailIdentifier")
    public static MobileElement emailInput;

    @iOSXCUITFindBy(id = "PasswordIdentifier")
    public static MobileElement passwordInput;

    @iOSXCUITFindBy(id = "LoginIdentifier")
    public static MobileElement loginOption;
}
