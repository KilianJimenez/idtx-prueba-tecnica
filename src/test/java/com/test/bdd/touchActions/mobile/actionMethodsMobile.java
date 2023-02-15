package com.test.bdd.touchActions.mobile;

import com.test.bdd.drivers.CustomAndroidDriver;
import com.test.bdd.drivers.CustomiOSDriver;
import com.test.bdd.framework.Framework;
import com.test.bdd.utils.Utils;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class actionMethodsMobile {

    public static MobileDriver getDriver() {
        switch (Framework.getPlatform()) {
            case ANDROID:
            default:
                return CustomAndroidDriver.driver;

            case IOS:
                return CustomiOSDriver.driver;
        }
    }

    //// Clicks
    public static void clickOn(MobileElement element) {
        element.click();
    }

    public static void clickOnByXpath(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();

    }

    /**
     * Long pressing for a period of times
     *
     * @param element     MobileElement
     * @param timePressed times while pressing element in SECONDS
     */
    public static void longPress(MobileElement element, int timePressed) {
        AndroidTouchAction touch = new AndroidTouchAction(getDriver());
        touch.longPress(LongPressOptions.longPressOptions()
                        .withElement(ElementOption.element(element)).withDuration(Duration.ofSeconds(timePressed)))
                .release().perform();
    }


    /// Field Actions
    public static void sendKeysTo(String text, MobileElement element) {
        element.sendKeys(text);
    }

    /**
     * Sends keys (string) to the location of the cursor
     *
     * @param sentText
     */
    public static void sendKeysToCursor(String sentText) {
        MobileElement activeElement = (MobileElement) getDriver().switchTo().activeElement();
        activeElement.sendKeys(sentText);

    }

    public static void listClear(List<MobileElement> elements) {
        for (MobileElement element : elements) {
            element.clear();
        }
    }

    public static void clear(MobileElement element) {
        element.clear();
    }


    /// Element status/actions
    public static boolean isDisplayed(MobileElement element) {
        try {
            return element.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    /**
     * Checks the presence of an element in the screen
     *
     * @param element Mobile element
     * @return returns the presence
     */
    public static boolean isPresent(MobileElement element) throws Exception {
        try {
            boolean presence = element.isDisplayed();
            Utils.logger.info("Checking presence of [" + element + "]. Is present: <" + presence + ">");
            return presence;
        } catch (Exception e) {
            throw new Exception("Element not found");
        }
    }

    /**
     * Gets text from specific element
     *
     * @param element MobileElement
     * @return returns the text
     */
    public static String getText(MobileElement element) {
        return element.getText();
    }

    /**
     * verification of element text to content input
     *
     * @param element MobileElement
     * @param content content returned
     * @return true or false depending on element text is equal to the content
     */
    public static Boolean containsText(MobileElement element, String content) {
        String text = element.getText();
        return text.contains(content);
    }


    /// Debug/Waits

    /**
     * Used for debugging - generates all elements present on screen the moment this function is called
     */
    public static void pageSource() {
        String pageSource = getDriver().getPageSource();
    }

    public static void hideKeyboard() {
        //TODO: does not work for ios
        getDriver().hideKeyboard();
    }

    /**
     * Wait for element to be visible on screen depending on time given
     *
     * @param element  MobileElement
     * @param waitTime Time to wait for element to appear
     */
    public static void waitForVisible(MobileElement element, int waitTime) throws TimeoutException {
        try {
            long start = System.currentTimeMillis();
            Utils.logger.info("Waiting for visibility of element [" + element.toString() + "] --> " + waitTime + " second");
            WebDriverWait wait = new WebDriverWait(getDriver(), waitTime);
            wait.until(ExpectedConditions.visibilityOf(element));
            long stop = System.currentTimeMillis();
            Utils.logger.info("Actual wait time: " + (stop - start) + "ms");
        } catch (Exception ex) {
            throw new TimeoutException("element --> " + element.toString() + " not found!");
        }
        //isPresent(element);
    }

    public static void sleep(int timeMS) {
        try {
            Thread.sleep(timeMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //TODO: Please do not delete it's a WIP, add new functions above
    /*
        /**
         * waits for visibility of element, shows how much time was spent waiting in theory and the actual time spent
         * @param element
         * @param waitTimeInSeconds

    public static void waitForVisible(MobileElement element, double waitTimeInSeconds) throws Exception {
        wait.until(element::isDisplayed);
        element.
        WebElement clickOK=wait.until(ExpectedConditions.visibilityOf(getDriver().findElementByXPath("//*[@text='OK']")));
        WebElement clickOK=wait.until(ExpectedConditions.visibilityOf(getDriver().findElementByXPath("//*[@text='OK']")));
        WebElement clickOK=wait.until(ExpectedConditions.visibilityOf(getDriver().findElementByXPath("//*[@text='OK']")));
        //WebElementFacade elementFacade = (WebElementFacade) element;

*//*        String elementID = element.toString();
       logger.info(elementID);*//*

        //WebElementFacade element;

        //WebElementFacade elementFacade = getDriver().findElement(By.id("first_name_field"));


        logger.info("Waiting for visibility of the element --> [" + element + "] for " + waitTimeInSeconds + " seconds");
        long start = System.currentTimeMillis();
        double count = waitTimeInSeconds*2;
       *//* while ((!element.isCurrentlyVisible()) && (count >= 0)){
            Thread.sleep(500);
            count -= 1;
            if (count==0){
                Assert.fail(element + "--> NOT FOUND");
                //throw new AssertionError(element + "--> NOT FOUND");
            }
        }*//*
        long stop = System.currentTimeMillis();
        if((stop - start) > (waitTimeInSeconds*1000)){
            throw new Exception("Wait time exceeded, expected: "+ (waitTimeInSeconds*1000) + "ms, actual wait time: "+ (stop - start) + "ms");
        }
        logger.info("Actual wait time: " + (stop - start) + "ms");
    }*/


}
