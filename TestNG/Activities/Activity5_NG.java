
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Activity5 {
    // Declare the WebDriver object
    private WebDriver driver;
    @BeforeClass
    public void beforeMethod(){
        // Set up the Firefox driver
        WebDriverManager.firefoxdriver().setup();
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open browser
        driver.get("https://www.training-support.net/selenium/target-practice");
    }
    @Test (groups = {"HeaderTests", "ButtonTests"})
    public void pageTitleTest() {
        String title = driver.getTitle();
        System.out.println("Title is: " + title);
        Assert.assertEquals(title, "Target Practice");
    }

    @Test (groups = {"HeaderTests"})
    public void HeaderTest1() {
        WebElement header3 = driver.findElement(By.cssSelector("h3#third-header"));
        Assert.assertEquals(header3.getText(), "Third header");
    }

    @Test (groups = {"HeaderTests"})
    public void HeaderTest2() {
        WebElement header5 = driver.findElement(By.cssSelector("h3#third-header"));
        Assert.assertEquals(header5.getCssValue("color"), "rgb(251, 189, 8)");
    }

    @Test (groups = {"ButtonTests"})
    public void ButtonTest1() {
        WebElement button1 = driver.findElement(By.cssSelector("button.olive"));
        Assert.assertEquals(button1.getText(), "Olive");
    }

    @Test (groups = {"ButtonTests"})
    public void ButtonTest2() {
        WebElement button2 = driver.findElement(By.cssSelector("button.brown"));
        Assert.assertEquals(button2.getCssValue("color"), "rgb(255, 255, 255)");
    }
    @AfterClass
    public void afterMethod() {
        //Close the browser
        driver.close();
    }
}
/**
 * <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
 * <suite name="FST_TestNG Suite">
 *     <test name="TestNG Training">
 *
 *         <groups>
 *             <run>
 *                 <include name="HeaderTests" />
 *                 <!--include name="ButtonTests" />-->
 *                 <!--<exclude name="HeaderTests" />-->
 *             </run>
 *         </groups>
 *         <classes>
 *             <class name="Activity5" />
 *         </classes>
 *     </test>
 * </suite>
 */