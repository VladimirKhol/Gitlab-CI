package org.example.utils;

import com.codeborne.selenide.Configuration;

public class DriverUtil {
    public static void setCapabilities() {
        Configuration.pageLoadTimeout = 120000;
        Configuration.browserSize = "1440x900";
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = true;
        Configuration.browserCapabilities.setCapability("acceptInsecureCerts", true);
    }
}