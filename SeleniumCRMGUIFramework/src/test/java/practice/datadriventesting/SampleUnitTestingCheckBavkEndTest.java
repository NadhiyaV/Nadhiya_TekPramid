package practice.datadriventesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import dev.failsafe.internal.util.Assert;

public class SampleUnitTestingCheckBavkEndTest {
@Test
public void CheckBackend() throws SQLException {
	String expectedname="keyan";
	boolean flag= false;
	Driver driver= new Driver();
	DriverManager.registerDriver(driver);
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	Statement stat = conn.createStatement();
	ResultSet resultset = stat.executeQuery("select * from Project");
	while(resultset.next()) {
		String actualdata = resultset.getString(2);
		if(expectedname.equals(actualdata)) {
			flag=true;
			System.out.println(expectedname+"is present++++++++++++++PASS+++++++++++");
		}
	}
	if(flag==false) {
		System.out.println(expectedname+"is NOT present++++++++++++++FAIL+++++++++++");
		org.testng.Assert.fail();
	}
	conn.close();
	
}
}
