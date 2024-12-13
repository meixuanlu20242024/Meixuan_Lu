package com.insurance.system.shared.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RefGen {
  private static Map<String, Integer> counters = new HashMap<>();
  
  private static final int MAX_COUNT = 999;
  
  private static final int MAX_STAN = 999999;
  
  private static final int ORIGINATOR_COUNT = 50;
  
  private static Date anchorDate;
  
  private static Date getAnchorDate() {
    if (anchorDate == null)
      try {
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        anchorDate = df.parse("01012019");
      } catch (ParseException parseException) {} 
    return anchorDate;
  }
  
  public static void main(String[] args) {
    try {
      int MAX_VAL = 5;
      Map<String, String> test = Collections.synchronizedMap((Map<String, String>)new Object());
      test.putIfAbsent("1", "test1");
      test.putIfAbsent("1", "test0");
      test.put("2", "test2");
      test.put("3", "test3");
      test.put("4", "test4");
      for (int i = 0; i < 10; i++) {
        if (test.containsKey(String.valueOf(i))) {
          System.out.println("1. irimo  >>>>" + i + ">>>>" + test);
        } else {
          System.out.println("2. haimo  >>>>" + i + ">>>>" + test);
        } 
      } 
      System.out.println("1. Before 5>>>>>>>>" + test);
      test.put("5", "test5");
      System.out.println("2. Before 5>>>>>>>>" + test);
      test.put("6", "test6");
      test.put("7", "test7");
      System.out.println("3. Before 5>>>>>>>>" + test);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  

  
  private static long calculateDays() {
    Date now = new Date();
    return TimeUnit.MILLISECONDS.toDays(now.getTime() - getAnchorDate().getTime());
  }
  
  private static long calculateSeconds() {
    Calendar c = Calendar.getInstance();
    Date now = c.getTime();
    c.set(11, 0);
    c.set(12, 0);
    c.set(13, 0);
    Date old = c.getTime();
    return TimeUnit.MILLISECONDS.toSeconds(now.getTime() - old.getTime());
  }
  
  private static String pad(String text, String padChar, int requiredLength) {
    StringBuffer sb = new StringBuffer(text);
    while (sb.length() < requiredLength)
      sb.insert(0, padChar); 
    return sb.toString();
  }
  
  private static String Rpad(String text, String padChar, int requiredLength) {
    StringBuffer sb = new StringBuffer(text);
    while (sb.length() < requiredLength)
      sb.append(padChar); 
    return sb.toString();
  }
  

}
