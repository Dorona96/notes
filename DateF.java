package Q2;
import java.io.Serializable;
import java.util.*;
import java.util.HashMap;

/*
 * BY: Doron Sharaby
 * ID: 204862197
 * DATE: 22/5/2021
 * 
 * 
 * 
 * */
public class DateF implements Serializable {
		private int day;
		private int month;
		private int year;

		public enum  MonthName{ January,February,March,April,May,June,July,August,
		September,October,November,December};

		public HashMap<Integer,String> MonthsName = new HashMap<>();

		public DateF(int day, int month, int year){
			this.day = day; 
			this.month = month; 
			this.year = year;
			makeMonthsName();

		}
		
		public DateF() {
			makeMonthsName();
			Date date = new Date();
	        Calendar calendar = new GregorianCalendar();
	        
	        calendar.setTime(date);
	        year = calendar.get(Calendar.YEAR);
	        month = calendar.get(Calendar.MONTH);
	        day = calendar.get(Calendar.DAY_OF_MONTH);
		}

		//set and get functions.
		public int getDay() {	return day; 	}
		public int getMonth() {		return month; }
		public int getYear() {	return year; 	}
		public void setDay(int day) {	this.day = day; }
		public void setMonth(int month) {	this.month = month; }
		public void setYear(int year) {		this.year = year;	}

		@Override
		public boolean equals(Object o) {
	        if(o instanceof DateF){
	            DateF currentN = (DateF)(o);
	            return (currentN.day == this.day && currentN.month == this.month && currentN.year == this.year);
	        }
	        return super.equals(o);
	    }

		@Override
		public int hashCode() {
			return year*10000 + month*100 +day;
		}

		private void makeMonthsName(){
			MonthsName.put(0,"January");
			MonthsName.put(1,"February");
			MonthsName.put(2,"March");
			MonthsName.put(3,"April");
			MonthsName.put(4,"May");
			MonthsName.put(5,"June");
			MonthsName.put(6,"July");
			MonthsName.put(7,"August");
			MonthsName.put(8,"September");
			MonthsName.put(9,"October");
			MonthsName.put(10,"November");
			MonthsName.put(11,"December");
		}

		@Override
		public String toString() {
			return String.format("%d/%s/%d",day,MonthsName.get(month),year);
		}
		
		 public DateF(DateF curDate){
		        this(curDate.day, curDate.month ,curDate.year);
		    }
}
