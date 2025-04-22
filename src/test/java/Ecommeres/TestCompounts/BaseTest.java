package Ecommeres.TestCompounts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Ecommeres.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

	public static WebDriver driver;
	public LandingPage landlingPage;

	public static WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\rupalnar\\eclipse-workspace\\EcommeresWebsite\\src\\main\\java\\Ecommeres\\resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null
				? System.getProperty("browser")
				: prop.getProperty("browser");

		System.out.println("Browser name is: " + browserName);

		if (browserName.toLowerCase().contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-cache");

			if (browserName.toLowerCase().contains("headless")) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
			}

			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Add firefox logic
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Unsupported browser: " + browserName);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}


	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//Read Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		//String to HashMap - Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

		});
		return data;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}

	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landlingPage = new LandingPage(driver);
		landlingPage.goTo();
		return landlingPage;
	}

	//@AfterMethod
	public void closedriver() {
		driver.close();
	}

}