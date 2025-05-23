package testCases;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.LoginPage;

public class TC007_EmptyCartTest extends TestBase {
	LoginPage loginPage;
	CartPage cartPage;
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver, wait);
	}

	@Test
	public void testEmptyCart() {
		try {
			String loginEmail = "eiffelaqila@gmail.com";
			String loginPassword = "Dummy!Password123";
			String productName = "The Death Cure: Book Three of the Maze Runner Series";

			System.out.println("------------Starting TC007_EmptyCartTest------------");
			waitSpinnerHidden();
			loginPage.login(loginEmail, loginPassword);
			waitSpinnerHidden();

			cartPage.openCart();
			waitSpinnerHidden();
			cartPage.removeProduct(productName);
			waitSpinnerHidden();

			System.out.println("------------Verifying------------");
			boolean isCartEmpty = cartPage.verifyCartEmpty();

			Assert.assertTrue(isCartEmpty);

			System.out.println("✅ TC007_EmptyCartTest PASSED");
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail("❌ Cart not empty");
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) driver.quit();
	}
}
