package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.logs.Log;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class SupportPage extends BasePage {
    public SupportPage(WebDriver driver) {
        super(driver);
    }

    String baseURL = "https://steampay.com/support";

    public SupportPage goToSupportPage() {
        Log.info("Opening steampay Website.");
        driver.get(baseURL);
        return this;
    }

    public SupportPage checkCards() {
        By supportTabsItem = By.cssSelector(".support-tabs__item > a");

        waitVisibility(supportTabsItem);
        List<WebElement> webElementList = driver.findElements(supportTabsItem);

        for (int i = 0; i < webElementList.size(); i++) {
            Actions builder = new Actions(driver);
            builder.moveToElement(webElementList.get(i)).perform();

            sleep(1000);

            assertEquals("rgba(255, 121, 58, 1)", webElementList.get(i).getCssValue("color"));
        }

        return this;
    }

    public SupportPage checkDropDownMenu() {
        By supportTabsItem = By.cssSelector(".support__item");
        waitVisibility(supportTabsItem);
        List<WebElement> webElementList = driver.findElements(supportTabsItem);

        for (int i = 0; i < webElementList.size(); i++) {
            Actions builder = new Actions(driver);
            builder.click(webElementList.get(i)).perform();

            sleep(1000);

            assertEquals("support__item support__item--active", webElementList.get(i).getDomAttribute("class"));
        }

        return this;
    }

    public SupportPage checkFeedbackButton() {
        By feedbackFormItem = By.cssSelector(".support-form");
        By feedbackBtnItem = By.cssSelector(".support-footer__btn");


        WebElement feedbackForm = driver.findElements(feedbackFormItem).get(0);
        WebElement feedbackBtn = driver.findElements(feedbackBtnItem).get(0);

        Actions builder = new Actions(driver);
        builder.click(feedbackBtn).perform();

        sleep(1000);

        assertEquals("block", feedbackForm.getCssValue("display"));


        return this;
    }
}
