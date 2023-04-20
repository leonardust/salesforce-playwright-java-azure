package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.java.Log;
import services.EnvironmentReaderService;

@Log
public class BrowserFactory {

    private final Browser browser;

    public BrowserFactory() {
        Playwright playwright = Playwright.create();
        log.info("Create playwright instance");

        String browserType = System.getProperty("browser") != null ? System.getProperty("browser") : EnvironmentReaderService.getProperty("browser");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless") != null ? System.getProperty("headless") : EnvironmentReaderService.getProperty("browser.mode"));

        switch (browserType) {
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                log.info("Browser: " + browser.browserType().name() + "\nVersion: " + browser.version() + "\nHeadless: " + isHeadless);
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                log.info("Browser: " + browser.browserType().name() + "\nVersion: " + browser.version() + "\nHeadless: " + isHeadless);
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                log.info("Browser: " + browser.browserType().name() + "\nVersion: " + browser.version() + "\nHeadless: " + isHeadless);
                break;
            default:
                throw new IllegalArgumentException("Invalid browser value: " + null);
        }
    }

    public Browser getBrowser() {
        return browser;
    }
}
