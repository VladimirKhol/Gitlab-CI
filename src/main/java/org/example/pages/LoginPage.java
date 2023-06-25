package org.example.pages;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.example.utils.PropertiesContext;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;
import static org.example.utils.MyLogger.getLogger;

public class LoginPage {
    private final static String LOGO = "#logo";
    private final static String EMAIL_ADDRESS_INPUT = "#username";
    private final static String PASSWORD_INPUT = "#password";
    private final static String LOGIN_BUTTON = "//button[contains(@class, 'button_primary')]";
    private final PropertiesContext context = PropertiesContext.getInstance();

    @SneakyThrows
    public LoginPage() {
        open(context.getProperty("app.url"));
        $(LOGO).shouldBe(exist);
    }

    @Step("Enter email")
    public LoginPage enterEmail() {
        $(EMAIL_ADDRESS_INPUT).setValue(context.getProperty("email.address"));
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword() {
        $(PASSWORD_INPUT).setValue(context.getProperty("password"));
        return this;
    }

    @Step("Click LogIn")
    public MainPage logIn() {
        $x(LOGIN_BUTTON).click();
        return new MainPage();
    }
}