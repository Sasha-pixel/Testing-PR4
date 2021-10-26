package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import utils.logs.Log;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class MainSliderInHomePage extends BasePage {
    public MainSliderInHomePage(WebDriver driver) {
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
    By mainSlider = By.cssSelector(".main-slider");

    By swiperPaginationBullet = By.cssSelector(".main-slider__pagination .swiper-pagination-bullet");

    By mainSliderBtnNext = By.cssSelector(".main-slider__btn-next");
    By mainSliderBtnPrev = By.cssSelector(".main-slider__btn-prev");

    By mainSliderItem = By.cssSelector(CSS_SELECTOR_MAIN_SLIDER_ITEM);
    By mainSliderItemActive = By.cssSelector(".main-slider__item.swiper-slide-active");

    /**
     * Page Methods
     */
    public MainSliderInHomePage checkNextButton() {
        return checkButton(true);
    }

    public MainSliderInHomePage checkPrevButton() {
        return checkButton(false);
    }

    private MainSliderInHomePage checkButton(boolean isNext) {
        WebElement webElementMainSlider = waitVisibility(mainSlider);
        List<WebElement> webElementsMainSliderItem = webElementMainSlider.findElements(mainSliderItem);
        List<WebElement> webElementsSwiperPaginationBullet = webElementMainSlider.findElements(swiperPaginationBullet);

        Log.info("Count bullet: " + webElementsSwiperPaginationBullet.size());
        Log.info("Count slider items: " + webElementsMainSliderItem.size());

        webElementsSwiperPaginationBullet.get(0).click();

        int initIndex = isNext ?0 :webElementsSwiperPaginationBullet.size();
        int maxIndex =  isNext ?webElementsSwiperPaginationBullet.size() :0;
        int step = isNext ?1 :-1;

        for (int index = initIndex; (isNext && index <= maxIndex) || (!isNext && index >= maxIndex); index += step) {
            int expectedIndex = index % webElementsSwiperPaginationBullet.size();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int indexSlide = Integer.parseInt(
                    waitVisibility(mainSliderItemActive).getDomAttribute(DATA_SWIPER_SLIDE_INDEX));

            assertEquals("Должен меняться активный slide",
                    expectedIndex, indexSlide);

            WebElement currentBullet = webElementsSwiperPaginationBullet.get(expectedIndex);
            assertTrue("Булет с активным классом должен меняться",
                    currentBullet.getDomAttribute("class").contains(CLASS_BULLET_ACTIVE));

            if (isNext) {
                click(mainSliderBtnNext);
            } else {
                click(mainSliderBtnPrev);
            }
        }

        return this;
    }

    public MainSliderInHomePage checkPaginationBullet() {
        WebElement webElementMainSlider = waitVisibility(mainSlider);
        List<WebElement> webElementsMainSliderItem = webElementMainSlider.findElements(mainSliderItem);
        List<WebElement> webElementsSwiperPaginationBullet = webElementMainSlider.findElements(swiperPaginationBullet);

        Log.info("Count bullet: " + webElementsSwiperPaginationBullet.size());
        Log.info("Count slider items: " + webElementsMainSliderItem.size());

        for (int index = 0; index < webElementsSwiperPaginationBullet.size(); index++) {
            WebElement currentBullet = webElementsSwiperPaginationBullet.get(index);

            currentBullet.click();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int indexSlide = Integer.parseInt(
                    waitVisibility(mainSliderItemActive).getDomAttribute(DATA_SWIPER_SLIDE_INDEX));

            assertEquals("Должен меняться активный slide", index, indexSlide);

            assertTrue("Булет с активным классом должен меняться",
                    currentBullet.getDomAttribute("class").contains(CLASS_BULLET_ACTIVE));
        }

        return this;
    }
}
