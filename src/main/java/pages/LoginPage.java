package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class LoginPage extends BasePage {

    @Getter
    private final Locator usernameInput;
    @Getter
    private final Locator passwordInput;
    @Getter
    private final Locator loginButton;

    public LoginPage(Page page) {
        super(page);
        this.usernameInput = page.locator("#login_id");
        this.passwordInput = page.locator("#login_password");
        this.loginButton = page.locator("#login-btn");
    }

    public void enterCredentials(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
    }
}
