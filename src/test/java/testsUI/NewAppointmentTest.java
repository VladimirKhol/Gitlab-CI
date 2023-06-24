package testsUI;

import com.codeborne.selenide.Condition;
import org.example.pages.LoginPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NewAppointmentTest extends AbstractTest {
    @Test(description = "")
    public void test() {
        openApp();
        new LoginPage()
                .enterEmail()
                .enterPassword()
                .logIn();
    }

    @Test
    public void test2() {
        open("https://www.google.com/");
        $("[alt='Google']").shouldBe(Condition.exist);
    }
}
