package tests;

import annotations.PlaywrightPage;
import com.microsoft.playwright.*;
import factory.BrowserFactory;
import factory.ContextFactory;
import io.qameta.allure.Allure;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import pages.HomePage;
import pages.LoginPage;
import pages.QuickTransferPage;
import services.EnvironmentReaderService;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;

@Log
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    private final BrowserFactory browserFactory = new BrowserFactory();
    private final ContextFactory contextFactory = new ContextFactory();
    private Browser browser;
    protected BrowserContext browserContext;
    private Page page;
    @PlaywrightPage
    protected LoginPage loginPage;
    @PlaywrightPage
    protected HomePage homePage;
    @PlaywrightPage
    protected QuickTransferPage quickTransferPage;

    @BeforeEach
    public void setUp() {

        browser = browserFactory.getBrowser();
        browserContext = contextFactory.getBrowserContext();
        page = browserContext.newPage();
        initPage(this, page);

        log.info("Navigate to " + EnvironmentReaderService.getProperty("url"));
        page.navigate(EnvironmentReaderService.getProperty("url"));
    }

    private void initPage(Object object, Page page) {
        Class<?> clazz = object.getClass().getSuperclass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PlaywrightPage.class)) {
                Class<?>[] type = {Page.class};
                try {
                    field.set(this, field.getType().getConstructor(type).newInstance(page));
                    log.info("Call constructor for playwright page with name: " + field.getName());
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                         NoSuchMethodException e) {
                    log.info("Did not manage to call constructor for playwright page with name " + field.getName());
                }
            }
        }
    }

    @AfterAll
    void tearDown(TestInfo testInfo) {
        if (contextFactory.isTracing()) {
            browserContext.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/traces/" + testInfo.getDisplayName().replace("()", "") + ".zip")));
            log.info("Stop tracing");
        }

        browserContext.close();
        log.info("Close browser context");
        browser.close();
        log.info("Close browser");
    }

    public void captureScreenshot(String fileName) {
        try {
            byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions());
            Allure.addAttachment(fileName, new ByteArrayInputStream(screenshotBytes));
            String path = System.getProperty("user.dir") + "/target/allure-results/" + fileName + ".png";
            try (FileOutputStream out = new FileOutputStream(path)) {
                out.write(screenshotBytes);
            }
        } catch (IOException | PlaywrightException e) {
            System.out.println("screenshot failed:" + e.getMessage());
        }
    }

}
