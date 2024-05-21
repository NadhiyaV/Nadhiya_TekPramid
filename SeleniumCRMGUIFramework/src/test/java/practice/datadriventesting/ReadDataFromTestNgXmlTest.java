package practice.datadriventesting;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromTestNgXmlTest {
	@Test
	public void sampleTest(XmlTest test) {
		Reporter.log("execute sampleTest");
		System.out.println(test.getParameter("browser"));
		System.out.println(test.getParameter("url"));
		System.out.println(test.getParameter("username"));
		System.out.println(test.getParameter("password"));
	}

}
