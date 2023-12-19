package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.User;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");
    private SelenideElement loginSub = $("[data-test-id=login] .input__sub");
    private SelenideElement passwordSub = $("[data-test-id=password] .input__sub");

    private void fillLoginForm(String login, String password) {
        loginField.sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        loginField.val(login);
        passwordField.val(password);
        loginButton.click();
    }

    public VerificationPage validLogin(User user) {
        fillLoginForm(user.getLogin(), "qwerty123");
        return new VerificationPage();
    }

    public void invalidLogin(User user) {
        fillLoginForm(user.getLogin(), user.getPassword());
        errorNotification.shouldHave(Condition.text("Неверно указан логин или пароль"));
        errorNotification.shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    public void emptyLogin(User user) {
        fillLoginForm(user.getLogin(), user.getPassword());
        loginSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void emptyPassword(User user) {
        fillLoginForm(user.getLogin(), user.getPassword());
        passwordSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
}
