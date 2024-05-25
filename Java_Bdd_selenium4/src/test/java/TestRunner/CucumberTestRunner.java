package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import org.testng.annotations.DataProvider;


@RunWith(Cucumber.class)
@CucumberOptions(tags="",features= {"Features"},glue ={"StepDefinitions","Hooks"},
plugin = {"pretty","html:Reports/htmlreport.html"},monochrome = true)

public class CucumberTestRunner extends AbstractTestNGCucumberTests{

}


 