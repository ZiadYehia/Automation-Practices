import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ElementActions {
    final String url = "https://aa-practice-test-automation.vercel.app/";
    String filePath = "src/main/resources/README.md";
    WebDriver driver;
    By username = By.id("inputUsername");
    By password = By.cssSelector("[type='password']");
    By loginButton = RelativeLocator.with(By.tagName("button")).below(password);
    By uploadFilesTab = By.cssSelector("[href='uploadFile.html']");
    By uploadFile = By.id("regularFileInput");


    @Test(priority = 1)
    public void startDriver() {
        driver = new EdgeDriver();
        maximize();
        navigateTo(url);
    }

    @Test(priority = 2)
    public void fieldsEnabled() {
        Assert.assertTrue(isEnabled(username));
        Assert.assertTrue(isEnabled(password));
    }

    @Test(priority = 3)
    public void loginTC() {
        typing(username, "admin");
        typing(password, "admin");
        clicking(loginButton);
    }

    @Test(priority = 4)
    public void uploadFileTC() {
        clicking(uploadFilesTab);
        uploadFile(uploadFile, filePath);
    }

    @Test(priority = 5)
    public void checkShadowDom() {
        driver = new EdgeDriver();
        maximize();
        navigateTo("http://watir.com/examples/shadow_dom.html");
        By root = By.id("shadow_host");
        By by = By.cssSelector("[type='text']");
        shadowDom(root, by).sendKeys("Ziad");
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public WebElement findLocator(By by) {
        return driver.findElement(by);
    }

    public void clicking(By by) {
        driver.findElement(by).click();
    }

    public void clear(By by) {
        driver.findElement(by).clear();
    }

    public void typing(By by, String text) {
        clear(by);
        driver.findElement(by).sendKeys(text);
    }

    public void uploadFile(By by, String path) {
        driver.findElement(by).sendKeys(System.getProperty("user.dir") + File.separator + path);
    }

    public Boolean isEnabled(By by) {
        return driver.findElement(by).isEnabled();
    }

    public Boolean isDisplayed(By by) {
        return driver.findElement(by).isDisplayed();
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public String getAttribute(By by, String attribute) {
        return driver.findElement(by).getDomAttribute(attribute); //intial value only
    }

    public String getProperty(By by, String property) {
        return driver.findElement(by).getDomProperty(property); //dynamic
    }

    public WebElement shadowDom(By root, By by) {
        return driver.findElement(root).getShadowRoot().findElement(by);
    }
}
