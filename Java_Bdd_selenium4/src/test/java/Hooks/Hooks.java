package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Utils.ScreenshotUtil;

import java.io.File;
import java.time.Duration;

public class Hooks {
	

    private static WebDriver driver;
    private ScreenshotUtil screenshotUtil;
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private static final String SCREENSHOT_DIR = "Screenshots";

    @Before
    public void setup() {
        logger.info("Deleting old screenshots.");
        deleteScreenshots();

        logger.info("Initializing WebDriver.");
        driver = new ChromeDriver();
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
