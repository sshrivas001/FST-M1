package liveProject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class Activity1 {
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> noOfTasks;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired Capabilities
        UiAutomator2Options caps = new UiAutomator2Options();
        caps.setPlatformName("android");
        caps.setAutomationName("UiAutomator2");
        caps.setAppPackage("com.google.android.apps.tasks");
        caps.setAppActivity(".ui.TaskListsActivity");
        caps.noReset();

        //Appium Server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //Initialize Android Driver with above values
        driver = new AndroidDriver(serverURL, caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void addNewTask() {

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Create new task']")));
        driver.findElement(AppiumBy.accessibilityId("Create new task")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title")));
        WebElement inputFirst = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title"));
        inputFirst.sendKeys("Complete Activity with Google Tasks");
        driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_done")).click();

        //wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("tasks_fab")));
        driver.findElement(AppiumBy.accessibilityId("Create new task")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title")));
        WebElement inputSecond = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title"));
        inputSecond.sendKeys("Complete Activity with Google Keep");
        driver.findElement(AppiumBy.id("add_task_done")).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("tasks_fab")));
        driver.findElement(AppiumBy.accessibilityId("Create new task")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title")));
        WebElement inputThird = driver.findElement(AppiumBy.id("com.google.android.apps.tasks:id/add_task_title"));
        inputThird.sendKeys("Complete the second Activity Google Keep");
        driver.findElement(AppiumBy.id("add_task_done")).click();

        //Assertions

        noOfTasks = driver.findElements(AppiumBy.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.google.android.apps.tasks:id/tasks_list']//android.widget.TextView"));
        Assert.assertEquals(noOfTasks.size(), 3);

    }
}
