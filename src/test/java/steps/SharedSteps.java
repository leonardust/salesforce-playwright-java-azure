package steps;

import io.qameta.allure.Step;
import pages.LoginPage;

public class SharedSteps {

    @Step("Enter credentials")
    public static void enterCredentials(LoginPage loginPage, String username, String password) {
        loginPage.enterCredentials(username, password);
    }

    @Step("Submit login")
    public static void submitLogin(LoginPage loginPage) {
        loginPage.getLoginButton().click();
    }
}
