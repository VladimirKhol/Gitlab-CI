package testsUI;

import org.example.utils.pages.LoginPage;
import org.testng.annotations.Test;

public class NewAppointmentTest extends AbstractTest {
    @Test(description = "")
    public void test() {
        openApp();
        new LoginPage()
                .enterEmail()
                .enterPassword()
                .logIn();
    }
}
