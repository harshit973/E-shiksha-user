package com.example.user.AuthConfig;

import com.example.user.Entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenHelper {
  @Bean
  TokenHelper getTokenHelper(){
    return new TokenHelper();
  }
  public String generateJwtToken(final User user){
    return "";
  }

}
