package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import services.EnvironmentReaderService;

import java.nio.file.Paths;
import java.util.List;

public class ContextFactory extends BrowserFactory{
    private final BrowserContext browserContext;

    private final boolean isTracing;

    public ContextFactory() {
        browserContext = getBrowser().newContext(new Browser.NewContextOptions()
                .setPermissions(List.of("geolocation"))
                .setRecordVideoDir(Paths.get("target/videos/"))
        );

        isTracing = Boolean.parseBoolean(System.getProperty("tracing") != null ? System.getProperty("tracing") : EnvironmentReaderService.getProperty("tracing"));
        if (isTracing) {
            browserContext.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(false));
        }
    }

    public BrowserContext getBrowserContext() {
        return browserContext;
    }

    public boolean isTracing() {
        return isTracing;
    }
}
