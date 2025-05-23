package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	private WebDriver driver;

	private By signInBtn = By.linkText("Sign In");
	private By email = By.name("email");
	private By password = By.name("password");
	private By loginBtn = By.id("button-login");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String emailStr, String passwordStr) {
		driver.findElement(signInBtn).click();
		driver.findElement(email).sendKeys(emailStr);
		driver.findElement(password).sendKeys(passwordStr);
		driver.findElement(loginBtn).click();
	}
	
	public boolean verifyLoggedIn() {
		List<WebElement> signInElements = driver.findElements(By.linkText("Sign In"));
	    return signInElements.isEmpty();
	}
}
