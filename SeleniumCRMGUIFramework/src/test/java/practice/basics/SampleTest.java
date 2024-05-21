package practice.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleTest {

	public static void main(String[] args) throws SQLException {

		//step 1: load/register the database driver
		Driver driverref= new Driver();
		DriverManager.registerDriver(driverref);
		//step 2:connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		System.out.println("======connection done=======");
		//step3 : create sql statement
		Statement st = conn.createStatement();
		//step4:execute select query& getresult
		ResultSet rs = st.executeQuery("select * from Project");
		while(rs.next()) {
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
		}
		//step 5: close the connection
		conn.close();
		
		}

}
