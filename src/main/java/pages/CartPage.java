package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By cartBtn = By.xpath("//div[@id='show-your-cart']//a[@class='single-icon']");
	private By addToCartButton = By.xpath("//button[normalize-space()='Add to Cart']");
	private By emptyCartText = By.xpath("//div[text()=\"Your shopping cart is empty\"]");

	public CartPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void openCart() {
		WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
		cartButton.click();
	}

	public boolean verifyProductInCart(String productName, String productPrice, String quantity) {
		String xpath = String.format(
	        "//div[contains(@class,'row-cart-product')]["
	      + ".//p[contains(@class,'product-name')]//a[contains(text(),'%s')] and "
	      + ".//div[contains(text(),'%s')] and "
	      + ".//input[contains(@class,'input-number') and @value='%s']"
	      + "]",
	      productName, productPrice, quantity
	    );

		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		return !elements.isEmpty();
	}

	public boolean verifyCartTotal(String expectedPrice) {
		String xpath = String.format("//li[contains(text(),'Total') and contains(.,'%s')]", expectedPrice);
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
	    return !elements.isEmpty();
	}

	public void changeQuantity(String productName, String quantity) {
		String xpath = String.format(
			"//div[contains(@class,'row-cart-product')]"
	      + "[.//p[contains(@class,'product-name')]//a[contains(text(),'%s')]]"
	      + "//input[contains(@class,'input-number')]",
	      productName
	     );
	    driver.findElement(By.xpath(xpath)).sendKeys(Keys.chord(Keys.CONTROL, "a"), quantity, Keys.ENTER);
	}

	public void removeProduct(String productName) {
		String xpath = String.format(
			"//div[contains(@class,'row-cart-product')]"
	      + "[.//p[contains(@class,'product-name')]//a[contains(text(),'%s')]]"
	      + "//a[contains(@class,'btn-cart-remove')]",
	      productName
	     );
	    driver.findElement(By.xpath(xpath)).click();
	}

	public boolean verifyCartEmpty() {
		List<WebElement> elements = driver.findElements(emptyCartText);
	    return !elements.isEmpty();
	}

	public void addToCart() {
		driver.findElement(addToCartButton).click();
	}
}
