package org.challenge.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;
    static int timeout = 60;

    public void waitUntil(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void mouseOver(WebElement element) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
    }

    public void clickOn(WebElement element){
        Actions builder = new Actions(driver);
        builder.moveToElement(element).click().build().perform();
    }

    public void sendKeysById(String className, String text){
        WebElement el = driver.findElement(By.id(className));
        ((RemoteWebElement) el).setFileDetector(new LocalFileDetector());
        el.sendKeys(text);
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}