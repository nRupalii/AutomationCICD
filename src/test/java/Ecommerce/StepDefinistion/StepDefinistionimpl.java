package Ecommerce.StepDefinistion;

import Ecommeres.TestCompounts.BaseTest;
import Ecommeres.pageobjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinistionimpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatloag productCatloag;
    public ConfirmationPage confimastionPage;
    @Given("I landed on Ecommerce Page")
    public void iLandedOnEcommercePage() throws InterruptedException, IOException {
        landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void loggedInWithUsernameAndPassword(String username, String password) throws InterruptedException, IOException {
        productCatloag = landlingPage.LoginApplication(username,password);
    }
    @When("^I add product (.+) to cart$")
    public void iAddProductToCart(String productName) throws InterruptedException, IOException {
        List<WebElement> products = productCatloag.getProductList();
        productCatloag.addProductToCart(productName);
    }

    @When("^checkout (.+) and submit the order$")
    public void checkoutSubmitOrder(String productName){
        CartPage cartPage = productCatloag.goToCartPage();

        Boolean match = cartPage.VarifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutpage = cartPage.goToCheckout();
        checkoutpage.SelectCounty("India");

        confimastionPage = checkoutpage.submitOrder();

    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void messageIsDisplayedOnConfirmationPage(String string) throws InterruptedException, IOException {
        String confimastionMessage = confimastionPage.verifyConfrimastionMessage();
        Assert.assertTrue(confimastionMessage.equalsIgnoreCase(string));
        System.out.println(confimastionMessage);
    }
    @Then ("{string} message is displayed")
    public void messageIsDisplayed(String string) throws InterruptedException, IOException {

        String ErrorMassage = driver.findElement(By.xpath("//div[@aria-label='Incorrect email or password.']")).getText();
        Assert.assertTrue(landlingPage.getErrorMessage().contains("Incorrect email"));
        //Assert.assertEquals("Incorrect email or ", landlingPage.getErrorMessage());
        System.out.println(ErrorMassage);
    }
}
