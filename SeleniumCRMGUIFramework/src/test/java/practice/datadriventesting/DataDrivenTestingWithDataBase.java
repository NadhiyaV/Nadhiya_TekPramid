package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class DataDrivenTestingWithDataBase {

	public static void main(String[] args) throws SQLException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://106.51.90.215:8084/dashboard/projects");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create')]")).click();
		Random r= new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("projectName")).sendKeys("TekPyramid"+random);
		driver.findElement(By.name("createdBy")).sendKeys("deepak");
		WebElement dropdownelement = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		dropdownelement.click();
		Select dropdown= new Select(dropdownelement); 
		dropdown.selectByVisibleText("Completed");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//To verify Project name in DataBase
		
		Boolean flag=false;
		String expectedata="TekPyramid_0905";
		String actualdata="";
		Driver driver1= new Driver();
		DriverManager.registerDriver(driver1);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		Statement stat = conn.createStatement();
		ResultSet resultset = stat.executeQuery("select * from project");
		while(resultset.next()){
			actualdata = resultset.getString(4);
			if(expectedata.equals(actualdata)) {
				flag=true;
				System.out.println(actualdata+"present in DataBase");
			
			}
		}
		if(flag==false) {
			System.out.println(actualdata+"is not Present in DataBase");
		}
		
		
	}
}
