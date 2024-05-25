package StepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;
import PageObjects.LoginPage;
import Hooks.Hooks;

public class LoginTest {
	
//	static WebDriver driver;
//	static LoginPage lp;
	
	private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
	WebDriver driver = Hooks.getDriver();
    LoginPage lp = new LoginPage(driver);
	
	@Given("User Launch chrome browser")
	public void user_launch_chrome_browser() {
		// The WebDriver is already initialized in the Hooks class
		 log.info("Launching Chrome browser.");
		System.out.println("The WebDriver is already initialized in the Hooks class");
	}
	
	@And("Navigate to login page")
	public void navigate_to_login_page() {
		 log.info("Navigating to login page.");
//		 driver.get("https://www.demoblaze.com/");
	    String act_tit =driver.getTitle();
	    String ex_tit = "STORE";
	    assertEquals(ex_tit,act_tit);
	}	

	@When("Input username {string} and password {string}")
	public void input_username_and_password(String username, String password) {
		log.info("Entering username: {} and password: {}", username, password);
		lp.HomePageNavitems("Log in");
	    lp.setLoginCred("loginusername",username);
	    lp.setLoginCred("loginpassword",password);
	}

	@And("Click on login")
	public void click_on_login() {
		 log.info("Clicking on login.");
	    lp.ClickBtn("Log in");
	}

	@Then("login page user message should be validated")
	public void login_page_user_message_should_be_validated() {
		
//		 String act_greet_msg = lp.Userprofile().getText();
//	     String exp_greet_msg = "Welcome harish24";
//	     System.out.println(act_greet_msg);
//	     assertEquals(exp_greet_msg, act_greet_msg);
		log.info("Validating user login.");
		boolean user = lp.Userprofile().isDisplayed();
		assertTrue(user);
		
		
	}

	@When("User click on logout")
	public void user_click_on_logout() {
		log.info("Clicking on logout.");
		
		lp.HomePageNavitems("Log out");
		
	}

	@Then("Page title should be validated")
	public void page_title_should_be_validated() {
		 log.info("Validating page title.");
		String act_tit =driver.getTitle();
	    String ex_tit = "STORE";
	    assertEquals(ex_tit,act_tit);
	    
	}

	@And("close the browser")
	public void close_the_browser() {
		 log.info("Closing the browser.");
		// The WebDriver quit is handled in the Hooks class		
//	    driver.quit();
		System.out.println("The WebDriver quit is handled in the Hooks class");
	    
	}
	
	@When("error message should be displayed")
	public void error_message_should_be_displayed() {
		log.info("Validating error message.");
		boolean isErrorDisplayed = lp.isErrorMessageDisplayed();
	    assertTrue("Error message is not displayed", isErrorDisplayed);
	   
	}

}
