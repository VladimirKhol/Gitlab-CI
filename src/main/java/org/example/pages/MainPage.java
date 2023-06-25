package org.example.pages;

import io.qameta.allure.Step;
import org.example.popups.appointment.CreateNewAppointmentPopup;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private static final String HEADER_ADD_BTN = "//div[contains(@class, 'HeaderAddButton')]";
    private static final String NEW_APPOINTMENT = "//p[contains(text(), 'New Appointment')]";
    private static final String SIDE_BAR_TOOLS = "//div[contains(@class, 'linkWrapperNarrowed') or contains(@class, 'linkWrapper_')]";
    private static final String SIDE_BAR_CALENDAR = "//div[contains(text(), 'Calendar')]";
    private static final String SIDE_BAR_SCHEDULER = "//div[contains(text(), 'Scheduler')]";
    private static final String PROFILE_ICON = "[class^='ProfileControl'] [class*='icon']";
    private static final String LOG_OUT = "//li[contains(@class, 'ProfileControlActions')]//p[contains(text(), 'Log Out')]/..";

    @Step("Select New Appointment")
    public CreateNewAppointmentPopup selectNewAppointment() {
        $x(HEADER_ADD_BTN).shouldBe(visible).click();
        $x(NEW_APPOINTMENT).shouldBe(visible).click();
        return new CreateNewAppointmentPopup();
    }

    @Step("Go to Calendar")
    public CalendarPage goToCalendar() {
        $$x(SIDE_BAR_TOOLS).get(1).hover().click();
        $x(SIDE_BAR_CALENDAR).shouldBe(visible).click();
        return new CalendarPage();
    }

    @Step("Go to Scheduler")
    public SchedulerPage goToScheduler() {
        $$x(SIDE_BAR_TOOLS).get(1).hover().click();
        $x(SIDE_BAR_SCHEDULER).shouldBe(visible).click();
        return new SchedulerPage();
    }

    @Step("Log Out")
    public void logOut() {
        $(PROFILE_ICON).shouldBe(visible).click();
        $x(LOG_OUT).shouldBe(visible).click();
        $x(LOG_OUT).shouldNotBe(visible);
    }
}