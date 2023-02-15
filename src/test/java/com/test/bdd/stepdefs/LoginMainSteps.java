package com.test.bdd.stepdefs;

import com.test.bdd.businessFlows.LoginBusinessFlow;
import cucumber.api.java.en.Given;

import static com.test.bdd.utils.Utils.logger;

public class LoginMainSteps {

    private final LoginBusinessFlow loginBusinessFlow;

    public LoginMainSteps() {
        this.loginBusinessFlow = LoginBusinessFlow.loadPlatformFlow();
    }

    @Given("I login as user with email {string} and password {string}")
    public void iLoginAsUserWithEmailAndPassword(String email, String password) throws InterruptedException {
        loginBusinessFlow.doLogin(email, password);
        logger.info("Authenticated with email ["+email+"] and password ["+password+"].");
    }
}
