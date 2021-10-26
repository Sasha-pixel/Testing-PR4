package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;
import utils.logs.Log;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CatalogInHomePage extends BasePage {
    public CatalogInHomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Variables
     */
    final String DATA_SWIPER_SLIDE_INDEX = "data-swiper-slide-index";
    final String CSS_SELECTOR_MAIN_SLIDER_ITEM = ".main-slider__item";
    final String CLASS_BULLET_ACTIVE = "swiper-pagination-bullet-active";

    /**
     * Web Elements
     */
    By mainPageCatalog = By.cssSelector(".main-page-catalog");

    By tabNews = By.cssSelector(".main-page-catalog .tabs__item:nth-child(1)");
    By tabSalesLeaders = By.cssSelector(".main-page-catalog .tabs__item:nth-child(2)");
    By tabExpected = By.cssSelector(".main-page-catalog .tabs__item:nth-child(3)");
    By catalogItems = By.cssSelector(".main-page-catalog .tab-pane.active .catalog-item");


    /**
     * Page Methods
     */
    public CatalogInHomePage goToNews() {
        Log.info("Перехожу во вкладку 'Новинки'");

        WebElement webElementTabNews = waitVisibility(tabNews);
        webElementTabNews.click();
        assertTrue("Вкладка 'Новинки' должна получить класс 'active'",
                webElementTabNews.getDomAttribute("class").contains("active"));

        sleep(500);

        return this;
    }

    public CatalogInHomePage goToSalesLeaders() {
        Log.info("Перехожу во вкладку 'Лидеры продаж'");

        WebElement webElementTabSalesLeaders = waitVisibility(tabSalesLeaders);
        webElementTabSalesLeaders.click();
        assertTrue("Вкладка 'Лидеры продаж' должна получить класс 'active'",
                webElementTabSalesLeaders.getDomAttribute("class").contains("active"));

        sleep(500);

        return this;
    }

    public CatalogInHomePage goToExpected() {
        Log.info("Перехожу во вкладку 'Ожидаемые'");

        WebElement webElementTabExpected = waitVisibility(tabExpected);
        webElementTabExpected.click();
        assertTrue("Вкладка 'Ожидаемые' должна получить класс 'active'",
                webElementTabExpected.getDomAttribute("class").contains("active"));

        sleep(500);

        return this;
    }

    public CatalogInHomePage checkHover() {
        List<WebElement> webElementsCatalogItems = driver.findElements(catalogItems);

        Log.info("Count elements catalog: " + webElementsCatalogItems.size());

        for (WebElement elementCatalogItem: webElementsCatalogItems) {
            Actions builder = new Actions(driver);
            builder.moveToElement(elementCatalogItem).perform();

            sleep(500);

            String boxShadow = elementCatalogItem.getCssValue("box-shadow");
            String zIndex = elementCatalogItem.getCssValue("z-index");
            String textDecorationLine = elementCatalogItem.getCssValue("text-decoration-line");
            String color = elementCatalogItem.getCssValue("color");

            String description = "При наведение на элемент каталога ожидается изменение свойств";

            assertEquals(description, "rgba(0, 0, 0, 0.15) 0px 0px 15px 0px", boxShadow);
            assertEquals(description, "2", zIndex);
            assertEquals(description, "none", textDecorationLine);
            assertEquals(description, "rgba(255, 121, 58, 1)", color);
        }

        return this;
    }
}
