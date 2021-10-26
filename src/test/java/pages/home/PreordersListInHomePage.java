package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;
import utils.logs.Log;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class PreordersListInHomePage extends BasePage {
    public PreordersListInHomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Web Elements
     */

    By mainPreorders = By.cssSelector(".main-preorders");

    By mainPreordersListButtonNext = By.cssSelector(".section-arrows__next");
    By mainPreordersListButtonPrev = By.cssSelector(".section-arrows__prev");

    By mainPreordersListItem = By.cssSelector(".main-preorders__list-item");

    By preordersImage = By.cssSelector(".main-preorders__image");


    /**
     * Page Methods
     */
    public PreordersListInHomePage checkNextButton() {
        WebElement webElementPreorders = waitVisibility(mainPreorders);
        WebElement webElementButtonNext = webElementPreorders.findElement(mainPreordersListButtonNext);
        if (!webElementButtonNext.getDomAttribute("class").contains("section-arrows__disabled")) {
            List<WebElement> items = driver.findElements(mainPreordersListItem);
            List<String> titles = new ArrayList<>();
            for (WebElement item : items) {
                titles.add(item.getDomAttribute("title"));
            }
            Log.info("Перехожу на следующую страницу");
            webElementButtonNext.click();
            sleep(1000);
            assertFalse("При переключении на след. страницу кнопка \"Назад\" должна становится enabled",
                    webElementPreorders
                            .findElement(mainPreordersListButtonPrev)
                            .getDomAttribute("class")
                            .contains("section-arrows__disabled"));
            items = driver.findElements(mainPreordersListItem);
            assertTrue("Список не может быть пустым", items.size() > 0);
            for (WebElement item : items) {
                assertTrue("На разных страницах не могут находится одинаковые элементы",
                        titles.stream()
                                .filter(title -> title.equals(item.getDomAttribute("title"))
                                )
                                .findAny()
                                .isEmpty()
                );
            }
        }

        sleep(500);

        return this;
    }

    public PreordersListInHomePage checkPrevButton() {
        WebElement webElementPreorders = waitVisibility(mainPreorders);
        WebElement webElementButtonPrev = webElementPreorders.findElement(mainPreordersListButtonPrev);
        if (!webElementButtonPrev.getDomAttribute("class").contains("section-arrows__disabled")) {
            List<WebElement> items = driver.findElements(mainPreordersListItem);
            List<String> titles = new ArrayList<>();
            for (WebElement item : items) {
                titles.add(item.getDomAttribute("title"));
            }
            Log.info("Перехожу на предыдущую страницу");
            webElementButtonPrev.click();
            sleep(1000);
            assertFalse("При переключении на пред. страницу кнопка \"Вперед\" должна становится enabled",
                    webElementPreorders
                            .findElement(mainPreordersListButtonNext)
                            .getDomAttribute("class")
                            .contains("section-arrows__disabled"));
            items = driver.findElements(mainPreordersListItem);
            assertTrue("Список не может быть пустым", items.size() > 0);
            for (WebElement item : items) {
                assertTrue("На разных страницах не могут находится одинаковые элементы",
                        titles.stream()
                                .filter(title -> title.equals(item.getDomAttribute("title"))
                                )
                                .findAny()
                                .isEmpty()
                );
            }
        }

        sleep(500);

        return this;
    }

    public PreordersListInHomePage checkContent() {
        Log.info("Начинаю тестирование наведения на элементы");
        WebElement webElementPreorders = waitVisibility(mainPreorders);
        List<WebElement> items = webElementPreorders.findElements(mainPreordersListItem);
        for (WebElement item : items) {
            Actions builder = new Actions(driver);
            builder.moveToElement(item).perform();
            sleep(1000);
            assertEquals("При наведении должна меняться прозрачность изображения", "0.8", item.findElement(preordersImage).getCssValue("opacity"));
        }

        sleep(500);

        return this;
    }

}
