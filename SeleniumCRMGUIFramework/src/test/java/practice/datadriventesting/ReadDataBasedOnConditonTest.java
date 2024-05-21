package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnConditonTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		String data1="";
		String data2="";
		String data3="";
		boolean flag=false;
		FileInputStream fis= new FileInputStream("C:\\Users\\DELL\\Desktop\\ORGANIZATION.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("ORG");
		int lastrownum = sh.getLastRowNum();
		String expectedtc = "TC_70";
		for(int i=0;i<=lastrownum;i++) {
			String data="";
			try {
			 data=sh.getRow(i).getCell(0).toString();
			 if(data.equals(expectedtc)) {
				 flag=true;
				 data1=sh.getRow(i).getCell(1).toString();
				 data2=sh.getRow(i).getCell(2).toString();
				 data3=sh.getRow(i).getCell(3).toString();
			 }
			}
			catch(Exception e) {}
		}
		if(flag==true) {
		System.out.println(data1);
		System.out.println(data2);
		System.out.println(data3);
		}
		else {
			System.out.println(expectedtc+"is not present");
		}
		wb.close();
		
	}

}
