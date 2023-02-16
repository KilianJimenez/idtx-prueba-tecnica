package com.test.bdd.touchActions.web;

import com.test.bdd.drivers.CustomWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class actionMethodsWeb {
    public static WebDriver driver = CustomWebDriver.driver;
    public static WebDriverWait wait = new WebDriverWait(driver, 5);

    private static final JavascriptExecutor js = (JavascriptExecutor) driver;

    public static void openPage(String pageUrl) {
        driver.get(pageUrl);
    }

    public static void clickOn(WebElement element) {
        element.click();
    }

    public static void clickOnByXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if (!isDisplayed(element))
            scrollToJS(element);
        element.click();
    }


    public static void acceptCookies() {
        js.executeScript("document.querySelector('bpc-cookie-banner').shadowRoot.querySelector('.bpc-cookie-accept-button').click()");
    }

    public static void sendKeysTo(String keys, WebElement element) {
        element.sendKeys(keys);
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public static void scrollToJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollTo(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void waitForElementToBeVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementToBeVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {

        }
    }

    public static WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

}
