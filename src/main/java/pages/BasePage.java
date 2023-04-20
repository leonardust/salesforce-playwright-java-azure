package pages;

import com.microsoft.playwright.Page;

public class BasePage {

    private final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

}
