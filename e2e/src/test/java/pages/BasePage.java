package pages;

import static com.codeborne.selenide.Selenide.$;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class BasePage {

	public Logger log = Logger.getLogger("devpinoyLogger");

	public void clickElement(By locator) {
		$(locator).click();
	}

	public void selectValueFromDropDown(By locator, String value) {
		$(locator).selectOption(value);
	}

	public void setValueToElement(By locator, String value) {
		$(locator).setValue(value);
	}

	public String getTextOfElement(By locator) {
		return $(locator).getText();
	}

	public String getValueOfElement(By locator) {
		return $(locator).getValue();
	}

	public Boolean isElementPresent(By locator) {
		return $(locator).exists();
	}

//	This should work on any OS as opposed to CTRL+A + DEL which is faster but works only on Win
	public void clearValueOfElement(By locator) {
		while (getValueOfElement(locator).length() != 0) {
			$(locator).sendKeys(Keys.BACK_SPACE);
		}
	}

}
