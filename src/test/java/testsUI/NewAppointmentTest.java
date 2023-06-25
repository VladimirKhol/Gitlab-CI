package testsUI;

import lombok.val;
import org.example.pages.LoginPage;
import org.example.popups.appointment.CreateNewAppointmentPopup;
import org.example.popups.appointment.DatePickerPopup;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class NewAppointmentTest extends AbstractTest {
    final String TITLE = "Title " + getRandomString();

    @Test(description = "e2e. Create a new Appointment and verify in Calendar")
    public void createAppointmentTest() {
        val customer = "Abigale Spencer";
        val day = LocalDate.now().getDayOfMonth() + 1;
        new LoginPage()
                .enterEmail()
                .enterPassword()
                .logIn()
                .selectNewAppointment()
                .setTitle(TITLE)
                .selectType(CreateNewAppointmentPopup.AppointmentType.NONE)
                .selectCustomer(customer)
                .selectStatus(CreateNewAppointmentPopup.StatusType.REQUEST_MADE)
                .setDateAndTime(day, day, DatePickerPopup.Time.TWELVE_PM, DatePickerPopup.Time.ONE_PM)
                .details("The meeting was scheduled")
                .create()
                .goToCalendar()
                .checkAppointmentDetailsByTitle(TITLE, customer, ", 12pm")
                .mainPage()
                .logOut();
    }

    @Test(description = "e2e. Decline appointment and verify in Calendar", dependsOnMethods = {"createAppointmentTest"})
    public void declineAppointmentTest() {
        new LoginPage()
                .enterEmail()
                .enterPassword()
                .logIn()
                .goToScheduler()
                .declineAppointment(TITLE)
                .confirmDecline()
                .mainPage()
                .goToCalendar()
                .isAppointmentByTitleExists(TITLE, false)
                .mainPage()
                .logOut();
    }
}