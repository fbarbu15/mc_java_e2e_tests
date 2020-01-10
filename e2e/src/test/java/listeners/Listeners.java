package listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

	private Logger log;

	public void onTestStart(ITestResult result) {
		log = (Logger) result.getAttribute("log");
		log.debug("Starting test <<<<<<<<<<<<<<< " + result.getName() + " >>>>>>>>>>>>>>>");
	}

	public void onTestSuccess(ITestResult result) {
		log.debug("Test Successfull!!");
	}

	public void onTestFailure(ITestResult result) {
		log.debug("Test failed!! Check screenshot!!");
	}

	public void onTestSkipped(ITestResult result) {
		log.debug("Test Skipped!!");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}