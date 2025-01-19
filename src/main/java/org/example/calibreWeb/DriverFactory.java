package org.example.calibreWeb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

public class DriverFactory {

    private static final String grid_url = System.getenv("GRID_URL");
    static String projectPath = Paths.get("").toAbsolutePath().toString();
    static String filePath =  "\\src\\main\\downloadTest";
    private static final String DOWNLOAD_PATH = projectPath+filePath;

    private static final String browser = Optional
            .ofNullable(System.getenv("BROWSER"))
            .orElse("chrome");

    public static WebDriver getDriver() {
        if (grid_url != null) {
            return getRemoteDriver(browser);
        } else {
            return getLocalDriver(browser);
        }
    }

    private static WebDriver getRemoteDriver(String browser) {
        URL hubUrl;
        try {
            hubUrl = new URI(grid_url).toURL();
        } catch (URISyntaxException | MalformedURLException err) {
            throw new IllegalArgumentException("Invalid grid URL");
        }

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-popup-blocking"); // Disable popups
            options.addArguments("--start-maximized");
            options.setExperimentalOption("prefs", Map.of(
                    "download.default_directory", DOWNLOAD_PATH,
                    "download.prompt_for_download", false,
                    "download.directory_upgrade", true,
                    "safebrowsing.enabled", true
            ));

            options.addArguments("--headless");
            return new RemoteWebDriver(hubUrl, options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("-headless");
            return new RemoteWebDriver(hubUrl, options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver getLocalDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--start-maximized");
            options.setExperimentalOption("prefs", Map.of(
                    "download.default_directory", DOWNLOAD_PATH,
                    "download.prompt_for_download", false,
                    "download.directory_upgrade", true,
                    "safebrowsing.enabled", true
            ));
            return new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            return new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}