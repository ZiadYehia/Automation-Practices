import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Waits {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/dynamic_loading/2";

    By startButton = By.tagName("button");
    By hiddenText = By.cssSelector("#finish > h4");

    @Test(priority = 1)
    public void startDriver() {
        driver = new EdgeDriver();
        maximize();
        navigateTo(url);
    }

    @Test(priority = 2)
    public void implicitWait() {
        clicking(startButton);
        implicitWait(5L);
        Assert.assertEquals("");
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

    public void implicitWait(Long sec) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }
}
