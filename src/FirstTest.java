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
    public void firstTest() throws InterruptedException {
        WebElement skipButton = assertElementHasText("//*[contains(@text,'SKIP')]",
                "The 'Skip' button is not found",
                5);
        skipButton.click();
        WebElement element = assertElementHasText("//*[contains(@text, 'Search Wikipedia')]",
                "Test 'Search Wikipedia is not found'",
                10);
        element.click();

        WebElement element2 =
                assertElementHasTextById("org.wikipedia:id/search_src_text",
                        "This text is not found",
                        5);
        element2.sendKeys("Java");
        boolean isDisplayed = element2.getText().equalsIgnoreCase("Java");
        Assert.assertTrue(isDisplayed);
    }

    public WebElement assertElementHasText(String xpath, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement assertElementHasText(String xpath, String errorMessage) {
        return assertElementHasText(xpath, errorMessage, 5);
    }

    public WebElement assertElementHasTextById(String xpath, String errorMessage, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(errorMessage);
        By by = By.id(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}

