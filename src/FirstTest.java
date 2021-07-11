import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Courses\\Mobile Automation\\AppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        WebElement element = assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'");
        element.click();

        WebElement element2 =
                assertElementHasTextById(By.id("org.wikipedia:id/search_src_text"),
                        "This text is not found",
                        5);
        element2.sendKeys("Java");
        boolean isDisplayed = element2.getText().equalsIgnoreCase("Java");
        Assert.assertTrue(isDisplayed);
    }

    @Test
    public void cancelTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "This text is not found",
                5);

        waitForElementAndClear(By.id("org.wikipedia:id/search_close_btn"), "Oh no", 10);
        boolean here = elementIsDisplayed(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);
        Assert.assertTrue(here);
    }

    @Test
    public void someWordInEveryFindElementsTest() throws InterruptedException {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "This text is not found",
                5);
        Thread.sleep(5000);
        List<WebElement> listOfFindElements = assertElementsHasText(By.xpath("//*[contains(@resource-id, 'org.wikipedia:id/page_list_item_title')]"),
                "message",
                10);
        String someText = "Java";
        boolean containsSomeText = false;
        for (int i = 0; i < listOfFindElements.size(); i++) {
            if (listOfFindElements.get(i).getText().contains(someText)) {
                containsSomeText = true;
            }
        }
        Assert.assertTrue(containsSomeText);
    }

    @Test
    public void swipeTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "This text is not found",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find article",
                15);

        swipeToElement(By.xpath("//*[@text='View article in browser']"),
                "Cannot go to the end of page",
                10);

    }

    @Test
    public void saveTwoArticlesTest() {
        String firstSubject = "Java";
        String secondSubject = "Appium";
        String articleName = "Java Article";

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                firstSubject,
                "This text is not found",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "Cannot find article",
                20);

        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"),
                "Cannot find toolbar_button",
                20);

        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list'",
                15);

        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find onboarding_button",
                15);

        waitForElementAndClearAndSendKeys(By.id("org.wikipedia:id/text_input"),
                articleName,
                15);

        waitForElementAndClick(By.id("android:id/button1"),
                "Cannot find onboarding_button",
                15);

        waitForElementAndClick(By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot find exit from article button",
                15);

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                secondSubject,
                "This text is not found",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find article",
                20);

        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"),
                "Cannot find toolbar_button",
                20);

        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list'",
                15);

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='"+articleName+"']"),
                "Cannot find onboarding_button",
                15);

        waitForElementAndClick(By.xpath("//*[@class='android.widget.ImageButton']"),
                "Cannot find exit from article button",
                15);

        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find android.widget.ImageButton",
                15);


        waitForElementAndClick(By.id("org.wikipedia:id/item_title"),
                "Cannot find My Java List",
                10);

        int sizeOfArticles = 1;

        leftSwipe(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article");

        Assert.assertTrue(waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find article",
                20).isDisplayed());
    }

    @Test
    public void titlePresentTest(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "This text is not found",
                5);

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Object-oriented programming language']"),
                "Cannot find article",
                20);

        Assert.assertEquals("Object-oriented programming language", assertElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='Java (programming language)']"), "title"));
    }

    public String assertElementPresent(By by, String attribute){
        return driver.findElement(by).getAttribute(attribute);
    }

    public void moveAndClickAction(WebElement by, int timeOfSwipe) {
        Dimension size = driver.manage().window().getSize();
        int x = (int) (size.width * 0.9);
        int y = (int) (size.height * 0.1);
        TouchAction action = new TouchAction(driver);
        action.press(by)
                .waitAction(timeOfSwipe)
                .release()
                .perform();
    }

    public WebElement waitForElementPresent(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> assertElementsHasText(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement assertElementHasText(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    public WebElement assertElementHasTextById(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By by, String errorMessage, long time) {
        WebElement element = waitForElementPresent(by, errorMessage, time);
        element.clear();
        return element;
    }

    public WebElement waitForElementAndClearAndSendKeys(By by, String value, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.clear();
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long time) {
        WebElement element = waitForElementPresent(by, errorMessage, time);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long time) {
        WebElement element = waitForElementPresent(by, errorMessage, time);
        element.sendKeys(value);
        return element;
    }

    public boolean elementIsDisplayed(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    public void swipeUp(int timeOfSwipe) {
        Dimension size = driver.manage().window().getSize();
        int x = (int) size.width / 2;
        int firstY = (int) (size.height * 0.8);
        int lastY = (int) (size.height * 0.2);
        TouchAction action = new TouchAction(driver);
        action
                .press(x, firstY)
                .waitAction(timeOfSwipe)
                .moveTo(x, lastY)
                .release()
                .perform();
    }

    public void swipeQiuck() {
        swipeUp(500);
    }

    public void swipeToElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwipes = 0;
        int size = driver.findElements(by).size();
        while (size == 0) {
            if (alreadySwipes > maxSwipes) {
                waitForElementPresent(by, "Cannot find article after swipes. + \n" + errorMessage, 10);
                return;
            }
            swipeQiuck();
            ++alreadySwipes;
        }
    }

    public void leftSwipe(By by, String errorMessage){
        WebElement element = waitForElementPresent(by, errorMessage, 20);
        int leftX=element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY= element.getLocation().getY();
        int lowerY=upperY+element.getSize().getHeight();
        int middleY=(upperY+lowerY)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(250)
                .moveTo(leftX,middleY)
                .release()
                .perform();

    }
}

