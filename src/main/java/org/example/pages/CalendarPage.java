package org.example.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarPage {
    private final static String CALENDAR_APPOINTMENT_INFO = "[title='%s'] [class^='eventInfo'] span";

    @Step("Verify that appointment {title} was created")
    public CalendarPage checkAppointmentDetailsByTitle(String title, String... expectedDetails) {
        $$(String.format(CALENDAR_APPOINTMENT_INFO, title)).shouldHave(texts(expectedDetails));
        return this;
    }

    @Step("Verify if an appointment {title} exists")
    public CalendarPage isAppointmentByTitleExists(String title, boolean isExist) {
        $(String.format(CALENDAR_APPOINTMENT_INFO.replace(" span", ""), title))
                .shouldBe(isExist ? exist : not(exist));
        return this;
    }

    public MainPage mainPage() {
        return new MainPage();
    }
}
