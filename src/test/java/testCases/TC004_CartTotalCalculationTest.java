package testCases;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.LoginPage;

public class TC004_CartTotalCalculationTest extends TestBase {
	LoginPage loginPage;
	CartPage cartPage;
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver, wait);
	}

	@Test
	public void testCartTotalCalculation() {
		try {
			String loginEmail = "eiffelaqila@gmail.com";
			String loginPassword = "Dummy!Password123";
			String calculatedTotal = "Rp 581,000";

			System.out.println("------------Starting TC004_CartTotalCalculationTest------------");
			waitSpinnerHidden();
			loginPage.login(loginEmail, loginPassword);
			waitSpinnerHidden();

			cartPage.openCart();
			waitSpinnerHidden();

			System.out.println("------------Verifying------------");
			boolean isPriceEqual = cartPage.verifyCartTotal(calculatedTotal);

			Assert.assertTrue(isPriceEqual);
			System.out.println("✅ TC004_CartTotalCalculationTest PASSED");
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail("❌ Total isn't calculated right!");
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) driver.quit();
	}
}
