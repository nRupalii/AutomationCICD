package Ecommerce.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//Cucumber can not run its own its required TestNG or Junit to run the Test Cases
//TestNG we are using only for Test Run , its depedets upone what we have the Asserstion
@CucumberOptions(features = "src/test/java/Ecommerce/Cucumber", glue ="Ecommerce.StepDefinistion",
monochrome = true,tags = "@ErrorValidastions", plugin = {"html:target/Cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    public static void main(String[] args) {

    }
}
