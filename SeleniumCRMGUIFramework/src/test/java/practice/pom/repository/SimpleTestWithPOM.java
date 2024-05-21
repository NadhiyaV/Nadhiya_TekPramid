package practice.pom.repository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SimpleTestWithPOM {

	@FindBy(name="user_name")
	WebElement username;
	@FindBy(name="user_password") 
	WebElement password;
	@FindBy(id="submitButton")
	WebElement loginbtn;
	@FindBy(linkText = "products")
	WebElement products;
	
	@Test
	public void simpleTest() {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		SimpleTestWithPOM s=PageFactory.initElements(driver, SimpleTestWithPOM.class);
		s.username.sendKeys("admin");
		s.password.sendKeys("password");
		s.loginbtn.click();
	}
}
