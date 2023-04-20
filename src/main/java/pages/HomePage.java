package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class HomePage extends BasePage {

    @Getter
    private final Locator logoutButton;

    public HomePage(Page page) {
        super(page);
        this.logoutButton = page.locator("#log_out");
    }

}
