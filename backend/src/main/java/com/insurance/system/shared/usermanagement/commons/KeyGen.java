package com.insurance.system.shared.usermanagement.commons;

import java.sql.Date;
import java.text.SimpleDateFormat;
import com.insurance.system.shared.utils.Utils;

public class KeyGen {
  private static final long LIMIT = 1000000000000L;
  
  private static long last = 0L;
  
  private static Date date;
  
  public static String generateKey() {
    long id = System.currentTimeMillis() % 1000000000000L;
    if (id <= last)
      id = (last + 1L) % 1000000000000L; 
    last = id;
    return "LD-" + String.valueOf(last);
  }
  
  public static String dateFormatter() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date(System.currentTimeMillis());
    System.out.println(formatter.format(date));
    return formatter.format(date);
  }
  
  public static void main(String[] args) {
    String cardNumber = "5439480151300053860";
    System.out.println("Card number ####### " + cardNumber);
    System.out.println("Card number ####### " + Utils.maskNumber(cardNumber, "####xxxxxxxxxxx####"));
  }
}
