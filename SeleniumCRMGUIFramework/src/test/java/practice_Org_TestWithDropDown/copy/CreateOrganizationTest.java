package practice_Org_TestWithDropDown.copy;

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

public class CreateOrganizationTest {

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
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		//To verify the header msg
		String headermsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headermsg.contains(ORGNAME)) {
			System.out.println(ORGNAME+"is present=====PASSED");
		}
		else {
			System.out.println(ORGNAME+"is not present=====Failed");
		}
		//To verify the Organization name
		WebElement actualdata = driver.findElement(By.id("dtlview_Organization Name"));
		if(actualdata.equals(ORGNAME)) {
			System.out.println(actualdata+"is present=====PASSED");
		}
		else {
			System.out.println(actualdata+"is not present=====FAILED");
		}
		/*
		 * WebElement orgnameEle =
		 * driver.findElement(By.xpath("//span[contains(text(),'Pyramid')]")); String
		 * orgname = orgnameEle.getText(); System.out.println(orgname);
		 */
		driver.findElement(By.xpath("(//img[@style='padding: 0px;padding-left:5px'])[1]")).click();
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
