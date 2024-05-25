package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
	private static final Logger log = LoggerFactory.getLogger(ScreenshotUtil.class);
    private WebDriver driver;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }
    	
    public void takeScreenshot(String testName) {
    	log.info("Taking screenshot for test: {}", testName);
        // Get the screenshot as an image file
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destDir = "Screenshots";
        String destFilePath = destDir + "/" + testName + "_" + timestamp + ".png";

        try {
            // Create the screenshots directory if it doesn't exist
            File destDirFile = new File(destDir);
            if (!destDirFile.exists()) {
                destDirFile.mkdirs();
            }
            // Copy the screenshot to the destination path
            FileUtils.copyFile(srcFile, new File(destFilePath));
            System.out.println("Screenshot taken: " + destFilePath);
            log.info("Screenshot saved at: {}", destFilePath);
        } catch (IOException e) {
        	 log.error("Failed to take screenshot: {}", e.getMessage());
            e.printStackTrace();
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}
