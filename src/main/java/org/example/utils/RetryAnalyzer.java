package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.atomic.AtomicInteger;

import static org.example.utils.MyLogger.getLogger;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int TRY_MAX = Integer.parseInt(PropertiesContext.getInstance().getProperty("retries"));
    private AtomicInteger tryCounter = new AtomicInteger(TRY_MAX);

    public boolean isRetryAvailable() {
        return (tryCounter.intValue() > 0);
    }

    @Override
    public boolean retry(ITestResult iTestResult) {
        boolean retryAgain = false;
        if (isRetryAvailable()) {
            getLogger().info("Going to retry test case: " + iTestResult.getMethod().getDescription() + ", " + ((
                    (TRY_MAX - tryCounter.intValue()) + 1)) + " out of " + TRY_MAX);
            retryAgain = true;
            tryCounter.decrementAndGet();
        }
        return retryAgain;
    }
}
