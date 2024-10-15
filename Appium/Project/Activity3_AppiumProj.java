package liveProject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static examples.ActionsBase.doSwipe;


public class Activity3 {
    AppiumDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        // Appium Server URL
        URL servrURL = new URL("http://localhost:4723/wd/hub");

        // Initialize AndroidDriver
        driver = new AndroidDriver(servrURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://www.training-support.net/selenium");
    }
    @Test
    public void addListTest() throws InterruptedException {
        Dimension size = driver.manage().window().getSize();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@text='Get Started!']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.View[@text='JavaScript Alerts \uF06A Explore the 3 types of JavaScript alerts. ']")));
        Point start = new Point((int) (size.width * 0.5), (int) (size.height * 0.8));
        Point end = new Point((int) (size.width * 0.5), (int) (size.height * 0.2));
        doSwipe(driver, start, end, 200);

        //Find and click To-Do List
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.View[@text='To-Do List \uF0AE Elements get added at run time ']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']")));
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']")).sendKeys("Add tasks to list");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Add Task']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.View[@text='Add tasks to list']")));
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']")).sendKeys("Get number of tasks");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Add Task']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.View[@text='Get number of tasks']")));
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']")).sendKeys("Clear the list");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Add Task']")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.View[@text='Add tasks to list']")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.View[@text='Get number of tasks']")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.View[@text='Clear the list']")).click();
        Thread.sleep(1000);
        List<WebElement> tasks = driver.findElements(AppiumBy.xpath("//android.view.View[@resource-id='tasksList']//android.view.View[contains(@text, ' ')]"));
        Assert.assertEquals(tasks.size(), 4);
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.View[@text=' Clear List']")).click();
        tasks = driver.findElements(AppiumBy.xpath("android.view.View[@resource-id='tasksList']//android.view.View[contains(@text, ' ')]"));
        Assert.assertEquals(tasks.size(), 0);
    }

    @AfterClass
    public void closeApp() {
        driver.quit();
    }
}