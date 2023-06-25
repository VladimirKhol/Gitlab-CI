package org.example.pages;

import io.qameta.allure.Step;
import org.example.popups.scheduler.DeclineAppointmentPopup;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SchedulerPage {
    private final static String ICON_DECLINE = "//div[contains(text(), '%s')]/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'declineIconWrapper')]";

    @Step("Decline {appointmentTitle}")
    public DeclineAppointmentPopup declineAppointment(String appointmentTitle) {
        $x(String.format(ICON_DECLINE, appointmentTitle)).shouldBe(visible, Duration.ofSeconds(30)).click();
        return new DeclineAppointmentPopup();
    }

    public MainPage mainPage() {
        return new MainPage();
    }
}
