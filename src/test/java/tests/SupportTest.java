package tests;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.listeners.TestListener;

import java.lang.reflect.Method;

import static utils.extentreports.ExtentTestManager.startTest;

@Listeners({ TestListener.class })
@Epic("Страница поддержки")
@Feature("Работа со страницой поддержки")
public class SupportTest extends BaseTest {

    @Test(priority = 0, description = "Проверка изменения цвета при наведении на карточки (Продукты, Платежи ..)")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Изменения цвета при наведении на карточки (Продукты, Платежи ..)")
    @Story("Пользователь наводит курсор на карточку")
    public void checkCards(Method method) {
        startTest(method.getName(), "Проверка изменения цвета при наведении на карточки (Продукты, Платежи ..) на странице поддержки");

        supportPage.goToSupportPage()
                   .checkCards();
    }

    @Test(priority = 0, description = "Проверка раскрывающегося меню внизу страницы")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Пользователь кликает на элемент меню")
    @Story("Пользователь кликает на элемент меню")
    public void checkDropDownMenu(Method method) {
        startTest(method.getName(), "Проверка открытия элементов раскрывающегося меню");

        supportPage.goToSupportPage()
                .checkDropDownMenu();
    }

    @Test(priority = 0, description = "Проверка появления полей для ввода при клике на кнопку 'Напишите нам' внизу страницы")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка появления полей для ввода при клике на кнопку 'Напишите нам' внизу страницы")
    @Story("Проверка появления полей для ввода при клике на кнопку 'Напишите нам' внизу страницы")
    public void checkFeedbackButton(Method method) {
        startTest(method.getName(), "Проверка появления полей для ввода при клике на кнопку 'Напишите нам' внизу страницы");

        supportPage.goToSupportPage()
                .checkFeedbackButton();
    }

}
