package base;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

public class SetupWebDriver {

	private WebDriver driver;
	private String browser;

	public SetupWebDriver(String browser, String seleniumHubUrl) throws MalformedURLException {
		this.browser = browser;
		if (this.browser.equalsIgnoreCase("firefox_docker")) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			this.driver = new RemoteWebDriver(new URL(seleniumHubUrl), firefoxOptions);
			WebDriverRunner.setWebDriver(this.driver);
			
		} else if (this.browser.equalsIgnoreCase("chrome_docker")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			this.driver = new RemoteWebDriver(new URL(seleniumHubUrl), chromeOptions);
			WebDriverRunner.setWebDriver(this.driver);
		} 
		Configuration.startMaximized = true;
		Configuration.screenshots = false;
		Configuration.savePageSource = false;

	}

	public String getBrowser() {
		return this.browser;
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}

}
