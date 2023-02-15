package com.test.bdd.app.android.flows;

import com.test.bdd.app.android.pageObjects.LoginPage;
import com.test.bdd.businessFlows.LoginBusinessFlow;

public class LoginFlowAndroid extends LoginBusinessFlow {
    LoginPage loginPage;

    public LoginFlowAndroid() {
        this.loginPage = new LoginPage();
    }

    @Override
    public void doLogin(String email, String password) {
        loginPage.clickLoginButton();
        loginPage.typeEmailInput(email);
        loginPage.typePasswordInput(password);
        loginPage.clickLoginButton();
    }
}
