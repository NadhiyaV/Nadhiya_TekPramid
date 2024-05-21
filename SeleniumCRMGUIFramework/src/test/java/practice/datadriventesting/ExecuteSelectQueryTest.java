package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteSelectQueryTest {

	public static void main(String[] args) throws SQLException {
Connection conn=null;
	try{
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement stat = conn.createStatement();
		ResultSet resultset = stat.executeQuery("select * from Project");
		while(resultset.next()) {
			System.out.println(resultset.getString(1)+"\t"+resultset.getString(2)+"\t"+resultset.getInt(3)+"\t"+resultset.getString(4));
		}
	}
	catch(Exception e) {
		System.out.println("Handled Exception");
	}
	finally{
		conn.close();
		System.out.println("==========Connection closed==========");
	}
	}

}
