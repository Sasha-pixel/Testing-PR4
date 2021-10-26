package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.listeners.TestListener;

import java.lang.reflect.Method;

import static utils.extentreports.ExtentTestManager.startTest;

@Listeners({ TestListener.class })
@Epic("Главаная страница")
@Feature("Работа с главно")
public class HomeTest extends BaseTest {
    @Test(priority = 0, description = "Проверка слайдера на главной странице")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Переключение слайдера с помощью кнопок и булетов")
    @Story("Пользователь щёлкает на булеты и кнопки у слайдера")
    public void checkMainSlider(Method method) {
        startTest(method.getName(), "Проверка слайдера на главной странице");

        homePage.goToHome()
                .goToSlider()
                .checkNextButton()
                .checkPrevButton()
                .checkPaginationBullet();
    }

    @Test(priority = 0, description = "Проверка каталога на главной странице")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Переключение по табам каталога и проверка стилей наведение на элементы каталога")
    @Story("Пользователь ищет по каталогу интересующую игру")
    public void checkMainCatalog(Method method) {
        startTest(method.getName(), "Проверка каталога на главной странице");

        homePage.goToHome()
                .goToCatalog()
                .goToExpected()
                .checkHover()
                .goToNews()
                .checkHover()
                .goToSalesLeaders()
                .checkHover();
    }

    @Test(priority = 0, description = "Проверка календаря релизов на главной странице")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Переключение по страницам списка и проверка стилей при наведении на элементы в списке")
    @Story("Пользователь смотрит, когда выйдут новые игры")
    public void checkMainPreordersList(Method method) {
        startTest(method.getName(), "Проверка календаря релизов на главной странице");
        homePage.goToHome()
                .goToPreordersList()
                .checkContent()
                .checkNextButton()
                .checkPrevButton();
    }

    @Test(priority = 0, description = "Проверка категорий на главной странице")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка работы кнопки \"Показать все\", проверка стилей при наведении на элементы в списке")
    @Story("Пользователь просматривает категории игр")
    public void checkMainCategories(Method method) {
        startTest(method.getName(), "Проверка категорий на главной странице");
        homePage.goToHome()
                .goToCategories()
                .checkCategoryItems()
                .checkShowMoreButton()
                .checkCategoryItems();
    }

    @Test(priority = 0, description = "Проверка навигации на главной странице")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка работы меню навигации, правильности ссылок")
    @Story("Пользователь перемещается между страницами с помощью меню навигации")
    public void checkMainNavigation(Method method) {
        startTest(method.getName(), "Проверка навигации на главной странице");
        homePage.goToHome()
                .goToNavigation()
                .checkNavigation(0);
    }
}
