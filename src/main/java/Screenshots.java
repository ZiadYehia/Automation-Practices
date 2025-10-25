import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Screenshots {

    WebDriver driver;
    String url = "https://www.google.com";
    String filePath = "src/main/resources";

    By googleicon = By.cssSelector("[aria-label=\"Google\"]");


    @Test
    public void startDriverTC() {
        driver = new EdgeDriver(new EdgeOptions() {{
            setPageLoadStrategy(PageLoadStrategy.EAGER);
        }});
        maximize();
        navigateTo(url);
    }

    @Test
    public void takeScreenshotTC() {
        takeScreenshot(filePath, "google.png");
    }

    @Test
    public void takeScreenshotElementTC() {
        takeScreenshotForElement(googleicon, filePath, "google2.png");
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void takeScreenshot(String path, String screenName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // (EdgeDriver)
        File dest = new File(System.getProperty("user.dir") + File.separator + path + File.separator + screenName);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void takeScreenshotForElement(By by, String path, String screenName) {
        File src = driver.findElement(by).getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + File.separator + path + File.separator + screenName);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void fluentWaitElementExists(WebDriver driver, By by, Long sec, Long millis) {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(sec))
                .pollingEvery(Duration.ofMillis(millis))
                .withMessage("Element is not found, locator: " + by.toString())
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                            try {
                                WebElement e = driver.findElement(by);
                                return e.isDisplayed() && e.isEnabled();
                            } catch (NoSuchElementException | StaleElementReferenceException e) {
                                return false;
                            }
                        }
                );
    }

    public void defaultFluentWaitElementExists(WebDriver driver, By by) {
        fluentWaitElementExists(driver, by, 10L, 200L);
    }
}
