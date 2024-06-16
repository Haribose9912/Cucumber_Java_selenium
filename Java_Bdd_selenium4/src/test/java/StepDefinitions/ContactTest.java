package StepDefinitions;

import io.cucumber.java.en.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.*;

import java.util.List;
import PageObjects.*;


import Utils.ExcelUtil;

import Hooks.Hooks;


public class ContactTest{

	
private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

WebDriver driver = Hooks.getDriver();
SoftAssert sa = new SoftAssert();

ContactPage cp = new ContactPage(driver);

LoginPage lp = new LoginPage(driver);

ExcelUtil excelUtil = new ExcelUtil("src/main/resources/ContactDetails.xlsx");// Provide the correct path to your Excel file


List<List<String>> excelData = excelUtil.readSheetData("Sheet1");

@Given("Navigate to Home page")
public void navigate_to_home_page() {
	 log.info("Navigating to login page.");
//	 driver.get("https://www.demoblaze.com/");
    String act_tit =driver.getTitle();
    String ex_tit = "STORE";
    sa.assertEquals(ex_tit,act_tit,"Expected title is not correct");
}

@When("click on Contact under menu")
public void click_on_contact_under_menu() {
	lp.HomePageNavitems("Contact");
	log.info("Clicked on Contact of menu");
    
}

@Then("should input Contact details from Excel")
public void should_input_contact_details_from_excel() {
	List<List<String>> excelData = excelUtil.readSheetData("Sheet1");
	  for (List<String> row : excelData) {
          String email = row.get(0);
          String name = row.get(1);
          String message = row.get(2);
          cp.fillContactForm(email, name, message);
          cp.clickSendMessage();
          log.info("Switched to alert");
      	Alert alert = driver.switchTo().alert();
      	String exp_alertMsg ="Thanks for the message!!";
      	String act_alertMsg = alert.getText();
      	log.info("Alert message: " + act_alertMsg);
          assertNotNull(act_alertMsg);
          sa.assertEquals(exp_alertMsg, act_alertMsg,"Validation of alert message failed");  
          alert.accept();
          lp.HomePageNavitems("Contact");
          log.info("Alert accepted");
      	sa.assertAll();
      }
      log.info("Entered contact details");
     System.out.println("excel data: "+excelData);
	
}

@Then("Click on send message")
public void click_on_send_message() {
//	log.info("Clicked on send message");
//	 cp.clickSendMessage();
	
}

@When("Alert is opened")
public void alert_is_opened() {
	log.info("Alert is opned ");
}

@And("validate Alert message")
public void validate_alert_message() {
	
//	log.info("Switched to alert");
//	Alert alert = driver.switchTo().alert();
//	String exp_alertMsg ="Thanks for the message!!";
//	String act_alertMsg = alert.getText();
//	log.info("Alert message: " + act_alertMsg);
//    assertNotNull(act_alertMsg);
//    sa.assertEquals(exp_alertMsg, act_alertMsg,"Validation of alert message failed");  
//    alert.accept();
//    log.info("Alert accepted");
//	sa.assertAll();
}

}