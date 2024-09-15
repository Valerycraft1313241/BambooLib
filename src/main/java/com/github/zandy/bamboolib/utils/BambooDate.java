package com.github.zandy.bamboolib.utils;

import java.util.Calendar;

public class BambooDate {
   final int day;
   final int month;
   final int year;

   public BambooDate(long var1) {
      Calendar var3 = Calendar.getInstance();
      var3.setTimeInMillis(var1);
      this.day = var3.get(Calendar.DATE);
      this.month = var3.get(Calendar.MONTH) + 1;
      this.year = var3.get(Calendar.YEAR);
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
