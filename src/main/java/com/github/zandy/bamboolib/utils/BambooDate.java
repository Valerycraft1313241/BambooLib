package com.github.zandy.bamboolib.utils;

import java.util.Calendar;

public class BambooDate {
   final int day;
   final int month;
   final int year;

   public BambooDate(long timestamp) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(timestamp);
      this.day = calendar.get(Calendar.DATE);
      this.month = calendar.get(Calendar.MONTH) + 1;
      this.year = calendar.get(Calendar.YEAR);
   }

   public int getDay() {
      return this.day;
   }

   public int getMonth() {
      return this.month;
   }

   public int getYear() {
      return this.year;
   }
}
