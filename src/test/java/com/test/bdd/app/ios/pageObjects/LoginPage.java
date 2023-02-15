package com.test.bdd.app.ios.pageObjects;

import com.test.bdd.app.ios.elements.LoginElements;
import com.test.bdd.touchActions.mobile.actionMethodsMobile;

public class LoginPage {

    public LoginPage() {
        new LoginElements();
    }

    public void typeEmailInput(String email) {
        actionMethodsMobile.sendKeysTo(email, LoginElements.emailInput);
        actionMethodsMobile.sleep(3000);
    }

    public void typePasswordInput(String password) {
        actionMethodsMobile.sendKeysTo(password, LoginElements.passwordInput);
    }

    public void clickOnSubmitLogin() {
        actionMethodsMobile.clickOn(LoginElements.loginOption);
    }
}
