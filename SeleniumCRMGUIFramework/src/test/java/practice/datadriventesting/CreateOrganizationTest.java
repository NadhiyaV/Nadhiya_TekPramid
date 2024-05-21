package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.asynchttpclient.handler.resumable.PropertiesBasedResumableProcessor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class CreateOrganizationTest {

	@Test
	/*
	 * FileInputStream fis= new
	 * FileInputStream("‪C:\\Users\\DELL\\Desktop\\CommonData (2).properties");
	 * Properties prob= new Properties(); prob.load(fis);
	 */
public void createorgtest(XmlTest test) throws FileNotFoundException, IOException, ParseException {
	//read data from command line
		JSONParser parser =new JSONParser();
		Object obj = parser.parse(new FileReader("C:\\Users\\DELL\\Desktop\\appCommonData1.json"));
		JSONObject map = (JSONObject) obj;
		
		String BROWSER = test.getParameter("browser");
		String URL = test.getParameter("url");
		String USERNAME = test.getParameter("username");
		String PASSWORD = test.getParameter("password");
		
		/*
		 * FileInputStream fis= new
		 * FileInputStream("C:\\Users\\DELL\\Desktop\\ORGANIZATION.xlsx"); Workbook
		 * wb=WorkbookFactory.create(fis); Sheet sheetname = wb.getSheet("ORG"); Row row
		 * = sheetname.getRow(1);
		 */

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
		
		
		driver.findElement(By.name("accountname")).sendKeys("Tek  Pyramid"+random);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		WebElement orgnameEle = driver.findElement(By.xpath("//span[contains(text(),'Pyramid')]"));
		String orgname = orgnameEle.getText();
		System.out.println(orgname);
		driver.findElement(By.xpath("(//img[@style='padding: 0px;padding-left:5px'])[1]")).click();
		driver.findElement(By.linkText("Sign Out")).click();
	
	}
}
