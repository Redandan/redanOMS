package tw.org.w3;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 共用函式
 *
 * @author P-C Lin
 */
public class Utils {

	public static String toDateByZoneId(Date date, String zoneId) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		LocalDateTime localDateTime = LocalDateTime.of(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1, gregorianCalendar.get(Calendar.DAY_OF_MONTH), gregorianCalendar.get(Calendar.HOUR_OF_DAY), gregorianCalendar.get(Calendar.MINUTE), gregorianCalendar.get(Calendar.SECOND), gregorianCalendar.get(Calendar.MILLISECOND));

		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(zoneId));
		return DateTimeFormatter.ISO_OFFSET_DATE.format(zonedDateTime);
	}

	public static String toDateTimeByZoneId(Date date, String zoneId) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		LocalDateTime localDateTime = LocalDateTime.of(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH) + 1, gregorianCalendar.get(Calendar.DAY_OF_MONTH), gregorianCalendar.get(Calendar.HOUR_OF_DAY), gregorianCalendar.get(Calendar.MINUTE), gregorianCalendar.get(Calendar.SECOND), gregorianCalendar.get(Calendar.MILLISECOND));

		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(zoneId));
		return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(zonedDateTime);
	}
}
