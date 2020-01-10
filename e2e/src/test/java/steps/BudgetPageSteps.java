package steps;

import org.testng.Assert;

import base.TestBase;
import pages.BudgetPage;
import utilities.NumUtils;

public class BudgetPageSteps extends TestBase {

	BudgetPage budgetPage = new BudgetPage();
	private Double initialInflow;
	private Double initialOutflow;
	private Double workingBalance;

	public void givenFocusOnBudgetTab() {
		budgetPage.clickBudgetTab();
	}

	public void andInitialValuesForTotalInflowOutflowAndBalance() {
		initialInflow = NumUtils.dollarsToNumeric(budgetPage.getValueOfTotalInflow());
		initialOutflow = NumUtils.dollarsToNumeric(budgetPage.getValueOfTotalIOutflow());
		workingBalance = NumUtils.dollarsToNumeric(budgetPage.getValueOfWorkingBalance());
	}

	public void andRowWithDescriptionValueIsClicked(String category) {
		budgetPage.clickRowWithDescriptionValue(category);
	}

	public void andCategoryIsSelected(String category) {
		budgetPage.selectFromCategoryDropDown(category);
	}

	public void andCategoryIsUpdated(String category) {
		budgetPage.updateCategoryDropDown(category);
	}

	public void andDescriptionIsAdded(String description) {
		budgetPage.setDescription(description);
	}

	public void andDescriptionIsUpdated(String description) {
		budgetPage.clearDescription();
		budgetPage.updateDescription(description);
	}

	public void andAmmountValueIsAdded(String ammount) {
		budgetPage.setAmmountValue(ammount);
	}

	public void andAmmountValueIsUpdated(String ammount) {
		budgetPage.clearAmmount();
		budgetPage.updateAmmountValue(ammount);
	}

	public void whenAddButtonIsClicked() {
		budgetPage.clickAddButton();
	}

	public void whenUpdateButtonIsClicked() {
		budgetPage.clickUpdateButton();
	}

	public void thenABudgetRowIsExistsWithValues(String category, String description, String ammount) {
		Assert.assertTrue(budgetPage.isBudgetRowWithValuePresent(category));
		Assert.assertTrue(budgetPage.isBudgetRowWithValuePresent(description));
		Assert.assertTrue(budgetPage.isBudgetRowWithValuePresent(ammount));
	}

//	Note that this will work only when adding new rows and for some updates (for demo purposes). Additional logic needs to be added to cover all update cases
	public void andValuesForTotalInflowOutflowAndBalanceUpdatedCorrectly(Double updateValue) {
		if (updateValue >= 0) {
			Assert.assertEquals(initialInflow + updateValue,
					NumUtils.dollarsToNumeric(budgetPage.getValueOfTotalInflow()),
					"Wrong value for Initital Inflow");
			Assert.assertEquals(workingBalance + updateValue,
					NumUtils.dollarsToNumeric(budgetPage.getValueOfWorkingBalance()),
					"Wrong value for Working Balance");
		} else {
			Assert.assertEquals(initialOutflow - updateValue,
					NumUtils.dollarsToNumeric(budgetPage.getValueOfTotalIOutflow()),
					"Wrong value for Initital Outflow");
			// Math is used bellow to round unexpected trailing decimals resulted in
			// subtract operations on Double
			Assert.assertEquals(Math.round((workingBalance + updateValue) * 100.0) / 100.0,
					NumUtils.dollarsToNumeric(budgetPage.getValueOfWorkingBalance()),
					"Wrong value for Working Balance");
		}

	}
}
