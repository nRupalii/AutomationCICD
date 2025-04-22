package Ecommerce.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomastionTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String ProductName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup(); // ChromeDriver Automtically Downlaode the Chrome Based on the Version
													// , WebdriverManger Dependancy is added

		ChromeOptions options = new ChromeOptions();// Run browser in Incognito mode
		options.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://rahulshettyacademy.com/client");
//Login URL 
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".mb-3")));
//Select the Item 
		// 1. Get all the Product in the List

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// Page Loading inpector - ng-animating
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")));
		// Thread.sleep(1000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

//Validate Whaterver you added in the Cart its Correct 
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		// driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build()
				.perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		// Scroll to the Submit button
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

		// Small delay to allow UI update
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// Click the button
		button.click();
		// driver.findElement(By.cssSelector(".action__submit")).click();
		String ConfimastionMassage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfimastionMassage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(ConfimastionMassage);

	}

}
