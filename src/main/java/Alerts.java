import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Alerts {

    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/";


    By jsAlertTab = By.cssSelector("[href=\"/javascript_alerts\"]");
    By jsAlert = By.cssSelector("[onclick=\"jsAlert()\"]");
    By jsConfirmDismiss = By.cssSelector("[onclick=\"jsConfirm()\"]");
    By jsPrompt = By.cssSelector("[onclick=\"jsPrompt()\"]");

    @Test(priority = 1)
    public void startDriverTC() {
        driver = new EdgeDriver(new EdgeOptions() {{
            setPageLoadStrategy(PageLoadStrategy.NONE);
        }});
        maximize();
        navigateTo(url);
        clicking(jsAlertTab);
    }

    @Test(priority = 2)
    public void jsConfirmTC() {
        clicking(jsAlert);
        alertAccept();
    }

    @Test(priority = 3)
    public void jsDismissTC() {
        clicking(jsConfirmDismiss);
        alertDismiss();
    }

    @Test(priority = 4)
    public void jsPromptTC() {
        clicking(jsPrompt);
        alertPrompt("ziad");
        alertAccept();
    }

    @Test(priority = 5)
    public void jsAlertTC() {

    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void clicking(By by) {
        defaultFluentWaitElementExist(driver, by);
        driver.findElement(by).click();
    }

    public void alertAccept() {
        driver.switchTo().alert().accept();
    }

    public void alertDismiss() {
        driver.switchTo().alert().dismiss();
    }

    public void alertPrompt(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public void fluentWaitElementExists(WebDriver driver, By by, Long sec, Long millis) {
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

    public void defaultFluentWaitElementExist(WebDriver driver, By by) {
        fluentWaitElementExists(driver, by, 10L, 200L);
    }
}
