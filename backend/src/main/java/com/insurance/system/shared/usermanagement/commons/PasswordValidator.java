package com.insurance.system.shared.usermanagement.commons;

import java.util.Objects;
import java.util.regex.Pattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.insurance.system.shared.usermanagement.domain.PasswordPolicy;
import com.insurance.system.shared.usermanagement.repository.PasswordPolicyRepository;

public class PasswordValidator implements ConstraintValidator<Password, String> {
  private static final Logger log = LoggerFactory.getLogger(PasswordValidator.class);
  
  private final PasswordPolicyRepository passwordPolicyRepository;
  
  @Autowired
  public PasswordValidator(PasswordPolicyRepository passwordPolicyRepository) {
    this.passwordPolicyRepository = passwordPolicyRepository;
  }
  
  public boolean isValid(String password, ConstraintValidatorContext context) {
    PasswordPolicy policy = this.passwordPolicyRepository.findAll().get(0);
    Pattern ambiguousChars = Pattern.compile("[^a-z0-9 ]", 2);
    Pattern upperCase = Pattern.compile("[A-Z ]");
    Pattern lowerCase = Pattern.compile("[a-z ]");
    Pattern numbers = Pattern.compile("[0-9 ]");
    if (password.length() < policy.getMinimumLength().intValue())
      return false; 
    if (policy.getAmbiguousChars().booleanValue() && !ambiguousChars.matcher(password).find())
      return false; 
    if (policy.getLowerChars().booleanValue() && !lowerCase.matcher(password).find())
      return false; 
    if (policy.getUpperChars().booleanValue() && !upperCase.matcher(password).find())
      return false; 
    if (policy.getIncludeNumbers().booleanValue() && !numbers.matcher(password).find())
      return false; 
    if (Objects.isNull(password))
      return false; 
    return true;
  }
}
