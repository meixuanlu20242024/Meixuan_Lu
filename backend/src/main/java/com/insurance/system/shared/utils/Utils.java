package com.insurance.system.shared.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.system.shared.domain.payload.RenewalsToDates;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.*;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import jakarta.persistence.criteria.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
@Slf4j
public class Utils {
  private static ObjectMapper objectMapper = new ObjectMapper();

  public static Map<String, Object> toJsonMap(Object object) {
    return (Map<String, Object>)objectMapper.convertValue(object, Map.class);
  }

  public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
    XMLGregorianCalendar date2 = null;
    String FORMATER = "yyyy-MM-dd'T'HH:mm:ss";
    DateFormat format = new SimpleDateFormat(FORMATER);
    try {
      date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return date2;
  }
  
  public static Long dateSubtraction(Timestamp transactionTime) {
    XMLGregorianCalendar currentTime = toXMLGregorianCalendar(new Date());
    XMLGregorianCalendar result = null;
    try {
      result = DatatypeFactory.newInstance().newXMLGregorianCalendar(String.valueOf(transactionTime));
    } catch (DatatypeConfigurationException e) {
      e.printStackTrace();
    } 
    Date date = currentTime.toGregorianCalendar().getTime();
    Date date1 = result.toGregorianCalendar().getTime();
    long diff = date.getTime() - date1.getTime();
    long diffMinutes = diff / 60000L;
    return Long.valueOf(diffMinutes);
  }
  
  public static String dFormat(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.add(5, 1);
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    String formatted = format1.format(cal.getTime());
    return formatted;
  }


    public static String formatDate(Date date) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return sdf.format(date);
    }

  
  public static Date stringToDate(String StringDate) throws ParseException {
    if (StringDate != null) {
      DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
      Date date = formatter2.parse(StringDate);
      return date;
    } 
    return null;
  }
  
  public static String formatString(String str) {
    String s = "";
    try {
      if (str != null)
        s = str.trim(); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return s;
  }
  
  public static String replaceChars(String str, char toReplace, char replaceWith) {
    return str.replace(toReplace, replaceWith);
  }
  
  public static String formatNumber(String s, int len) {
    if (s == null)
      s = ""; 
    if (s.length() > len) {
      int l = s.length();
      s = s.substring(l - len);
    } 
    while (len > 0 && s.length() < len)
      s = "0" + s; 
    return s;
  }
  
  public static String maskNumber(String number, String mask) {
    int index = 0;
    StringBuilder masked = new StringBuilder();
    try {
      for (int i = 0; i < mask.length(); i++) {
        char c = mask.charAt(i);
        if (c == '#') {
          masked.append(number.charAt(index));
          index++;
        } else if (c == 'x') {
          masked.append(c);
          index++;
        } else {
          masked.append(c);
        } 
      } 
      return masked.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return "xxxxxxxxxxxxxxxxxxx";
    } 
  }

  public static RenewalsToDates renewalsToDates() {



    Date today = new Date();
    Date sevenDays = new Date(today.getTime() + 604800000L);
    Date thirtyDaysFromToday = new Date(today.getTime() + 2592000000L);
    Date sixtyDaysFromToday = new Date(today.getTime() + 5184000000L);
    Date ninetyDaysFromToday = new Date(today.getTime() + 7776000000L);

    return new RenewalsToDates(today, sevenDays, thirtyDaysFromToday, sixtyDaysFromToday, ninetyDaysFromToday);
  }
  public static Month getMonthFromNumericValue(int numericValue) {
    switch (numericValue) {
      case 1:
        return Month.JANUARY;
      case 2:
        return Month.FEBRUARY;
      case 3:
        return Month.MARCH;
      case 4:
        return Month.APRIL;
      case 5:
        return Month.MAY;
      case 6:
        return Month.JUNE;
      case 7:
        return Month.JULY;
      case 8:
        return Month.AUGUST;
      case 9:
        return Month.SEPTEMBER;
      case 10:
        return Month.OCTOBER;
      case 11:
        return Month.NOVEMBER;
      case 12:
        return Month.DECEMBER;
      default:
        throw new IllegalArgumentException("Invalid month value: " + numericValue);
    }
  }

  public static Predicate createQuarterFilter(
          CriteriaBuilder criteriaBuilder,
          Root<?> root,
          Integer year,
          Integer quarter , String dateField,
          Join policyJoin) {


    if (year == null || quarter == null || quarter < 1 || quarter > 5) {
      throw new IllegalArgumentException("Invalid year or quarter values");
    }

    Predicate yearPredicate = criteriaBuilder.equal(
            criteriaBuilder.function("year", Integer.class, (policyJoin == null ? root.get(dateField) : policyJoin.get(dateField))),
            year);

    Predicate quarterPredicate;

    switch (quarter) {
      case 2:
        quarterPredicate = createQuarterDateRangePredicate(criteriaBuilder, root, year, Month.JANUARY, Month.MARCH , dateField , policyJoin);
        break;
      case 3:
        quarterPredicate = createQuarterDateRangePredicate(criteriaBuilder, root, year, Month.APRIL, Month.JUNE, dateField , policyJoin);
        break;
      case 4:
        quarterPredicate = createQuarterDateRangePredicate(criteriaBuilder, root, year, Month.JULY, Month.SEPTEMBER, dateField , policyJoin);
        break;
      case 5:
        quarterPredicate = createQuarterDateRangePredicate(criteriaBuilder, root, year, Month.OCTOBER, Month.DECEMBER, dateField , policyJoin);
        break;
      default:
        throw new IllegalArgumentException("Invalid quarter value");
    }

    return criteriaBuilder.and(yearPredicate, quarterPredicate);
  }

  public static Predicate createQuarterDateRangePredicate(
          CriteriaBuilder criteriaBuilder,
          Root<?> root,
          Integer year,
          Month startMonth,
          Month endMonth,
          String dateField,
          Join<?,?> policyJoin) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, startMonth.getValue() - 1);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    Date startDate = new Date(calendar.getTimeInMillis());

    calendar.set(Calendar.MONTH, endMonth.getValue() - 1);
    calendar.set(Calendar.DAY_OF_MONTH, endMonth.maxLength());
    Date endDate = new Date(calendar.getTimeInMillis());

    return criteriaBuilder.between((policyJoin == null ? root.get(dateField) : policyJoin.get(dateField)), startDate, endDate);
  }

  public static String CurrencyFormatter(Double amount) {
    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);

    // Format the currency with two decimal places
    if (formatter instanceof DecimalFormat) {
      ((DecimalFormat) formatter).applyPattern("#,##0.00");
    }

      return formatter.format(amount);
  }

  public static boolean checkEmailAddress(String emailAddress) {
    return Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
            .matcher(emailAddress)
            .matches();
  }

}
