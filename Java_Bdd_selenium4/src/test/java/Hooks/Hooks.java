package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Utils.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.time.Duration;

public class Hooks {

    private static WebDriver driver;
    private ScreenshotUtil screenshotUtil;
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private static final String SCREENSHOT_DIR = "Screenshots";
    private static final String BROWSER = System.getProperty("browser", "chrome");

    @Before
    public void setup() {
        logger.info("Deleting old screenshots.");
        deleteScreenshots();

        logger.info("Initializing WebDriver for browser: {}", BROWSER);
        initializeDriver(BROWSER);
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        screenshotUtil = new ScreenshotUtil(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                logger.error("Scenario '{}' failed. Taking screenshot.", scenario.getName());
                screenshotUtil.takeScreenshot(scenario.getName());
            }
        } finally {
            logger.info("Quitting WebDriver.");
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    private void initializeDriver(String browser) {
        if (browser.equalsIgnoreCase("headless-chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Add headless argument
            options.addArguments("--disable-gpu"); // Disable GPU acceleration (required for Windows OS)
            options.addArguments("--window-size=1920,1080"); // Set window size to avoid issues with elements not being in the viewport
            driver = new ChromeDriver(options);
        } 
        else if (browser.equalsIgnoreCase("headless-firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless"); // Add headless argument
            options.addArguments("--disable-gpu"); // Disable GPU acceleration (required for Windows OS)
            options.addArguments("--window-size=1920,1080"); // Set window size to avoid issues with elements not being in the viewport
            driver = new FirefoxDriver(options);
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } 
        else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            logger.warn("Unsupported browser: {}. Defaulting to Chrome.", browser);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }

    private void deleteScreenshots() {
        File directory = new File(SCREENSHOT_DIR);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    logger.debug("Deleting file: {}", file.getName());
                    file.delete();
                }
            }
        }
    }
}
