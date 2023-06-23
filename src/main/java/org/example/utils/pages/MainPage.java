package org.example.utils.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private static final String HEADER_ADD_BTN = "//div[contains(@class, 'HeaderAddButton')]";
    private static final String NEW_APPOINTMENT = "//p[contains(text(), 'New Appointment')]";

    @Step("Select New Appointment")
    public MainPage selectNewAppointment() {
        $x(HEADER_ADD_BTN).shouldBe(visible).click();
        $x(NEW_APPOINTMENT).shouldBe(visible).click();
        return this;
    }
}
