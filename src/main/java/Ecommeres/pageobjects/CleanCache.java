package Ecommeres.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Ecommeres.AbstractCompounts.AbstractCompounts;

public class CleanCache extends AbstractCompounts{

	WebDriver driver;


	public CleanCache(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public  LandingPage DeleteCokkies() throws InterruptedException {
		driver.manage().deleteAllCookies(); // Clears cookies
		driver.get("chrome://settings/clearBrowserData"); 

		// Simulate keyboard actions to clear cache
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB)
		       .sendKeys(Keys.ENTER) // Press Enter to clear
		       .perform();

		Thread.sleep(3000); // Wait for cache to be cleared
		return new LandingPage(driver);

		
	}

}
