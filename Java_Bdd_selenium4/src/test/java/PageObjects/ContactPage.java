package PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage {

    WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
    }

    By contactEmail = By.id("recipient-email");
    By contactName = By.id("recipient-name");
    By message = By.id("message-text");
    By sendMessageBtn = By.xpath("//button[text()='Send message']");
    
    
    

    public void fillContactForm(String email, String name, String messageText) {
        driver.findElement(contactEmail).sendKeys(email);
        driver.findElement(contactName).sendKeys(name);
        driver.findElement(message).sendKeys(messageText);
    }

    public void clickSendMessage() {
        driver.findElement(sendMessageBtn).click();
    }
}

