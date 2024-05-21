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
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithDropDownTest {

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
		String INDUSTDD = wb.getSheet("ORG").getRow(4).getCell(3).toString();
		String TYPE = wb.getSheet("ORG").getRow(4).getCell(4).toString();
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		WebElement INDUSTRYELE = driver.findElement(By.name("industry"));
		Select select = new Select(INDUSTRYELE);
		INDUSTRYELE.click();
		select.selectByVisibleText(INDUSTDD);
		WebElement TYPEWEBELE = driver.findElement(By.name("accounttype"));
		Select select1 = new Select(TYPEWEBELE);
		TYPEWEBELE.click();
		select1.selectByVisibleText(TYPE);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		//To verify the dropdown industry and type info
		
		String actualdataindudd = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		if(actualdataindudd.equals(INDUSTDD)) {
			System.out.println(actualdataindudd+"verified===========PASSES");
		}
		else {
			System.out.println(actualdataindudd+"verified=============FAILED");
		}
		//TO VERIFY TYPE DROPDOWN
		String actualltypedd = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		if(actualltypedd.equals(TYPE)) {
			System.out.println(actualltypedd + "verified=========PASSED");
		}
		else {
			System.out.println(actualltypedd+"verified=========FAILED ");
		}
		/*
		 * WebElement orgnameEle =
		 * driver.findElement(By.xpath("//span[contains(text(),'Pyramid')]")); String
		 * orgname = orgnameEle.getText(); System.out.println(orgname);
		 */
		
	}

}
