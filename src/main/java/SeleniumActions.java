import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
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
    By hover1 = By.cssSelector("[href='sendData.html']");
    By hover2 = By.cssSelector("[href='scrolling.html']");
    By hover3 = By.cssSelector("[href='Challenge.html']");
    By dragdropTab = By.cssSelector("[href='dragDrop.html']");
    By imageDrag = By.cssSelector("[src='../Images/meme.jpg']");
    By imageDrop = By.id("dropZone");
    By homeTab = By.cssSelector("[href='../Pages/main.html'");
    By sendDataTab = By.cssSelector("[href='sendData.html']");
    By scrollTab = By.cssSelector("[href=\"scrolling.html\"]");
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By scrollTextField = By.id("scroll_text");


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
    public void hoverTC() {
        hovering(hover1);
        hovering(hover2);
        hovering(hover3);
    }

    @Test(priority = 4)
    public void rightClickActionTC() {
        clicking(mouseActionTab);
        rightClickAction(rightClick);
    }

    @Test(priority = 5)
    public void doubleClickActionTC() {
        doubleClickAction(doubleClick);
    }

    @Test(priority = 6)
    public void dragdropActionTC() {
        clicking(homeTab);
        clicking(dragdropTab);
        dragdroping(imageDrag, imageDrop);
    }

    @Test(priority = 7)
    public void holdReleaseActionTC() {
        clicking(homeTab);
        clicking(dragdropTab);
        holdAndRelease(imageDrag, imageDrop);
    }

    @Test(priority = 8)
    public void typingWithKeyboardTC() {
        clicking(homeTab);
        clicking(sendDataTab);
        keyDownAllCapitalTyping(firstName, "ziad");
        keyDownFirstCapitalTyping(lastName, "yehia");
    }

    @Test(priority = 9)
    public void scrollingTC() {
        clicking(homeTab);
        clicking(scrollTab);
        scrollToElement(scrollTextField);
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

    public void doubleClickAction(By by) {
        defaultFluentWaitExist(driver, by);
        new Actions(driver).doubleClick(locateElement(by)).perform();
    }

    public void hovering(By by) {
        defaultFluentWaitExist(driver, by);
        new Actions(driver).moveToElement(locateElement(by)).perform();
    }

    public void dragdroping(By drag, By drop) {
        defaultFluentWaitExist(driver, drag);
        new Actions(driver).dragAndDrop(locateElement(drag), locateElement(drop)).perform();
    }

    public void holdAndRelease(By from, By to) {
        defaultFluentWaitExist(driver, from);
        new Actions(driver)
                .clickAndHold(locateElement(from))
                .moveToElement(locateElement(to))
                .release()
                .perform();
    }

    public void keyDownAllCapitalTyping(By by, String text) {
        defaultFluentWaitExist(driver, by);
        WebElement element = locateElement(by);
        new Actions(driver)
                .click(element)
                .keyDown(Keys.SHIFT)
                .sendKeys(text)
                .keyUp(Keys.SHIFT)
                .perform();
    }

    public void keyDownFirstCapitalTyping(By by, String text) {
        defaultFluentWaitExist(driver, by);
        WebElement element = locateElement(by);

        String firstLetter = text.substring(0, 1);
        String rest = text.substring(1);

        new Actions(driver)
                .click(element)
                .keyDown(Keys.SHIFT)
                .sendKeys(firstLetter)
                .keyUp(Keys.SHIFT)
                .sendKeys(rest)
                .perform();
    }

    public void scrollToElement(By by) {
        defaultFluentWaitExist(driver, by);
        new Actions(driver)
                .scrollToElement(locateElement(by))
                .perform();
    }


    public void resetActions() {
        ((RemoteWebDriver) driver).resetInputState();
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
