package testsUI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.val;
import org.example.utils.PropertiesContext;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static org.example.utils.MyLogger.getLogger;

public abstract class AbstractTest {
    private static Map<String, String> testDescriptions = new HashMap<>();
    protected static PropertiesContext context = PropertiesContext.getInstance();

    @BeforeClass
    public void setHeadless() {
        Configuration.headless = true;
    }

    @Step("Open application")
    public void openApp() {
        Configuration.browserCapabilities.setCapability("acceptInsecureCerts", true);
        open(context.getProperty("app.url"));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult testResult, Object[] params) {
        //WebDriverWrapper.setWebDriver();
        updateTestDescription(testResult, params);
        getLogger().info("=== START TEST " + testResult.getMethod().getDescription() + " ====");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        getLogger().info("***TEST " + testResult.getMethod().getDescription() +
                " Status: " + testResult.getStatus() + " TEST***");
        getLogger().info("===== END TEST " + testResult.getMethod().getDescription() + " =====");
        //WebDriverRunner.getWebDriver().quit();
    }

    private void updateTestDescription(ITestResult testResult, Object[] params) {
        val testName = testResult.getMethod().getMethodName();
        if (testResult.getMethod().getDescription() != null)
            testResult.getMethod().setDescription(testResult.getMethod().getDescription());
        if (testResult.getMethod().getDescription() != null && testResult.getMethod().getDescription().matches("(?i).*\\{\\d\\}.*"))
            testDescriptions.put(testName, testResult.getMethod().getDescription());
        if (testDescriptions.containsKey(testName)) {
            String newDescription = testDescriptions.get(testName);
            for (int i = 0; i < params.length; i++) {
                newDescription = newDescription.replace("{" + i + "}", "" + (params[i]));
            }
            testResult.getMethod().setDescription(newDescription);
        }
    }
}