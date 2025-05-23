package testCases;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.LoginPage;

public class TC005_ModifyCartQuantityTest extends TestBase {
	LoginPage loginPage;
	CartPage cartPage;
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver, wait);
	}

	@Test
	public void testModifyCartQuantity() {
		try {
			String loginEmail = "eiffelaqila@gmail.com";
			String loginPassword = "Dummy!Password123";
			String productName = "The Infinite Sea: The";
			String productPrice = "Rp 331,000";
			String newProductQuantity = "2";
			String calculatedTotal = "Rp 912,000";

			System.out.println("------------Starting TC005_ModifyCartQuantityTest------------");
			waitSpinnerHidden();
			loginPage.login(loginEmail, loginPassword);
			waitSpinnerHidden();

			cartPage.openCart();
			waitSpinnerHidden();
			cartPage.changeQuantity(productName, newProductQuantity);
			waitSpinnerHidden();

			System.out.println("------------Verifying------------");
			boolean isQuantityEqual = cartPage.verifyProductInCart(productName, productPrice, newProductQuantity);
			boolean isPriceEqual = cartPage.verifyCartTotal(calculatedTotal);

			Assert.assertTrue(isQuantityEqual);
			Assert.assertTrue(isPriceEqual);

			System.out.println("✅ TC005_ModifyCartQuantityTest PASSED");
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail("❌ Product quantity or total is not correct");
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) driver.quit();
	}
}
