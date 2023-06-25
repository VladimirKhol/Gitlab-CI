package org.example.utils;

import com.codeborne.selenide.Configuration;

public class DriverUtil {
    public static void setCapabilities() {
        Configuration.browserSize = "1440x900";
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = true;
        Configuration.browserCapabilities.setCapability("acceptInsecureCerts", true);
    }
}