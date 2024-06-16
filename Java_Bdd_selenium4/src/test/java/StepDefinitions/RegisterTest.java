package StepDefinitions;

import io.cucumber.datatable.DataTable;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import Hooks.Hooks;
import PageObjects.*;
import io.cucumber.java.en.*;

public class RegisterTest {
	
	
	WebDriver driver = Hooks.getDriver();
	
	LoginPage lp = new LoginPage(driver);
	ContactPage cp = new ContactPage(driver);
	RegisterPage rp = new RegisterPage(driver);
	private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
	SoftAssert sa = new SoftAssert();
	
	
	@Given("Navigateto Home page")
	public void navigateto_home_page() {
		log.info("Navigating to login page.");
//		 driver.get("https://www.demoblaze.com/");
	    String act_tit =driver.getTitle();
	    String ex_tit = "STORE";
	    sa.assertEquals(ex_tit,act_tit,"Expected title is not correct");
	}

	@When("click on SignUP under menu")
	public void click_on_sign_up_under_menu() {
		lp.HomePageNavitems("Sign up");
		log.info("Clicked on Contact of menu");
	}

	@Then("should singinup with name and pass and validate alert message")
	public void should_singinup_with_name_and_pass_and_validate_alert_message(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String username = row.get("username");
            String password = row.get("password");
            lp.setLoginCred("sign-username",username);
            lp.setLoginCred("sign-password", password);
            rp.click_signup_Btn();
            try {
				Thread.sleep(Duration.ofSeconds(1));
			} catch (InterruptedException e) {
				
				System.err.println("error: "+e.getMessage());
			}
            Alert al = driver.switchTo().alert();
            al.getText();
            al.accept();
            log.info("Alert accepted");
            Actions act = new Actions(driver);
            // Press Escape key
            act.sendKeys(Keys.ESCAPE).build().perform();
            lp.HomePageNavitems("Sign up");
            
        	sa.assertAll();
        }
	   
	}

}
