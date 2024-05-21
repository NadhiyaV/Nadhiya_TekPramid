package practice.contacts_Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		//create Organization
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
		String ORGNAME = wb.getSheet("ORG").getRow(7).getCell(2).toString()+random;
		String CONTACTNAME = wb.getSheet("CONTACT").getRow(7).getCell(3).toString()+random;
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//create Contacts
		Thread.sleep(2000);
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(CONTACTNAME);
		String parentid = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		Set<String> allwindows = driver.getWindowHandles();
		allwindows.remove(parentid);
		for(String ids:allwindows) {
		driver.switchTo().window(ids);
		String Curl=driver.getCurrentUrl();
		if(Curl.contains("module=Accounts")) {
			break;
		}
		}
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();
		driver.switchTo().window(parentid);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String headermsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headermsg.contains(CONTACTNAME)) {
			System.out.println(CONTACTNAME +"Verified============PASSED");
		}
		else {
			System.out.println(CONTACTNAME +"Verified============FAILED");
		}
		
	String actualorgname= driver.findElement(By.xpath("//td[@class='dvtCellInfo' and @id='mouseArea_Organization Name']")).getText();
	System.out.println(actualorgname);
	if(actualorgname.trim().equals(ORGNAME)) {
		System.out.println(actualorgname + " =========passed");
	}
	else {
		System.out.println(actualorgname + " ===========FAILED");
	}
			
	}

}
