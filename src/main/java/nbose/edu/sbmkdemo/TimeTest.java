package nbose.edu.sbmkdemo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;

public class TimeTest {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis()) ;
		
		LocalDateTime ldt = LocalDateTime.ofInstant(ts.toInstant(), ZoneOffset.of( "+05:30" ));
		
		System.out.println(ldt);

	}

}
