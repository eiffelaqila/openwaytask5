package testCases;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.LoginPage;

public class TC006_RemoveProductFromCartTest extends TestBase {
	LoginPage loginPage;
	CartPage cartPage;
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver, wait);
	}

	@Test
	public void testRemoveProductFromCar() {
		try {
			String loginEmail = "eiffelaqila@gmail.com";
			String loginPassword = "Dummy!Password123";
			String productName = "The Infinite Sea: The";
			String productPrice = "Rp 331,000";
			String productQuantity = "2";
			String calculatedTotal = "Rp 250,000";

			System.out.println("------------Starting TC006_RemoveProductFromCartTest------------");
			waitSpinnerHidden();
			loginPage.login(loginEmail, loginPassword);
			waitSpinnerHidden();

			cartPage.openCart();
			waitSpinnerHidden();
			cartPage.removeProduct(productName);
			waitSpinnerHidden();

			System.out.println("------------Verifying------------");
			boolean isExist = cartPage.verifyProductInCart(productName, productPrice, productQuantity);
			boolean isPriceEqual = cartPage.verifyCartTotal(calculatedTotal);

			Assert.assertTrue(!isExist);
			Assert.assertTrue(isPriceEqual);

			System.out.println("✅ TC006_RemoveProductFromCartTest PASSED");
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail("❌ Product exist or total is not correct");
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) driver.quit();
	}
}
