package org.example.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static java.lang.Boolean.parseBoolean;

public class WebDriverWrapper {
    private static final PropertiesContext context = PropertiesContext.getInstance();

//    public static void setWebDriver() {
//        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", chromeOptionsMap);
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--ignore-certificate-errors");
//        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
//            put("enableVNC", true);
//            put("enableVideo", parseBoolean(PropertiesContext.getInstance().getProperty("selenoid.video")));
//        }});
//        RemoteWebDriver driver = null;
//        try {
//            driver = new RemoteWebDriver(new URL(Configuration.remote), options);
//            System.out.println(driver.getSessionId());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        assert driver != null;
//        driver.setFileDetector(new LocalFileDetector());
//        setWindowSize(driver);
//        WebDriverRunner.setWebDriver(driver);
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
//                .screenshots(true)
//                .savePageSource(true));
//    }
//
//    public static void setWindowSize(WebDriver driver) {
//        Dimension windowSize = driver.manage().window().getSize();
//        WebElement html = driver.findElement(By.tagName("html"));
//        int innerWidth = Integer.parseInt(html.getAttribute("clientWidth"));
//        int innerHeight = Integer.parseInt(html.getAttribute("clientHeight"));
//        driver.manage().window().setSize(new Dimension(
//                windowSize.getWidth() - innerWidth + Integer.parseInt(context.getProperty("browser.width")),
//                windowSize.getHeight() - innerHeight + Integer.parseInt(context.getProperty("browser.height"))));
//    }
}