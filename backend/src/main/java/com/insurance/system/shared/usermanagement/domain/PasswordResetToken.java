package com.insurance.system.shared.usermanagement.domain;

import java.util.Calendar;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PasswordResetToken {
  private static final int EXPIRATION = 1440;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String token;
  
  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  
  private Date expiryDate;
  
  public User getUser() {
    return this.user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getToken() {
    return this.token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
  
  public Date getExpiryDate() {
    return this.expiryDate;
  }
  
  public void setExpiryDate(Date expiryDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(12, 15);
    this.expiryDate = calendar.getTime();
  }
}
