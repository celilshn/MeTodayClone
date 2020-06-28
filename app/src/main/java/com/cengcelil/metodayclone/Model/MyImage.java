package com.cengcelil.metodayclone.Model;

import java.io.File;
import java.util.Calendar;

public class MyImage {
    private Calendar calendar;
    private File file;
    private String dbId;
    private int year,month,day;
    private String date,time;

    public Calendar getCalendar() {
        return calendar;
    }

    public File getFile() {
        return file;
    }

    public String getDbId() {
        return dbId;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public MyImage(Calendar calendar, File file, String dbId) {
        this.calendar = calendar;
        this.file = file;
        this.dbId = dbId;
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH)+1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.date = day+" "+month+" "+year;
        this.time = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE);

    }
}
