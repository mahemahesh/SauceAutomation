package tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.DriverManager;

public class CheckoutTest {
	WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }
    @Test
    public void testEndToEndCheckout() throws InterruptedException{
        loginPage.login("standard_user", "secret_sauce");
        System.out.println("Step 1: Logged in");
        Thread.sleep(2000);
        inventoryPage.addItemToCartByName("Sauce Labs Backpack");
        System.out.println("Step 2: Added item to cart");
        Thread.sleep(2000);
        String cartCount = inventoryPage.getCartItemCount();
        System.out.println("Step 3: Cart badge shows -> " + cartCount);
        Assert.assertEquals(cartCount, "1");
        Thread.sleep(2000);

        inventoryPage.goToCart();
        System.out.println("Step 4: Navigated to cart");
        Thread.sleep(2000);
        cartPage.clickCheckout();
        System.out.println("Step 5: Clicked checkout");
        Thread.sleep(2000);

        checkoutPage.fillCheckoutInfo("Mahesh", "R", "560001");
        System.out.println("Step 6: Filled checkout info and clicked continue");
        Thread.sleep(2000);
        checkoutPage.clickFinish();
        System.out.println("Step 7: Clicked finish");
        Thread.sleep(2000);

        String completeMsg = checkoutPage.getCompleteMessage();
        System.out.println("Step 8: Complete message -> '" + completeMsg + "'");
        Assert.assertEquals(completeMsg, "Thank you for your order!");
    }
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        
    }

}
