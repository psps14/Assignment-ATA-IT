import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TS2_SortingThePriceTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testIsTrueProductSortPriceHightToLow() throws IOException {
        //Login
        String username = "standard_user";
        String password = "secret_sauce";
        WebElement userNameTextBox = driver.findElement(By.id("user-name"));
        userNameTextBox.sendKeys(username);

        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //Click sorting and select "Price(High to Low)"
        Select sortingSelector = new Select(driver.findElement(By.className("product_sort_container")));
        sortingSelector.selectByVisibleText("Price (high to low)");

        // Check that the highest price is displayed as the first product
        WebElement itempriceText = driver.findElement(By.className("inventory_item_price"));
        assertEquals(itempriceText.getText(), "$49.99");

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
