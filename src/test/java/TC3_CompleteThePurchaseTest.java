import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TC3_CompleteThePurchaseTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

    }

    @Test
    public void CompleteThePurchase() throws IOException {
        //Login
        String username = "standard_user";
        String password = "secret_sauce";
        WebElement userNameTextBox = driver.findElement(By.id("user-name"));
        userNameTextBox.sendKeys(username);

        WebElement passwordTextBox = driver.findElement(By.id("password"));
        passwordTextBox.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //Add first item to cart (the product which has $15.99 price)
        WebElement addToCartFirstItemButton = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addToCartFirstItemButton.click();

        //Add second item to cart (the product which has $15.99 price)
        WebElement addToCartSecondItemButton = driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
        addToCartSecondItemButton.click();

        //Go to cart page
        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        //Click checkout button
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        //Input customer information
        WebElement firstNameTextBox = driver.findElement(By.id("first-name"));
        firstNameTextBox.sendKeys("test_firstname");

        WebElement lastNameTextBox = driver.findElement(By.id("last-name"));
        lastNameTextBox.sendKeys("test_lastname");

        WebElement postalCodeTextBox = driver.findElement(By.id("postal-code"));
        postalCodeTextBox.sendKeys("10010");

        //Click continue
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        //Log The checkout summary
        String orderSummary = "";
        WebElement cartList = driver.findElement(By.className("cart_list"));
        List<WebElement> cartItems= driver.findElements(By.className("cart_item"));
        System.out.println("Order Summary");
        for (WebElement i : cartItems) {
            WebElement itemNameText = i.findElement(By.className("inventory_item_name"));
            WebElement itemPriceText = i.findElement(By.className("inventory_item_price"));
            orderSummary += "Item Name : "+itemNameText.getText()+"\n"+"Item Price : "+itemPriceText.getText()+"\n";
            orderSummary += "############################################"+"\n";

        }

        WebElement totalPriceText = driver.findElement(By.className("summary_total_label"));
        orderSummary +=  totalPriceText.getText();
        System.out.println(orderSummary);


        //Confirm purchase
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        //Check complete purchase page , to make sure the purchase is successful
        WebElement checkoutCompletePage = driver.findElement(By.id("checkout_complete_container"));
        assertTrue(checkoutCompletePage.isDisplayed());

        WebElement thankyouText = driver.findElement(By.className("complete-header"));
        assertEquals(thankyouText.getText(),("Thank you for your order!"));

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
