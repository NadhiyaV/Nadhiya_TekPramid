package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Products {

	public Products(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
}
