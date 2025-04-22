package Ecommeres.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecommeres.AbstractCompounts.AbstractCompounts;

public class CheckoutPage extends AbstractCompounts {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".action__submit")
	WebElement Submit;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement County;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectcounty;

	By results = By.cssSelector(".ta-results");
	
	@FindBy(css =".action__submit")
	WebElement button;

	public void SelectCounty(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(County, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectcounty.click();
		waitForElementToClickable(button);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	public ConfirmationPage submitOrder() {
		Submit.click();
		return new ConfirmationPage(driver);

	}

}
