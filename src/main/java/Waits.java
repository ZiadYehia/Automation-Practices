import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Waits {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/dynamic_loading/1";

    By startButton = By.tagName("button");
    By hiddenText = By.cssSelector("#finish > h4");

    @Test(priority = 1)
    public void startDriver() {
        driver = new EdgeDriver(new EdgeOptions() {{
            setPageLoadStrategy(PageLoadStrategy.NONE);
        }});
        maximize();
        navigateTo(url);
    }

/*
    @Test(priority = 2)
    public void implicitWait() {
        clicking(startButton);
        implicitWait(10L);
        Assert.assertEquals(getText(hiddenText), "Hello World!");
        System.out.println(getText(hiddenText));
    }


    @Test(priority = 2)
    public void explicitWait() {
        clicking(startButton);
        //explicitWaitVisibilityOfElement(driver,hiddenText,10L);
        //explicitWaitVisibilityOfElementUsingLamda(driver,hiddenText,10L);
        explicitWaitVisibilityClickabilityOfElementUsingLamda(driver, hiddenText, 10L);
        Assert.assertEquals(getText(hiddenText), "Hello World!");
        System.out.println(getText(hiddenText));
    }*/

    @Test(priority = 2)
    public void fluentWaitTC() {
        fluentWaitVisibilityOfElement(driver, startButton, 10L, 200L);
        clicking(startButton);
        fluentWaitVisibilityOfElement(driver, hiddenText, 10L, 200L);
        Assert.assertEquals(getText(hiddenText), "Hello World!");
        System.out.println(getText(hiddenText));
    }


    public void maximize() {
        driver.manage().window().maximize();
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void findLocator(By by) {
        driver.findElement(by);
    }

    public void clicking(By by) {
        driver.findElement(by).click();
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public void implicitWait(Long sec) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

    public void explicitWaitVisibilityOfElement(WebDriver driver, By by, Long sec) {
        new WebDriverWait(driver, Duration.ofSeconds(sec))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void explicitWaitVisibilityOfElementUsingLamda(WebDriver driver, By by, Long sec) {
        new WebDriverWait(driver, Duration.ofSeconds(sec))
                .until(d -> driver.findElement(by).isDisplayed());
    }

    public void explicitWaitVisibilityClickabilityOfElementUsingLamda(WebDriver driver, By by, Long sec) {
        new WebDriverWait(driver, Duration.ofSeconds(sec))
                .until(d -> {
                    WebElement element = driver.findElement(by);
                    return element.isDisplayed() && element.isEnabled();
                });
    }

    public void fluentWaitVisibilityOfElement(WebDriver driver, By by, Long sec, Long millis) {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(sec))
                .pollingEvery(Duration.ofMillis(millis))
                .withMessage("Element is not visible, Locator: " + by.toString())
                .ignoring(NoSuchElementException.class)
                .until(d -> driver.findElement(by).isDisplayed());
    }

}
