package tests;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.listeners.TestListener;

import java.lang.reflect.Method;

import static utils.extentreports.ExtentTestManager.startTest;

@Listeners({ TestListener.class })
@Epic("Страница ролевых игр")
@Feature("Работа со страницой ролевых игр")
public class RPGTest extends BaseTest {

    @Test(priority = 0, description = "Проверка изменения валюты")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Изменения валюты при нажатии на определенную валюту")
    @Story("Пользователь нажимает на кнопку валюты, выбирает другую валюту")
    public void checkCurrency(Method method) {
        startTest(method.getName(), "Проверка изменения валюты на странице ролевых игр");
        rpgPage.goToRPGPage().checkCurrency();

    }
    @Test(priority = 0, description = "Проверка изменения цвета при наведении на кнопку Все")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Изменения цвета при наведении на кнопку Все")
    @Story("Пользователь наводит курсор на кнопку")
    public void checkButtonAll(Method method) {
        startTest(method.getName(), "Проверка изменения цвета при наведении на кнопку Все на странице ролевых игр");
        rpgPage.goToRPGPage().checkButtonAll();

    }
}
