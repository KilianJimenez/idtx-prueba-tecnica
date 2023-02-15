package com.test.bdd.app.web.flows;

import com.test.bdd.app.web.pageObjects.LoginPage;
import com.test.bdd.businessFlows.LoginBusinessFlow;
import com.test.bdd.setup.properties.GlobalState;

public class LoginFlowWeb extends LoginBusinessFlow {
    LoginPage loginPage;

    public LoginFlowWeb() {
        this.loginPage = new LoginPage();
    }

    @Override
    public void doLogin(String email, String password) {
        loginPage.openApp();
        loginPage.typeEmailInput(email);
        loginPage.typePasswordInput(password);
        loginPage.clickLoginButton();
        GlobalState.getInstance().setData("email", email);
    }
}
