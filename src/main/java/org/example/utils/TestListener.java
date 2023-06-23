package org.example.utils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static org.example.utils.MyLogger.getLogger;

/**
 * The primary purpose of this class is to listen to the events in a script and modify TestNG behavior accordingly
 */
public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        getLogger().info("<<<<START test: " + result.getMethod().getDescription() + " >>>>");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        getLogger().info("<<<<Test: " + result.getMethod().getDescription() + " passed >>>>");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        getLogger().info("<<<<Test: " + result.getMethod().getDescription() + " failed >>>>");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        super.onFinish(context);
    }
}