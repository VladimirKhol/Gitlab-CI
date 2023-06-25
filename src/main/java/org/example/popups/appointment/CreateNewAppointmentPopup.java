package org.example.popups.appointment;

import io.qameta.allure.Step;
import org.example.pages.MainPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateNewAppointmentPopup {
    private final static String APPOINTMENT_TITLE = "[name='title']";
    private final static String CUSTOMERS_DROPDOWN = "[name='customer'] [class$='control']";
    private final static String CUSTOMERS_LIST_ITEM = "[class$='MenuList'] [id*='react-select']";
    private final static String STATUS_DROPDOWN = "[name='status'] [class$='control']";
    private final static String STATUS_LIST_ITEM = "//div[contains(text(), '%s')]";
    private final static String DATE_PICKER = "[class='react-datepicker-wrapper']";
    private final static String DETAILS_AREA = "[class^='textField'] textarea";
    private final static String CREATE = "//button//span[contains(text(), 'Create')]";
    private final static String APPOINTMENT_TYPE = "//div[contains(text(), '%s')]";

    @Step("Set title to {value}")
    public CreateNewAppointmentPopup setTitle(String value) {
        $(APPOINTMENT_TITLE).shouldBe(enabled).setValue(value);
        return this;
    }

    @Step("Select Appointment type {type}")
    public CreateNewAppointmentPopup selectType(AppointmentType type) {
        $x(String.format(APPOINTMENT_TYPE, type.type)).shouldBe(visible).click();
        return this;
    }

    @Step("Select Customer {title}")
    public CreateNewAppointmentPopup selectCustomer(String title) {
        $(CUSTOMERS_DROPDOWN).shouldBe(visible).click();
        $$(CUSTOMERS_LIST_ITEM).filter(text(title)).filter(exist).first().click();
        return this;
    }

    @Step("Select Status {type}")
    public CreateNewAppointmentPopup selectStatus(StatusType type) {
        $(STATUS_DROPDOWN).shouldBe(visible).click();
        $$x(String.format(STATUS_LIST_ITEM, type.type)).first().click();
        return this;
    }

    @Step("Set start day {startDay}, end day {endDay}, start time {startTime}, end time {endTime}")
    public CreateNewAppointmentPopup setDateAndTime(int startDay, int endDay, DatePickerPopup.Time startTime, DatePickerPopup.Time endTime) {
        $(DATE_PICKER).shouldBe(visible).click();
        new DatePickerPopup()
                .selectDay(startDay)
                .selectDay(endDay)
                .setStartTime(startTime)
                .setEndTime(endTime);
        return this;
    }

    @Step("Set details {details}")
    public CreateNewAppointmentPopup details(String details) {
        $(DETAILS_AREA).sendKeys(details);
        return this;
    }

    @Step("Create")
    public MainPage create() {
        $x(CREATE).shouldBe(enabled).click();
        $x(CREATE).shouldNotBe(visible);
        return new MainPage();
    }

    public enum AppointmentType {
        WAITING("Waiting"),
        DROPPING_OFF("Dropping Off"),
        NONE("None");
        private final String type;

        AppointmentType(String type) {
            this.type = type;
        }
    }

    public enum StatusType {
        BOOKED("Booked"),
        REQUEST_MADE("Request Made"),
        MISSED("Missed"),
        FINISHED("Finished");
        private final String type;

        StatusType(String type) {
            this.type = type;
        }
    }
}