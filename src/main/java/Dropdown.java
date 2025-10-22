import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;

public class Dropdown {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/dropdown";
    By dropdown = By.id("dropdown");

    @Test(priority = 1)
    public void startDriver() {
        driver = new EdgeDriver(
                new EdgeOptions() {{
                    setPageLoadStrategy(PageLoadStrategy.NONE);
                }}
        );
        maximize();
        navigateTo(url);
    }

    @Test(priority = 2)
    public void DropdownTC() {
        fluentWaitVisibilityOfElement(driver, dropdown, 10L, 200L);
        dropdownSelectByIndex(dropdown, 2);
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void dropdownSelectByIndex(By by, int index) {
        new Select(getWebElement(by)).selectByIndex(index);
    }

    public void dropdownSelectByValue(By by, String value) {
        new Select(getWebElement(by)).selectByValue(value);
    }

    public void dropdownSelectByText(By by, String text) {
        new Select(getWebElement(by)).selectByVisibleText(text);
    }

    public WebElement getWebElement(By by) {
        return driver.findElement(by);
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
