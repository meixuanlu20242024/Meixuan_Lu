package com.insurance.system.shared.utils;

public class Constants {



//    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public static final String SLASH = "/";
  public static final String FOLDER_PATH_REPORT_DYNAMIC = "/report/allassetrisk2/";
  public static final String EXPORT_TYPE_PARAMETER_IS_MISSING = "Parameter: exportType is missing";
  public static final String EXTENSION_JRXML = ".jrxml";
  //  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static final String passwordmessage = "Welcome to PKSYSTEMS Portal:\nYour password is \n";

  public static final String otpmessage1 = "Welcome to PKSYSTEMS Portal:\nYour OTP code is \n";
  
  public static final String passwordmessage2 = " . Please use link before ";
  
  public static final String otpmessage2 = " . Please use code before ";
  
  public static final String poweredbymessage = " .\r\n Powered by wifpeak";
  
  public static final String linkmessage1 = "Welcome to PKSYSTEMS Portal:\n Click the link below to activate your account ";
  
  public static final String linkmessage2 = " . Links expires in 5 minutes ";
  
  public static final String linkmessage3 = " .\r\n Powered by swiftpeak";
  
  public static final String Originator = "PKSYSTEMS";
  
  public static final String HazelCast_Instance = "payments-portal";
  
  public static final Long JWT_TOKEN_VALIDITY = Long.valueOf(600000L);
  
  public static final String PENDING = "Pending";
  
  public static final String SUCCESS = "Successful";
  
  public static final int EMAIL_TOKEN_VALIDITY = 15;
  
  public static final Integer INITIAL_PASSWORD_EXPIRATION_DAYS = Integer.valueOf(30);
  
  public static final Integer INITIAL_PASSWORD_LENGTH = Integer.valueOf(8);
  
  public static final String CLIENT_VERIFY_ACCOUNT_URL = "http://localhost:4200/#/auth/verify-account/";
  
  public static final String ADMIN_VERIFY_ACCOUNT_URL = "http://localhost:4201/#/auth/verify-account/";
  
  public static final String PROVIDER_VERIFY_ACCOUNT_URL = "http://localhost:4202/#/auth/verify-account/";
  
  public static final String CLIENT_RESET_PASSWORD_URL = "http://localhost:4200/#/auth/reset-password/";
  
  public static final String ADMIN_RESET_PASSWORD_URL = "http://localhost:4201/#/auth/reset-password/";
  
  public static final String PROVIDER_RESET_PASSWORD_URL = "http://localhost:4202/#/auth/reset-password/";
}
