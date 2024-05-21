package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Sample_Data_Driven_Testing {

	public static void main(String[] args) throws IOException {
		//step1: get the java representation object of the physical file
		FileInputStream fis= new FileInputStream("C:\\Users\\DELL\\Desktop\\CommonData.properties");
		
		//step2: using properties class,load all the keys
		Properties prob= new Properties();
		prob.load(fis);
		
		//step3:get the value based on key
		System.out.println(prob.getProperty("browser"));
		
	}

}
