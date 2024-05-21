package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataDrivenWithIntegration {

	private static final String ORG = null;

	public static void main(String[] args) throws IOException {
		// READ data from common data
		FileInputStream fis= new FileInputStream("C:\\Users\\DELL\\Desktop\\DATA\\CommonData (2).properties");
		Properties prob= new Properties();
		prob.load(fis);
		String BROWSER = prob.getProperty("browser");
		String URL = prob.getProperty("url");
		String USERNAME = prob.getProperty("username");
		String PASSWORD = prob.getProperty("password");
		//generate random number
		Random r= new Random();
		int randomint=r.nextInt(1000);
		//Read test script data from excel
		FileInputStream fis1= new FileInputStream("C:\\Users\\DELL\\Desktop\\DATA\\ORGANIZATION.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("ORG");
		Row row = sh.getRow(1);
		String organame=row.getCell(2).toString() + randomint;
		wb.close();

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
		/*
		 * Random r= new Random(); int random = r.nextInt(1000);
		 */
		
		driver.findElement(By.name("accountname")).sendKeys(organame);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		WebElement orgnameEle = driver.findElement(By.xpath("//span[contains(text(),'Pyramid')]"));
		String orgname = orgnameEle.getText();
		System.out.println(orgname);
		driver.findElement(By.xpath("(//img[@style='padding: 0px;padding-left:5px'])[1]")).click();
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
