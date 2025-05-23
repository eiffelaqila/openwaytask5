package testCases;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

public class TC003_AddSearchedProductToCartTest extends TestBase {
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver, wait);
		cartPage = new CartPage(driver, wait);
	}

	@Test
	public void testAddSearchedProductToCart() {
		try {
			String loginEmail = "eiffelaqila@gmail.com";
			String loginPassword = "Dummy!Password123";
			String searchTerm = "infinite sea yancey";
			String productName = "The Infinite Sea: The";
			String productPrice = "Rp 331,000";

			System.out.println("------------Starting TC003_AddSearchedProductToCartTest------------");
			waitSpinnerHidden();
			loginPage.login(loginEmail, loginPassword);
			waitSpinnerHidden();

			productPage.searchProduct(searchTerm);
			waitSpinnerHidden();
			productPage.openProduct(productName);
			waitSpinnerHidden();
			productPage.addToCart();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Notification-Modal")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("Notification-Modal")));

			cartPage.openCart();
			waitSpinnerHidden();

			System.out.println("------------Verifying------------");
			boolean isPresent = cartPage.verifyProductInCart(productName, productPrice, "1");

			Assert.assertTrue(isPresent);
			System.out.println("✅ TC003_AddSearchedProductToCartTest PASSED");
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail("❌ Product not found in cart!");
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) driver.quit();
	}
}
