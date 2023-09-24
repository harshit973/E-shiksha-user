package com.example.user.DbConfig;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.user")
public class UserDbConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource-user")
  public DataSource userDataSource() {
    return DataSourceBuilder.create().build();
  }

}