package com.test.bdd.app.ios.flows;

import com.test.bdd.businessFlows.LoginBusinessFlow;
import com.test.bdd.app.ios.pageObjects.LoginPage;

public class LoginFlowiOS extends LoginBusinessFlow {
    LoginPage loginPage;

    public LoginFlowiOS() {
        this.loginPage = new LoginPage();
    }

    @Override
    public void doLogin(String email, String password) {
        loginPage.typeEmailInput(email);
        loginPage.typePasswordInput(password);
        loginPage.clickOnSubmitLogin();
    }
}
