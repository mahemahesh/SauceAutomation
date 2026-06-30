package tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DriverManager;

public class LoginTest {
	WebDriver driver;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setUp() {
		driver = DriverManager.getDriver();
		driver.get("https://www.saucedemo.com/");
		loginPage = new LoginPage(driver);
	}
	@Test
	 public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed - not on inventory page");
    }
	@Test
	public void testInvalidLogin() {
		loginPage.login("locked_out_user", "secret_sauce");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("locked out"), "Expected locked out error message");
	}
	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}

}
