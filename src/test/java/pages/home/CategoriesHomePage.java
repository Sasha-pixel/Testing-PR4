package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;
import utils.logs.Log;

import java.util.List;

import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertTrue;

public class CategoriesHomePage extends BasePage {
    public CategoriesHomePage(WebDriver driver) {
        super(driver);
    }


    /**
     * Web Elements
     */


    By mainCategories = By.cssSelector(".main-categories");

    By mainCategoriesItem = By.cssSelector(".main-categories__item");
    By mainCategoriesShowMoreBtn = By.cssSelector(".main-categories__show-more-btn");


    /**
     * Page Methods
     */
    public CategoriesHomePage checkShowMoreButton() {
        Log.info("Начинаю проверку кнопки \"Показать все\"");
        WebElement webElementCategories = waitVisibility(mainCategories);
        WebElement webElementButtonShowMore = webElementCategories.findElement(mainCategoriesShowMoreBtn);
        Actions builder = new Actions(driver);
        String oldColor = webElementButtonShowMore.getCssValue("color");
        builder.moveToElement(webElementButtonShowMore).perform();
        sleep(1000);
        assertNotSame("При наведении должен меняться цвет текста", oldColor, webElementButtonShowMore.getCssValue("color"));
        int oldCount = webElementCategories.findElements(mainCategoriesItem).size();
        webElementButtonShowMore.click();
        sleep(1000);
        assertTrue("Количество отображаемых категорий должно увеличиваться при нажатии на кнопку \"Показать все\"",
                oldCount < webElementCategories.findElements(mainCategoriesItem).size());
        sleep(500);

        return this;
    }

    public CategoriesHomePage checkCategoryItems() {
        Log.info("Начинаю проверку элементов списка категорий");
        WebElement webElementCategories = waitVisibility(mainCategories);
        List<WebElement> items = webElementCategories.findElements(mainCategoriesItem);
        for (int i = 0; (i < 10) && (i < items.size()); i++) {
            Actions builder = new Actions(driver);
            String oldColor = items.get(i).getCssValue("background-color");
            builder.moveToElement(items.get(i)).perform();
            sleep(1000);
            assertNotSame("При наведении должен меняться цвет фона", oldColor, items.get(i).getCssValue("background-color"));
        }

        sleep(500);

        return this;
    }


}
