package practice.basics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateSysCurrentDate {

	public static void main(String[] args) {
		Date dateobj= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String actualdate = sdf.format(dateobj);
		System.out.println(actualdate);
		
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String datareq = sdf.format(cal.getTime());
		System.out.println(datareq);
		
		
		
	}

}
