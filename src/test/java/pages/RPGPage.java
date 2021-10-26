package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.home.HomePage;
import utils.logs.Log;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;

public class RPGPage extends BasePage{
    public RPGPage(WebDriver driver) {
        super(driver);
    }

    String baseURL = "https://steampay.com/rpg";
    public RPGPage goToRPGPage() {
        Log.info("Opening steampay Website.");
        driver.get(baseURL);
        return this;
    }

    public RPGPage checkCurrency(){
        By buttonCurrency = By.cssSelector(".main-header__currency-selected");
        WebElement webElement = waitVisibility(buttonCurrency);
        By currency;
        String expectedCurrency;
        if(webElement.getAttribute("data-currency").equals("wmr")){
            currency = By.cssSelector("[href=\"/currency/wme\"]");
            expectedCurrency = "wme";
        }
        else{
            currency = By.cssSelector("[href=\"/currency/wmr\"]");
            expectedCurrency = "wmr";
        }
        webElement.click();
        WebElement webElementEuro = waitVisibility(currency);
        webElementEuro.click();
        WebElement webElementNext = waitVisibility(buttonCurrency);
        String attribute = webElementNext.getAttribute("data-currency");
        assertEquals(expectedCurrency, attribute);
        return this;
    }

    public RPGPage checkButtonAll(){
        By buttonAll = By.cssSelector(".js-available");
        List<WebElement> webElementButtons = driver.findElements(buttonAll);

        String oldColor = webElementButtons.get(0).getCssValue("color");

        Actions builder = new Actions(driver);
        builder.moveToElement(webElementButtons.get(0)).perform();
        sleep(1000);
        String newColor = webElementButtons.get(0).getCssValue("color");

        assertNotSame(oldColor, newColor);

        return this;
    }
}

