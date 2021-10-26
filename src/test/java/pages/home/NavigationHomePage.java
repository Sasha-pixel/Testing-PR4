package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;
import utils.logs.Log;

import java.util.List;

import static org.testng.AssertJUnit.*;

public class NavigationHomePage extends BasePage {
    public NavigationHomePage(WebDriver driver) {
        super(driver);
    }

    private final String baseURL = "https://steampay.com/";


    /**
     * Web Elements
     */

    By mainNav = By.cssSelector(".main-header__nav-toggle");
    By mainNavItem = By.cssSelector(".main-header__nav-item");
    By notFound = By.cssSelector(".not-found-error");

    /**
     * Page Methods
     */
    public NavigationHomePage checkNavigation(int index) {
        Log.info("Начинаю проверку навигации");
        WebElement mainNavElement = waitVisibility(mainNav);
        List<WebElement> items = driver.findElements(mainNavItem);
        if(index < items.size()) {
            mainNavElement.click();
            sleep(1000);

            Actions builder = new Actions(driver);
            String oldBackgroundColor = items.get(index).getCssValue("background-color");
            String oldColor = items.get(index).getCssValue("-color");
            builder.moveToElement(items.get(index)).perform();
            sleep(1000);
            assertNotSame("При наведении должен меняться цвет текста", oldColor, items.get(index).getCssValue("color"));
            assertNotSame("При наведении должен меняться цвет фона", oldBackgroundColor, items.get(index).getCssValue("background-color"));
            items.get(index).click();
            sleep(1000);
            List<WebElement> notFoundList = driver.findElements(notFound);
            assertEquals("Ссылка в навигационном меню должна существовать", 0, notFoundList.size());
            driver.get(baseURL);
            checkNavigation(index + 1);
        }

        sleep(500);

        return this;
    }

}
