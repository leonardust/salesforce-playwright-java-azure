package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import steps.SharedSteps;
import utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuickTransferTest extends BaseTest {

    private final TestUtils testUtils = new TestUtils();

    @BeforeEach
    void login(TestInfo testInfo) {
        try {
            SharedSteps.enterCredentials(loginPage, TestUtils.generateRandomString(8), TestUtils.generateRandomString(8));
            SharedSteps.submitLogin(loginPage);
        } catch(Throwable t) {
            captureScreenshot(testInfo.getDisplayName());
            throw t;
        }
    }

    @Test
    void quickTransferFundsPositive(TestInfo testInfo) {
        try {
            transferFunds("Jan Demobankowy", "100", "na waciki");
            validateDialogConfirmationText("Przelew wyonany!  Odbiorca: Jan Demobankowy Kwota: 100,00PLN Nazwa: na waciki");
        } catch (Throwable t) {
            captureScreenshot(testInfo.getDisplayName());
            throw t;
        }
    }

    @Step("Transfer funds")
    void transferFunds(String receiver, String amount, String title) {
        quickTransferPage.transferFunds(receiver, amount, title);
    }

    @Step("Validate dialog confirmation text")
    void validateDialogConfirmationText(String expectedText) {
        String actualText = quickTransferPage.getDialogConfirmationText();
        assertTrue(testUtils.containsIgnoreCaseAndSpace(quickTransferPage.getDialogConfirmationText(), expectedText), "Actual: " + actualText + "\nExpected: " + expectedText + "\n");
    }

}
