package tests;

import org.testng.annotations.Test;
import steps.BudgetPageSteps;

public class BudgetPageTests extends BudgetPageSteps {

	@Test()
	void addNewBudgetRow() {
		givenFocusOnBudgetTab();
		andInitialValuesForTotalInflowOutflowAndBalance();
		andCategoryIsSelected("School");
		andDescriptionIsAdded("Books for school");
		andAmmountValueIsAdded("-500");
		whenAddButtonIsClicked();
		thenABudgetRowIsExistsWithValues("School", "Books for school", "-$500.00");
		andValuesForTotalInflowOutflowAndBalanceUpdatedCorrectly(-500.00);
	}

	@Test()
	void editBudgetRowWithBigPositive() {
		givenFocusOnBudgetTab();
		andInitialValuesForTotalInflowOutflowAndBalance();
		andRowWithDescriptionValueIsClicked("Paycheck");
		andCategoryIsUpdated("Gifting");
		andDescriptionIsUpdated("Test");
		andAmmountValueIsUpdated("7000");
		whenUpdateButtonIsClicked();
		thenABudgetRowIsExistsWithValues("Gifting", "Test", "$7,000.00");
		andValuesForTotalInflowOutflowAndBalanceUpdatedCorrectly(1300.00);
	}

	@Test()
	void editBudgetRowWithBigNegative() {
		givenFocusOnBudgetTab();
		andInitialValuesForTotalInflowOutflowAndBalance();
		andRowWithDescriptionValueIsClicked("The usual weekly run");
		andAmmountValueIsUpdated("-7000");
		whenUpdateButtonIsClicked();
		thenABudgetRowIsExistsWithValues("Misc", "The usual weekly run", "-$7,000.00");
		andValuesForTotalInflowOutflowAndBalanceUpdatedCorrectly(-5900.00);
	}

}
