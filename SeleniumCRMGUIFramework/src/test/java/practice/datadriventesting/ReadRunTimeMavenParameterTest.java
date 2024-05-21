package practice.datadriventesting;

import org.testng.annotations.Test;

public class ReadRunTimeMavenParameterTest {
@Test
public void runtimeparameterTest() {
	String URL=System.getProperty("url");
	String BROWSER = System.getProperty("browser");
	String USERNAME=System.getProperty("username");
	String PASSWORD=System.getProperty("password");
	
	System.out.println("Environment data==>url==>"+ URL);
	System.out.println("Environment data==>browser==>"+ BROWSER);
	System.out.println("Environment data==>username==>"+ USERNAME);
	System.out.println("Environment data==>password==>"+ PASSWORD);
	
}
}
