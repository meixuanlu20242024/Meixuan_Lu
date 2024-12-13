package com.insurance.system.shared.usermanagement.domain;

import java.util.Calendar;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "verification_token")
public class VerificationToken {
  private static final int EXPIRATION = 1440;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Column(name = "token")
  private String token;
  
  @Cascade({CascadeType.DETACH, CascadeType.SAVE_UPDATE})
  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  
  @Column(name = "expiry_date")
  private Date expiryDate;
  
  public VerificationToken() {}
  
  public VerificationToken(String token) {
    this.token = token;
  }
  
  public VerificationToken(String token, User user) {}
  
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getToken() {
    return this.token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
  
  public User getUser() {
    return this.user;
  }
  
  public void setUser(User user) {
    this.user = user;
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
