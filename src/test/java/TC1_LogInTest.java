import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


public class TC1_LogInTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void LogInSuccess() throws InterruptedException, IOException {
        //Login
        String username = "standard_user";
        String password = "secret_sauce";
        WebElement userNameTextBox = driver.findElement(By.id("user-name"));
        userNameTextBox.sendKeys(username);

        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //Check able to login without any error
        //WebElement element = driver.findElement(By.className()"error-message-container");
        //Assert.assertNull(element);



        //Check that after login,Logout button is visible
        WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
        assertTrue(burgerMenu.isDisplayed());
        burgerMenu.click();
        Thread.sleep(5000);

        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        assertTrue(logoutButton.isDisplayed());

       //TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./SeleniumScreenshots/Screen.png"));

    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
