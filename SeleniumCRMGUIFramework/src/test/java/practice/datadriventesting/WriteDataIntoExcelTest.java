package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		FileInputStream fis= new FileInputStream("C:\\Users\\DELL\\Desktop\\ORGANIZATION.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("ORG");
		Row row = sheet.getRow(1);
		Cell cell = row.createCell(4);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("PASS");
		FileOutputStream fos= new FileOutputStream("C:\\Users\\DELL\\Desktop\\ORGANIZATION.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("+++++++++++++++executed+++++++++++++++++++");
		
	}

}
