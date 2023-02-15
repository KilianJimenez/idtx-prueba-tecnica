package com.test.bdd.businessFlows;

import com.test.bdd.app.android.flows.LoginFlowAndroid;
import com.test.bdd.framework.Framework;
import com.test.bdd.app.ios.flows.LoginFlowiOS;
import com.test.bdd.app.web.flows.LoginFlowWeb;

public abstract class LoginBusinessFlow {

    public static LoginBusinessFlow loadPlatformFlow() {
        switch (Framework.getPlatform()) {
            case ANDROID:
                return new LoginFlowAndroid();
            case IOS:
                return new LoginFlowiOS();
            case WEB:
            default:
                return new LoginFlowWeb();
        }
    }

    public abstract void doLogin(String email, String password) throws InterruptedException;
}
