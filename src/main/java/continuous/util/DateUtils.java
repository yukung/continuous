package continuous.util;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
	
	public static class Period {
		
		private Date from;
		
		private Date to;
		
		
		public Period(final Date from, final Date to) {
			this.from = from;
			this.to = to;
		}
		
		public Date getFrom() {
			return from;
		}
		
		public Date getTo() {
			return to;
		}
		
		public void setFrom(Date from) {
			this.from = from;
		}
		
		public void setTo(Date to) {
			this.to = to;
		}
		
	}
	
	
	public static Period currentPeriod() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int actualMinimum = cal.getActualMinimum(Calendar.DATE);
		cal.clear();
		cal.set(year, month, actualMinimum);
		Date from = cal.getTime();
		
		Date to = Calendar.getInstance().getTime();
		return new Period(from, to);
	}
	
	private DateUtils() {
		// For utility class can not be instantiated
	}
}
