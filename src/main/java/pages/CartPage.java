package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class CartPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By cartBtn = By.xpath("//div[@id='show-your-cart']//a[@class='single-icon']");
	private By addToCartButton = By.xpath("//button[normalize-space()='Add to Cart']");

	public CartPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void openCart() {
		WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
		cartButton.click();
	}

	public void verifyProductInCart(String productName) {
		try {
			By product = By.partialLinkText(productName);
			boolean isPresent = driver.findElement(product).getText().contains(productName);
	        Assert.assertTrue(isPresent, "❌ Product not found in cart!");
		} catch (NoSuchElementException e) {
			Assert.fail("❌ Product not found in cart!");
		}
	}

	public void addToCart() {
		driver.findElement(addToCartButton).click();
	}
}
