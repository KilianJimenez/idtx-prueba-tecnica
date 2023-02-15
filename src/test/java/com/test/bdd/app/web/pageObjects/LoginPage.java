package com.test.bdd.app.web.pageObjects;

import com.test.bdd.app.web.elements.LoginElements;

import static com.test.bdd.touchActions.web.actionMethodsWeb.*;

public class LoginPage {
    private final String URL = "https://www.google.com/";

    public LoginPage() {
        new LoginElements();
    }


    public void openApp() {
        openPage(URL);
        acceptCookies();
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
