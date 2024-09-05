
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class TS4_ValidateUserTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void ValidateUser() throws InterruptedException, IOException {
        //Login
        String username = "locked_out_user";
        String password = "secret_sauce";
        WebElement userNameTextBox = driver.findElement(By.id("user-name"));
        userNameTextBox.sendKeys(username);

        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        Thread.sleep(5000);



        //Check that after login with "locked_out_user" user, The system will display error message
        WebElement errormessage1Text = driver.findElement(By.className("error-message-container"));
        assertEquals(errormessage1Text.getText(),("Epic sadface: Sorry, this user has been locked out."));

        //Check that can't go to product page when input the url directly
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement errormessage2Text = driver.findElement(By.className("error-message-container"));
        assertEquals(errormessage2Text.getText(),("Epic sadface: You can only access '/inventory.html' when you are logged in."));

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
