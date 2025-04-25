import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

public class TaskTest extends TestBase {
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
	public void testTaskOpenWay() {
		loginPage.login("eiffelaqila@gmail.com", "Dummy!Password123");
		waitSpinnerHidden();

		productPage.searchProduct("The Infinite Sea");
		waitSpinnerHidden();
		productPage.openProduct("The 5th Wave: The Infinite");
		waitSpinnerHidden();
		productPage.addToCart();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Notification-Modal")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("Notification-Modal")));

		cartPage.openCart();
		waitSpinnerHidden();
		cartPage.verifyProductInCart("The 5th Wave: The Infinite");

		System.out.println("✅ Product successfully added to cart!");
	}
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) driver.quit();
	}
}
