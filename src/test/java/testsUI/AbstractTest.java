package testsUI;

import com.codeborne.selenide.Selenide;
import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static org.example.utils.DriverUtil.setCapabilities;
import static org.example.utils.MyLogger.getLogger;

public abstract class AbstractTest {
    private static Map<String, String> testDescription = new HashMap<>();

    @BeforeClass
    public void setDriverCapabilities() {
        Selenide.clearBrowserCookies();
        setCapabilities();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult testResult, Object[] params) {
        updateTestDescription(testResult, params);
        getLogger().info("=== START TEST " + testResult.getMethod().getDescription() + " ====");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        getLogger().info("***TEST " + testResult.getMethod().getDescription() +
                " Status: " + testResult.getStatus() + " TEST***");
        getLogger().info("===== END TEST " + testResult.getMethod().getDescription() + " =====");
    }

    protected String getRandomString() {
        return RandomStringUtils.random(5, true, false);
    }

    private void updateTestDescription(ITestResult testResult, Object[] params) {
        val testName = testResult.getMethod().getMethodName();
        if (testResult.getMethod().getDescription() != null)
            testResult.getMethod().setDescription(testResult.getMethod().getDescription());
        if (testResult.getMethod().getDescription() != null && testResult.getMethod().getDescription().matches("(?i).*\\{\\d\\}.*"))
            testDescription.put(testName, testResult.getMethod().getDescription());
        if (testDescription.containsKey(testName)) {
            String newDescription = testDescription.get(testName);
            for (int i = 0; i < params.length; i++) {
                newDescription = newDescription.replace("{" + i + "}", "" + (params[i]));
            }
            testResult.getMethod().setDescription(newDescription);
        }
    }
}