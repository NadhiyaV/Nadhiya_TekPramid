package practice.contacts_Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Contacts_With_StratsDateAndEndDate {

	public static void main(String[] args) throws IOException {
		FileInputStream fis= new FileInputStream("C:\\Users\\DELL\\Desktop\\DATA\\CommonData (2).properties");
		Properties prob= new Properties();
		prob.load(fis);
		String BROWSER=prob.getProperty("browser");
		String URL=prob.getProperty("url");
		String USERNAME=prob.getProperty("username");
		String PASSWORD=prob.getProperty("password");
		
		
		FileInputStream fis1= new FileInputStream("C:\\Users\\DELL\\Desktop\\DATA\\ORGANIZATION.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		String CONTACTNAME = wb.getSheet("CONTACT").getRow(1).getCell(2).getStringCellValue();
		
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}

		
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(CONTACTNAME);
		
		//To Create start date and EndDate
		Date dateobj= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String startdate = sdf.format(dateobj);
		
		
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String enddate = sdf.format(cal.getTime());
				
		driver.findElement(By.xpath("//input[@id='jscal_field_support_start_date']")).clear();
		driver.findElement(By.xpath("//input[@id='jscal_field_support_start_date']")).sendKeys(startdate);
		driver.findElement(By.xpath("//input[@id='jscal_field_support_end_date']")).clear();
		driver.findElement(By.xpath("//input[@id='jscal_field_support_end_date']")).sendKeys(enddate);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		String actalstdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actalstdate.equals(startdate)) {
			System.out.println(actalstdate + "verified =====PASS");
		}
		else {
			System.out.println(actalstdate + "verified =====fail");
		}
	String endda = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
	if(endda.equals(enddate)) {
		System.out.println(endda+"verification===========pass");
	}
	else {
		System.out.println(endda+"verification==============fail");
	}
	
			
		
	}

}
