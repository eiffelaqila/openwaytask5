package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By searchBar = By.xpath("//div[@class='search-bar']//input[@id='filter_name']");
	private By addToCartButton = By.xpath("//button[normalize-space()='Add to Cart']");

	public ProductPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void searchProduct(String productName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
		driver.findElement(searchBar).sendKeys(productName + "\n");
	}

	public void openProduct(String productName) {
		By product = By.partialLinkText(productName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(product));
		driver.findElement(product).click();
	}

	public void addToCart() {
		driver.findElement(addToCartButton).click();
	}
}
