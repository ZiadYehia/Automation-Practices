import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

public class SeleniumActions {
    WebDriver driver;
    String url = "https://aa-practice-test-automation.vercel.app";

    By username = By.id("inputUsername");
    By password = By.id("inputPassword");
    By loginButton = By.id("loginButton");
    By mouseActionTab = By.cssSelector("[href='mouse.html']");
    By rightClick = By.id("riClickBtn");
    By doubleClick = By.id("dblClickBtn");

    @Test(priority = 1)
    public void startDriver() {
        driver = new EdgeDriver(new EdgeOptions() {{
            setPageLoadStrategy(PageLoadStrategy.NONE);
        }});
        maximize();
        navigateTo(url);
    }

    @Test(priority = 2)
    public void loginTC() {
        typing(username, "admin");
        typing(password, "admin");
        clicking(loginButton);
    }

    @Test(priority = 3)
    public void rightClickActionTC() {
        clicking(mouseActionTab);
        rightClickAction(rightClick);
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void typing(By by, String text) {
        defaultFluentWaitExist(driver, by);
        driver.findElement(by).sendKeys(text);
    }

    public void clicking(By by) {
        defaultFluentWaitExist(driver, by);
        driver.findElement(by).click();
    }

    public void rightClickAction(By by) {
        defaultFluentWaitExist(driver, by);
        new Actions(driver).contextClick(locateElement(by)).perform();
    }

    public WebElement locateElement(By by) {
        return driver.findElement(by);
    }

    public void fluentWaitExist(WebDriver driver, By by, Long sec, Long millis) {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(sec))
                .pollingEvery(Duration.ofMillis(millis))
                .withMessage("Element is not found, locator: " + by.toString())
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class)
                .until(d -> {
                    try {
                        WebElement e = driver.findElement(by);
                        return e.isDisplayed() && e.isEnabled();
                    } catch (NoSuchElementException | StaleElementReferenceException e) {
                        return false;
                    }
                });
    }

    public void defaultFluentWaitExist(WebDriver driver, By by) {
        fluentWaitExist(driver, by, 10L, 200L);
    }

}
