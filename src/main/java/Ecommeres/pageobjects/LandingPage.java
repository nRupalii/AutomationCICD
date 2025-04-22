package Ecommeres.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecommeres.AbstractCompounts.AbstractCompounts;

public class LandingPage extends AbstractCompounts {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// PageFactorty
	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement passwordEle;
	@FindBy(id = "login")
	WebElement submit;
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	public ProductCatloag LoginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatloag productcatlog = new ProductCatloag(driver);
		return productcatlog;
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		errorMessage.getText();
		return errorMessage.getText();
		
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
