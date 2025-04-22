package Ecommerce.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import Ecommeres.pageobjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ecommeres.TestCompounts.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SubmitTestOrder extends BaseTest {

	@Test(dataProvider = "getData",groups = {"Purchase"})

	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatloag productcatlog = landlingPage.LoginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productcatlog.getProductList();

		// Debug logs to help in Jenkins
		System.out.println("Available products:");
		for (WebElement product : products) {
			System.out.println(product.findElement(By.cssSelector("b")).getText());
		}

		WebElement targetProduct = productcatlog.getProductByName(input.get("product"));
		Assert.assertNotNull(targetProduct, "Product not found: " + input.get("product"));

		productcatlog.addProductToCart(input.get("product"));

		CartPage cartPage = productcatlog.goToCartPage();

		Boolean match = cartPage.VarifyProductDisplay(input.get("product"));
		Assert.assertTrue(match, "Product is not displayed in cart.");

		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.SelectCounty("India");

		ConfirmationPage confimastionPage = checkoutpage.submitOrder();

		String confimastionMessage = confimastionPage.verifyConfrimastionMessage();
		Assert.assertTrue(confimastionMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(confimastionMessage);
	}

	/*public void SubmitOrder(HashMap<String, String> input) throws InterruptedException, IOException {


		ProductCatloag productcatlog = landlingPage.LoginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productcatlog.getProductList();
		productcatlog.addProductToCart(input.get("product"));
		CartPage cartPage = productcatlog.goToCartPage();

		Boolean match = cartPage.VarifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.SelectCounty("India");

		ConfirmationPage confimastionPage = checkoutpage.submitOrder();

		String confimastionMessage = confimastionPage.verifyConfrimastionMessage();
		Assert.assertTrue(confimastionMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(confimastionMessage);
	}*/
	/*@Test (dependsOnMethods = "SubmitTestOrder")
	public void OrderHistoryTest()  {
		ProductCatloag productcatlog = landlingPage.LoginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderPage = productcatlog.goToOrdersPage();
		Assert.assertTrue(orderPage.goToOrdersPage().VerifyOrderDisplay(ProductName));

	}
*/
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Ecommerce\\data\\Purches.Json");
		return new Object[][] {{data.get(0)},{data.get(1)} };

		/*HashMap<String, String> map = new HashMap<String,String>();
		map.put("email", "anshika@gmail.com");
		map.put("password", "Iamking@000");
		map.put("product", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product", "ADIDAS ORIGINAL");
		return new Object[][] {{map},{map1} };*/

	}
	/*Public void getScreenshot(){
		TakesScreenShot ts =  (TakesScreenShot)driver;
		File source= ts.getScreenshotAs(OutputTpe.FILE);
		File file = new File(System.getProperty("user.dir")) + "//reports//" +testCaseName +".png");
return System.getProperty("user.dir") +"//reports//" +testCaseName +".png");

	}
	*/



}
