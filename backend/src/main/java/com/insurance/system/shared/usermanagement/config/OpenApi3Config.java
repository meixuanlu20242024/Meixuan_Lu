package com.insurance.system.shared.usermanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApi3Config {
  @Bean
  public OpenAPI openApiConfig() {
    return (new OpenAPI()).info(opeApiInfo());
  }
  
  public Info opeApiInfo() {
    Info info = new Info();
    Contact contact = new Contact();
    contact.setName("Promise Makufa");
    contact.setEmail("kMakufa@outlook.com");
    info.title("User management portal")
      .description("User management")
      .contact(contact)
      .version("v1.1.0");
    return info;
  }
}
