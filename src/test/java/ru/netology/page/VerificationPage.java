package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        fillVerificationForm(verificationCode);
        return new DashboardPage();
    }

    public void invalidVerify(String invalidCode) {
        fillVerificationForm(invalidCode);
        errorNotification.shouldHave(Condition.text("Неверно указан код! Попробуйте ещё раз."));
        errorNotification.shouldHave(Condition.text("Трижды неверно указан код! Система заблокирована."));
    }
    private void fillVerificationForm(String code) {
        codeField.setValue(code);
        verifyButton.click();
    }
}