package uiTests;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static service.assertions.Assertion.assertTrue;

public class AnotherTests extends AnotherBaseTest {

    @Test
    public static void yandexTest() {
        open("http://ya.ru");
        $(byAttribute("aria-label", "Запрос")).shouldBe(visible).setValue("microsoft").submit();
        $(byText("microsoft.com")).shouldBe(visible);
    }

    @Test
    public static void googlTestWithFail() {
        open("http://google.com");
        $(byName("q")).shouldBe(visible).setValue("microsoft").submit();
        $(byText("Microsoft - Official Home Page")).shouldNotBe(visible);
    }

    @Test
    public static void gmailHome() {
        open("http://gmail.com");
    }

    @Test
    public static void googleHomeWillFail() {
        open("http://google.com");
        assertTrue(false);
    }
}
