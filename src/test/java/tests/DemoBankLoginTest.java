package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import steps.SharedSteps;
import utils.TestUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoBankLoginTest extends BaseTest {

    @Test
    void loginTestPositive(TestInfo testInfo) {
        try {
            SharedSteps.enterCredentials(loginPage, TestUtils.generateRandomString(8), TestUtils.generateRandomString(8));
            SharedSteps.submitLogin(loginPage);
            validatePositiveLogin();
        } catch (Throwable t) {
            captureScreenshot(testInfo.getDisplayName());
            throw t;
        }
    }

    @Test
    void loginTestNegative(TestInfo testInfo) {
        try {
            SharedSteps.enterCredentials(loginPage, TestUtils.generateRandomString(8), "");
            validateNegativeLogin();
        } catch (Throwable t) {
            captureScreenshot(testInfo.getDisplayName());
            throw t;
        }
    }

    @Step("Validate positive login")
    void validatePositiveLogin() {
        assertThat(homePage.getLogoutButton()).isVisible();
    }

    @Step("Validate negative login")
    void validateNegativeLogin() {
        assertThat(loginPage.getLoginButton()).isVisible();
    }

}
