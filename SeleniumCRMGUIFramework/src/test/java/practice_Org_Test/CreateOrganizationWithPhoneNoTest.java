package practice_Org_Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationWithPhoneNoTest {

	public static void main(String[] args) throws IOException {
		FileInputStream fis= new FileInputStream("C:\\Users\\DELL\\Desktop\\DATA\\CommonData (2).properties");
		Properties prob= new Properties();
		prob.load(fis);
		String BROWSER=prob.getProperty("browser");
		String URL=prob.getProperty("url");
		String USERNAME=prob.getProperty("username");
		String PASSWORD=prob.getProperty("password");
		
WebDriver driver= null;
		
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		//create Random number
		Random r= new Random();
		int random = r.nextInt(1000);
		//Read data from Excel file
		FileInputStream fis1= new FileInputStream("C:\\Users\\DELL\\Desktop\\DATA\\ORGANIZATION.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		String ORGNAME = wb.getSheet("ORG").getRow(1).getCell(2).toString()+random;
		String PHONENO = wb.getSheet("ORG").getRow(7).getCell(3).getStringCellValue();
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		driver.findElement(By.id("phone")).sendKeys(PHONENO);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		//To verify the phone no
		String actualphoneno = driver.findElement(By.id("dtlview_Phone")).getText();
		if(actualphoneno.equals(PHONENO)) {
			System.out.println(actualphoneno + "is verified=========PASS");
		}
		else {
			System.out.println(actualphoneno + "is verified=========FAIL");
		}
		/*
		 * WebElement orgnameEle =
		 * driver.findElement(By.xpath("//span[contains(text(),'Pyramid')]")); String
		 * orgname = orgnameEle.getText(); System.out.println(orgname);
		 */
		WebElement logoutimg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action= new Actions(driver);
		action.moveToElement(logoutimg).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
