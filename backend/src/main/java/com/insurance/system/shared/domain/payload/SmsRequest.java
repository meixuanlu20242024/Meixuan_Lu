package com.insurance.system.shared.domain.payload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SmsRequest {
    private String apikey;
    private String mobiles;
    private String sms;
    private String senderid;
    //private String schedule = "no" ;
    //private String sendingTime;
//    private String unicode;
//    private String clientSmsIds;
//    private String messageType;
}