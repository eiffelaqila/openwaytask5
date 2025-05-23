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

public class TC002_AddProductToCartTest extends TestBase {
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
	public void testAddProductToCart() {
		try {
			String loginEmail = "eiffelaqila@gmail.com";
			String loginPassword = "Dummy!Password123";
			String productURL = "/p/9780385738781/the-death-cure-book-three-of-the-maze-runner-series";
			String productName = "The Death Cure: Book Three of the Maze Runner Series";
			String productPrice = "Rp 250,000";
			
			System.out.println("------------Starting TC002_AddProductToCartTest------------");
			waitSpinnerHidden();
			loginPage.login(loginEmail, loginPassword);
			waitSpinnerHidden();

			productPage.openProductByURL(productURL);
			waitSpinnerHidden();
			productPage.addToCart();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Notification-Modal")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("Notification-Modal")));

			cartPage.openCart();
			waitSpinnerHidden();

			System.out.println("------------Verifying------------");
			boolean isPresent = cartPage.verifyProductInCart(productName, productPrice, "1");

			Assert.assertTrue(isPresent);
			System.out.println("✅ TC002_AddProductToCartTest PASSED");
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
