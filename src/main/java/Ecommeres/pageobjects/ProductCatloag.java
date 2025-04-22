package Ecommeres.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecommeres.AbstractCompounts.AbstractCompounts;

public class ProductCatloag extends AbstractCompounts {

	WebDriver driver;

	public ProductCatloag(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = "ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		List<WebElement> products = getProductList();
		System.out.println("Searching for product: " + productName);

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst()
				.orElse(null);

		if (prod == null) {
			System.out.println("Product NOT FOUND: " + productName);
		} else {
			System.out.println("Product FOUND: " + productName);
		}

		return prod;
	}


	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);

		if (prod == null) {
			throw new RuntimeException("Product not found: " + productName);
		}

		try {
			prod.findElement(addToCart).click();
			waitForElementToAppear(toastMessage);
			waitForElementToDisapper(spinner);
		} catch (Exception e) {
			System.out.println("Error adding product to cart: " + e.getMessage());
			throw e;
		}
	}

	/*public WebElement getProductByName(String ProductName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String ProductName) {
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		// Reuse the Wait Element
		waitForElementToAppear(toastMessage);
		waitForElementToDisapper(spinner);

	}*/

}
