package com.insurance.system.shared.usermanagement.payload;

public class SmsGatewayMessage {
  private String originator;
  
  private String messageText;
  
  private String destination;
  
  private String messageReference;
  
  public void setOriginator(String originator) {
    this.originator = originator;
  }
  
  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }
  
  public void setDestination(String destination) {
    this.destination = destination;
  }
  
  public void setMessageReference(String messageReference) {
    this.messageReference = messageReference;
  }
  

  
  public String toString() {
    return "SmsGatewayMessage(originator=" + getOriginator() + ", messageText=" + getMessageText() + ", destination=" + getDestination() + ", messageReference=" + getMessageReference() + ")";
  }
  
  public SmsGatewayMessage() {}
  
  public SmsGatewayMessage(String originator, String messageText, String destination, String messageReference) {
    this.originator = originator;
    this.messageText = messageText;
    this.destination = destination;
    this.messageReference = messageReference;
  }
  
  public String getOriginator() {
    return this.originator;
  }
  
  public String getMessageText() {
    return this.messageText;
  }
  
  public String getDestination() {
    return this.destination;
  }
  
  public String getMessageReference() {
    return this.messageReference;
  }
}
