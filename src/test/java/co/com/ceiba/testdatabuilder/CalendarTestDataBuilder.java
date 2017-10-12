package co.com.ceiba.testdatabuilder;

import java.util.Calendar;

public class CalendarTestDataBuilder {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	
	public CalendarTestDataBuilder(){
		this.year = 1900;
		this.month = 0;
		this.day = 1;
		this.hour = 0;
		this.minute = 0;
	}
	
	public CalendarTestDataBuilder withYear(int year){
		this.year = year;
		return this;
	}
	
	public CalendarTestDataBuilder withMonth(int month){
		this.month = month;
		return this;
	}
	
	public CalendarTestDataBuilder withDay(int day){
		this.day = day;
		return this;
	}
	
	public CalendarTestDataBuilder withHour(int hour){
		this.hour = hour;
		return this;
	}
	
	public CalendarTestDataBuilder withMinute(int minute){
		this.minute = minute;
		return this;
	}
	
	public Calendar Build(){
		Calendar fecha = Calendar.getInstance();
		fecha.set(this.year, this.month, this.day, this.hour, this.minute);
		return fecha;
	}
}
