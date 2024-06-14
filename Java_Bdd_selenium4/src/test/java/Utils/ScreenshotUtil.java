package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    private WebDriver driver;
    private static final String SCREENSHOT_DIR = "Screenshots";

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String scenarioName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
            Files.move(screenshot.toPath(), Paths.get(SCREENSHOT_DIR, scenarioName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
