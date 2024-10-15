package activities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class Activity1 {
    WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired Capabilities
        UiAutomator2Options caps = new UiAutomator2Options();
        caps.setPlatformName("android");
        caps.setAutomationName("UiAutomator2");
        caps.setAppPackage("com.android.calculator2");
        caps.setAppActivity(".Calculator");
        caps.noReset();

        //Appium Server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //Initialize Android Driver with above values
        driver = new AndroidDriver(serverURL, caps);
    }

    @Test
    public void multiplicationTest() throws IOException {
        //Find digit 5
        driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_5")).click();
        //Find multiply sign and click it
        driver.findElement(AppiumBy.accessibilityId("multiply")).click();
        //Find digit 2
        driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_2")).click();
        //Assertions
        String result = driver.findElement(By.id("result")).getText();
        //Take a screenshot
        takeScreenshot(driver);
        assertEquals(result, "10");
    }

    public static void takeScreenshot(WebDriver driver) throws IOException {
        //Take the screenshot
        File tempImg = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Create save location
        File screenshot = new File("src/test/resources/screenshot.jpg");
        //Copy temp data to your file
        FileUtils.copyFile(tempImg, screenshot);

        //Get file path for screenshot
        String imgPath="../" + screenshot;
        //Add it to HTML report
        String imgHtml = "<img src =" + imgPath + " width='500px' height='750px' />";
        Reporter.log(imgHtml);
    }

    @AfterClass
    public void tearDown() {
        //Close the app
        driver.quit();
    }
}
