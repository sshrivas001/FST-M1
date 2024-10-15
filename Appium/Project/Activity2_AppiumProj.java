package liveProject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;
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

public class Activity2 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options task = new UiAutomator2Options();
        task.setPlatformName("android");
        task.setAutomationName("UiAutomator2");
        task.setAppPackage("com.google.android.keep");
        task.setAppActivity(".activities.BrowseActivity");
        task.noReset();

        // Appium Server URL
        URL servrURL = new URL("http://localhost:4723/wd/hub");

        // Initialize AndroidDriver
        driver = new AndroidDriver(servrURL, task);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void addNoteTest() {
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("New text note")));
        driver.findElement(AppiumBy.accessibilityId("New text note")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.google.android.keep:id/edit_note_text']")));
        driver.findElement(AppiumBy.id("com.google.android.keep:id/edit_note_text")).sendKeys("This is a note");
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.google.android.keep:id/editable_title']")));
        driver.findElement(AppiumBy.id("com.google.android.keep:id/editable_title")).sendKeys("Note");
        driver.findElement(AppiumBy.accessibilityId("Navigate up")).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("New photo note")));
        WebElement title = driver.findElement(AppiumBy.id("com.google.android.keep:id/index_note_title"));
        Assert.assertEquals(title.getText(), "Note");
    }
    @AfterClass
    public void closeApp() {
        driver.quit();
    }
}