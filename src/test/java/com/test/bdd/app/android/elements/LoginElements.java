package com.test.bdd.app.android.elements;

import com.test.bdd.initElements.AndroidElements;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginElements extends AndroidElements {

    public LoginElements() {
        super();
    }

    @AndroidFindBy(id = "login_btn")
    public static MobileElement loginButton;

    @AndroidFindBy(id = "login_username_et")
    public static MobileElement emailInput;

    @AndroidFindBy(id = "login_password_et")
    public static MobileElement passwordInput;

    @AndroidFindBy(id = "login_username_et")
    public static MobileElement forgottenEmailInput;

    @AndroidFindBy(id = "accept_warning_read_checkbox")
    public static MobileElement verificationResetButton;

    @AndroidFindBy(id = "request_passwordlink_button")
    public static MobileElement resetPasswordButton;

    @AndroidFindBy(id = "tvText")
    public static MobileElement resetPasswordMessage;

    @AndroidFindBy(id = "pinEditText")
    public static MobileElement pinInput;

    @AndroidFindBy(id = "save_btn")
    public static MobileElement saveButton;
}
