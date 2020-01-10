package base;

import static com.codeborne.selenide.Selenide.open;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import base.SetupWebDriver;
import io.qameta.allure.Attachment;
import java.util.Properties;

public class TestBase {

	protected Logger log = Logger.getLogger("devpinoyLogger");
	private static Properties config = new Properties();
	private static FileInputStream fis;
	private String baseUrl;
	private String seleniumHubUrl;
	private WebDriver driver;

	@BeforeSuite
	public void setUpSuite() throws IOException {
		log.debug("STARTING TEST SUITE");
		fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/config.properties");
		config.load(fis);
		fis.close();
		log.debug("Config file loaded");

	}

	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setUp(String browser, ITestResult result) throws MalformedURLException {
		seleniumHubUrl = config.getProperty("seleniumHubUrl");
		SetupWebDriver setupWebDriver = new SetupWebDriver(browser, seleniumHubUrl);
		if (setupWebDriver.getBrowser().matches(".+_docker")) {
			baseUrl = config.getProperty("baseUrlDocker");
		}
		else {
			baseUrl = config.getProperty("baseUrlLocal");
		}
		driver = setupWebDriver.getDriver();
		open(baseUrl);
		result.setAttribute("log", log);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			takeScreenshot();
		}
		if (driver != null) {
			driver.quit();
		}
	}

	@Attachment(value = "Screenshot when error occurs", type = "image/png")
	public byte[] takeScreenshot() throws IOException {
		File screenshot = Screenshots.takeScreenShotAsFile();
		return Files.toByteArray(screenshot);
	}
}
