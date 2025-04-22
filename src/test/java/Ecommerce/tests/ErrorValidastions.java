package Ecommerce.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import Ecommeres.TestCompounts.Retry;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Ecommeres.TestCompounts.BaseTest;
import Ecommeres.pageobjects.CartPage;
import Ecommeres.pageobjects.CheckoutPage;
import Ecommeres.pageobjects.CleanCache;
import Ecommeres.pageobjects.ConfirmationPage;
import Ecommeres.pageobjects.LandingPage;
import Ecommeres.pageobjects.ProductCatloag;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidastions extends BaseTest {
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)


	public void LoginErrorValidastion() throws InterruptedException, IOException {

		ProductCatloag productcatlog = landlingPage.LoginApplication("anka@gmail.com", "Iamking@000");
//ng-tns-c4-4 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
		String ErrorMassage = driver.findElement(By.xpath("//div[@aria-label='Incorrect email or password.']")).getText();
		Assert.assertTrue(landlingPage.getErrorMessage().contains("Incorrect email"));
		//Assert.assertEquals("Incorrect email or ", landlingPage.getErrorMessage());
		System.out.println(ErrorMassage);
	}
	@Test
	public void ProductErrorValidastion() throws InterruptedException, IOException {

		String ProductName = "ZARA COAT 3";
		ProductCatloag productcatlog = landlingPage.LoginApplication("rupalin@gmail.com", "Iamking@000");

		List<WebElement> products = productcatlog.getProductList();

		// Debug logs to help in Jenkins
		System.out.println("Available products on the page:");
		for (WebElement product : products) {
			System.out.println(product.findElement(By.cssSelector("b")).getText());
		}

		WebElement targetProduct = productcatlog.getProductByName(ProductName);
		Assert.assertNull(targetProduct, "Product should not be found: " + ProductName);
	}

	/*public void ProductErrorValidastion() throws InterruptedException, IOException {

		String ProductName = "ZARA COAT 3";
		ProductCatloag productcatlog = landlingPage.LoginApplication("rupalin@gmail.com", "Iamking@000");

		List<WebElement> products = productcatlog.getProductList();
		productcatlog.addProductToCart(ProductName);
		CartPage cartPage = productcatlog.goToCartPage();

		Boolean match = cartPage.VarifyProductDisplay(ProductName);
		Assert.assertFalse(match);
		//Assert.assertTrue(match); // if you expect it to be there
		//Java Class --> Login Page 5 - @testCase1 , @testCase2, ...
		//Java Class Submit Order , Order check , Product Catloague --> All test cases in one Module
	}*/
}
