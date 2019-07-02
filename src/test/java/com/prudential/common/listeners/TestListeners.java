package com.prudential.common.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.prudential.common.utilities.Logger;
import com.prudential.stepdefs.common.TestRunner;
/**
 * Suite listener
 * @author Abhijit Bhattacharyya
 *
 */



public class TestListeners extends TestRunner implements ITestListener {
	

	
    @Override
    public void onFinish(final ITestContext context) {
    	Logger.logConsoleMessage("========TEST FINISH========");
    }

    @Override
    public void onStart(final ITestContext test) {
    	Logger.logConsoleMessage("========TEST START========");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {

    }

    @Override
    public void onTestFailure(final ITestResult result) {
        teardownTest(result);
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        teardownTest(result);
    }

    @Override
    public void onTestStart(final ITestResult result) {
       
    }

    @Override
    public void onTestSuccess(final ITestResult result) {
        teardownTest(result);
    }

    private void teardownTest(ITestResult result) {
        String status = result.isSuccess() ? "SUCCESS" : "FAILURE";
        Logger.logConsoleMessage("======" + status + "======");
        Logger.logConsoleMessage("Test: " + result.getInstanceName() + "." + result.getName());

        // attach screenshot
//        AllureManager.attachScreenshot();
        //AllureManager.attachSeleniumLog(Config.SELENIUM_LOG_PATH);
        //AllureManager.attachVideo(Config.SELENIUM_LOG_PATH);
    }


    
}
