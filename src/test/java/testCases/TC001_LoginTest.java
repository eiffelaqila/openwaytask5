package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;

public class TC001_LoginTest extends TestBase {
	LoginPage loginPage;
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage(driver);
	}

	@Test
	public void testLogin() {
		try {
			System.out.println("------------Starting TC001_LoginTest------------");
			waitSpinnerHidden();
			loginPage.login("eiffelaqila@gmail.com", "Dummy!Password123");
			waitSpinnerHidden();

			System.out.println("------------Verifying------------");
			boolean isLoggedIn = loginPage.verifyLoggedIn();

			Assert.assertTrue(isLoggedIn);
			System.out.println("✅ TC001_LoginTest PASSED");
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail("❌ User not logged in!");
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) driver.quit();
	}
}
