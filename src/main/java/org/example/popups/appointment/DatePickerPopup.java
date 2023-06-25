package org.example.popups.appointment;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DatePickerPopup {
    private final static String DAY_TO_SELECT = "//div[contains(@class, 'react-datepicker__day--00%s') and not(contains(@class, 'outside'))]";
    private final static String TIME = "[class*='dataPicker'] .react-datepicker-wrapper";
    private final static String TIME_TO_SELECT = "[class*='dataPicker'] .react-datepicker__time-list li";

    @Step("Select day {day}")
    public DatePickerPopup selectDay(int day) {
        if (day < 10)
            $$x(String.format(DAY_TO_SELECT, day)).filter(visible).first().click();
        else
            $$x(String.format(DAY_TO_SELECT.replace("--00", "--0"), day)).filter(visible).first().click();

        return this;
    }

    @Step("Set start time {time}")
    public DatePickerPopup setStartTime(Time time) {
        setTime(0, time);
        return this;
    }

    @Step("Set end time {time}")
    public DatePickerPopup setEndTime(Time time) {
        setTime(1, time);
        return this;
    }

    private void setTime(int startOrEnd, Time time) {
        $$(TIME).get(startOrEnd).shouldBe(visible).click();
        $$(TIME_TO_SELECT).filter(text(time.t)).first().click();
    }

    public enum Time {
        TWELVE_PM("12:00 PM"),
        ONE_PM("1:00 PM");
        private final String t;

        Time(String t) {
            this.t = t;
        }
    }
}
