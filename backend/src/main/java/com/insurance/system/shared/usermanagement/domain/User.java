package com.insurance.system.shared.usermanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private static final Logger log = LoggerFactory.getLogger(User.class);
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false)
  private String firstName;
  
  @Column(nullable = false)
  private String lastName;
  private String username;

  @JsonIgnore
  @Column(nullable = false)
  private Integer attempts;
  
  @Email
  @Column(nullable = false)
  private String email;
  
  @Column(unique = true)
  @Size(max = 13)
  private String mobile;


  @Column
  private String nationalId;
  
  private boolean enabled;
  
  @Enumerated(EnumType.STRING)
  private UserType usertype;
  
  @JsonIgnore
  private String password;
  
  @Enumerated(EnumType.STRING)
  private AuthProvider provider;
  
  private String providerId;
  
  @Column(name = "password_changed_time")
  private Date passwordChangedTime;
  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_privileges", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "privilege_id", referencedColumnName = "id")})
  private Set<Privilege> privileges;
  
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
  private Set<Role> roles;
  
  @OneToOne(cascade = {CascadeType.PERSIST})
  @JoinColumn(name = "company_id")
  private Company company;


  
  public boolean isPasswordExpired(Integer expirationdays) {
    if (this.passwordChangedTime == null)
      return false; 
    Calendar currentdate = Calendar.getInstance();
    currentdate.setTime(new Date());
    Date lastChangedTime = this.passwordChangedTime;
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(lastChangedTime);
    calendar.add(5, expirationdays.intValue());
    if (currentdate.compareTo(calendar) > 0)
      return true; 
    return false;
  }

}
