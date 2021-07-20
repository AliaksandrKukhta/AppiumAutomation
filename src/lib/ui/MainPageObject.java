package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject (AppiumDriver driver){
        this.driver=driver;
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
