import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;

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
        capabilities.setCapability("app", "C:\\Projects\\AppiumTestProject\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        WebElement skipButton = assertElementHasText(By.xpath("//*[contains(@text,'SKIP')]"),
                "The 'Skip' button is not found",
                5);
        skipButton.click();
        WebElement element = assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);
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
        waitForElementAndClick(By.xpath("//*[contains(@text,'SKIP')]"),
                "The 'Skip' button is not found",
                5);
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "This text is not found",
                5);

        waitForElementAndClear(By.id("org.wikipedia:id/search_close_btn"), "Oh no", 10);
        boolean here=elementIsDisplayed(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Test 'Search Wikipedia is not found'",
                10);
        Assert.assertTrue(here);
    }

    public WebElement assertElementHasText(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement assertElementHasText(By by, String errorMessage) {
        return assertElementHasText(by, errorMessage, 5);
    }

    public WebElement assertElementHasTextById(By by, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By by, String errorMessage, long time) {
        WebElement element = assertElementHasText(by, errorMessage, time);
        element.clear();
        return element;
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long time) {
        WebElement element = assertElementHasText(by, errorMessage, time);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long time) {
        WebElement element = assertElementHasText(by, errorMessage, time);
        element.sendKeys(value);
        return element;
    }

    public boolean elementIsDisplayed(By by, String errorMessage, long time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }
}

