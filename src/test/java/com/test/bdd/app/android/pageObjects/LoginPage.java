package com.test.bdd.app.android.pageObjects;

import com.test.bdd.app.android.elements.LoginElements;

import static com.test.bdd.touchActions.mobile.actionMethodsMobile.*;

public class LoginPage {

    public LoginPage() {
        new LoginElements();
    }

    public void clickLoginButton() {
        clickOn(LoginElements.loginButton);
    }

    public void typeEmailInput(String email) {
        sendKeysTo(email, LoginElements.emailInput);
    }

    public void typePasswordInput(String password) {
        sendKeysTo(password, LoginElements.passwordInput);
    }

}
