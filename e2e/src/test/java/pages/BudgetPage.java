package pages;

import org.openqa.selenium.By;

public class BudgetPage extends BasePage {

	private final By budgetTab = By.linkText("Budget");
	private final By categoryDropDown = By.name("categoryId");
	private final By description = By.name("description");
	private final By ammountValue = By.name("value");
	private final By addButton = By.xpath("//*[contains(text(),'Add')]");
	private final By totalInflow = By.xpath("//div[contains(text(),'Total Inflow')]/preceding::div[1]");
	private final By totalOutflow = By.xpath("//div[contains(text(),'Total Outflow')]/preceding::div[1]");
	private final By workingBalance = By.xpath("//div[contains(text(),'Working Balance')]/preceding::div[1]");
	private final By updateButton = By.xpath("//button[contains(text(),'Update')]");
	private final By updateCategory = By.xpath("//button[contains(text(),'Update')]/preceding::div[3]//select");
	private final By updateDescription = By.xpath("//button[contains(text(),'Update')]/preceding::div[2]//input[1]");
	private final By updateValue = By.xpath("//button[contains(text(),'Update')]/preceding::div[1]//input[1]");

	public void clickBudgetTab() {
		log.debug("Clicking on Budget Tab");
		clickElement(budgetTab);
	}

	public void selectFromCategoryDropDown(String categoryValue) {
		log.debug("Selecting value " + categoryValue + " from category dropdown");
		selectValueFromDropDown(categoryDropDown, categoryValue);
	}

//	Because of the issue https://github.com/SeleniumHQ/selenium/issues/6741 we need this work-around
	public void clearDescription() {
		log.debug("Clear description value");
		clearValueOfElement(description);
	}

	public void setDescription(String descriptionValue) {
		log.debug("Set value " + descriptionValue + " to description");
		setValueToElement(description, descriptionValue);
	}

//	Because of the issue https://github.com/SeleniumHQ/selenium/issues/6741 we need this work-around
	public void clearAmmount() {
		log.debug("Clear ammount value");
		clearValueOfElement(ammountValue);
	}

	public void setAmmountValue(String ammount) {
		log.debug("Set value " + ammount + " to ammount");
		setValueToElement(ammountValue, ammount);
	}

	public void updateCategoryDropDown(String categoryValue) {
		log.debug("Updating to new value " + categoryValue + " in category dropdown");
//		For some reasons those elements cannot be updated the usual way so I've done this using Keyboard Keys
		selectValueFromDropDown(updateCategory, categoryValue);
	}

	public void updateDescription(String descriptionValue) {
		log.debug("Update to new value " + descriptionValue + " to description");
		setValueToElement(updateDescription, descriptionValue);
	}

	public void updateAmmountValue(String ammount) {
		log.debug("Update to new value " + ammount + " to ammount");
		setValueToElement(updateValue, ammount);
	}

	public void clickAddButton() {
		log.debug("Clicking on Add Button");
		clickElement(addButton);
	}

	public void clickUpdateButton() {
		log.debug("Clicking on Update Button");
		clickElement(updateButton);
	}

	public void clickRowWithDescriptionValue(String descriptionValue) {
		log.debug("Clicking on row with description value of " + descriptionValue);
		clickElement(By.xpath("//*[contains(text(),'" + descriptionValue + "')]"));
	}

	public String getValueOfTotalInflow() {
		log.debug("Returning the value of Total Inflow");
		return getTextOfElement(totalInflow);
	}

	public String getValueOfTotalIOutflow() {
		log.debug("Returning the value of Total Outflow");
		return getTextOfElement(totalOutflow);
	}

	public String getValueOfWorkingBalance() {
		log.debug("Returning the value of Working Balance");
		return getTextOfElement(workingBalance);
	}

	public Boolean isBudgetRowWithValuePresent(String categoryValue) {
		log.debug("Checking if category with value " + categoryValue + " exists");
		return isElementPresent(By.xpath("//*[contains(text(),'" + categoryValue + "')]"));
	}

}
