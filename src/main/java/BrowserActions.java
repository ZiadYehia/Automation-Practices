import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import java.util.Set;

public class BrowserActions {
    WebDriver driver;

    @Test
    public void testCase() {
        driver = new EdgeDriver();
        maximize();
        navigateTo("https://demo.nopcommerce.com/nikon-d5500-dslr");
        getCurrentUrl();
        getTitle();
        getWindowHandle();
        getWindowHandles();
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void setPosition(int x, int y) {
        //Point point = new Point(100, 100);
        driver.manage().window().setPosition(new Point(x, y));
    }

    public void setSize(int w, int h) {
        driver.manage().window().setSize(new Dimension(w, h));
    }

    public void fullScreen() {
        driver.manage().window().fullscreen();
    }

    public void getCurrentUrl() {
        String url = driver.getCurrentUrl();
        System.out.println("Current Url: " + url);
    }

    public void getTitle() {
        String title = driver.getTitle();
        System.out.println("Title: " + title);
    }

    public void getPageSource() {
        String pageSource = driver.getPageSource();
        System.out.println("Page Source: " + pageSource);
    }

    public void getWindowHandle() {
        String windowHandle = driver.getWindowHandle();
        System.out.println("Window Handle: " + windowHandle);
    }

    public void getWindowHandles() {
        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            System.out.println("Tab :" + windowHandle);
        }
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }
}
