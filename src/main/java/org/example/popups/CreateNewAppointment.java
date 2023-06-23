package org.example.popups;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreateNewAppointment {
    private final static String APPOINTMENT_TITLE = "[name='title']";


    @Step("Set title to {value}")
    public CreateNewAppointment setTitle(String value) {
        $(APPOINTMENT_TITLE).shouldBe(enabled).setValue(value);
        return this;
    }

    @Step("Select Appointment type {type}")
    public CreateNewAppointment selectType(AppointmentType type) {
        $x(type.type).shouldBe(visible).click();
        return this;
    }

    public enum AppointmentType {
        WAITING("//div[contains(text(), 'Waiting')]"),
        DROPPING_OFF("//div[contains(text(), 'Dropping Off')]"),
        NONE("//div[contains(text(), 'None')]");
        private final String type;

        AppointmentType(String type) {
            this.type = type;
        }
    }
}
