package org.example.popups.scheduler;

import io.qameta.allure.Step;
import org.example.pages.SchedulerPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DeclineAppointmentPopup {
    private final static String BUTTON_YES = "[class*='modalWrapper__'] [class*='button_primary']";

    @Step("Click 'Yes, Decline'")
    public SchedulerPage confirmDecline() {
        $(BUTTON_YES).shouldBe(visible).click();
        $(BUTTON_YES).shouldNotBe(visible);
        return new SchedulerPage();
    }
}