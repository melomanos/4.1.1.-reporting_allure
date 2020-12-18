package ru.netology.web.test;

import com.codeborne.selenide.conditions.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.data.RegistrationInfo;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldRegisterSuccessful() {
        RegistrationInfo user = DataGenerator.Registration.generate("ru");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText(user.getDate())).waitUntil(visible, 3000);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(user.getOtherDate());
        $$("[type='button']").find(exactText("Запланировать")).click();
        $$("[type='button']").find(exactText("Перепланировать")).click();
        $(byText(user.getOtherDate())).waitUntil(visible, 3000);
    }

    @Test
    void shouldChooseCityFromDropDownList() {
        RegistrationInfo user = DataGenerator.Registration.generate("ru");
        $("[data-test-id='city'] input").setValue("Мо");
        $$(By.className("menu-item__control")).find(exactText("Смоленск")).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText(user.getDate())).waitUntil(visible, 3000);
    }

    @Test
    void shouldChooseDateFromCalendarApp() {
        RegistrationInfo user = DataGenerator.Registration.generate("ru");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $(By.className("icon_name_calendar")).click();
        $("[data-step='1']").doubleClick();
        $$(By.className("calendar__day")).find(exactText(DataGenerator.Registration.generateMeetingDateCalendarAppCut(5))).click();
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText(DataGenerator.Registration.generateMeetingDateCalendarApp(5))).waitUntil(visible, 3000);
    }

    @Test
    void shouldRegisterAllFieldsEmpty() {
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Поле обязательно для заполнения")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRegisterWrongCity() {
        RegistrationInfo user = DataGenerator.Registration.generate("ru");
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.generateCityWrong());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Доставка в выбранный город недоступна")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRegisterWrongDate() {
        RegistrationInfo user = DataGenerator.Registration.generate("ru");
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.generateCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(DataGenerator.Registration.generateMeetingDateWrong());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Заказ на выбранную дату невозможен")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRegisterWrongName() {
        RegistrationInfo user = DataGenerator.Registration.generate("ru");
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.generateCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.generateNameWrong());
        $("[data-test-id='date'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Имя и Фамилия указаные неверно")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRegisterWrongPhone() {
        // написать issue
        RegistrationInfo user = DataGenerator.Registration.generate("ru");
        $("[data-test-id='city'] input").setValue(DataGenerator.Registration.generateCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.generatePhoneWrong());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Телефон указан неверно")).waitUntil(visible, 3000);
    }

    @Test
    void shouldRegisterDoNotPushRadioButton() {
        RegistrationInfo user = DataGenerator.Registration.generate("ru");
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("[data-test-id='date'] input").setValue(user.getDate());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $$("[type='button']").find(exactText("Запланировать")).click();
        $$("[data-test-id='agreement'].input_invalid .checkbox__text")
                .find(new Text("Я соглашаюсь с условиями обработки и использования моих персональных данных"))
                .waitUntil(visible, 3000);
    }
}
