package com.github.jacekszymanski.realcamel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CamelConfiguration {
  @Bean
  CamelContextConfiguration contextConfiguration() {
    return new CamelContextConfiguration() {
      @Override
      public void beforeApplicationStart(CamelContext context) {
        context.setGlobalOptions(Map.of(
            Exchange.LOG_EIP_NAME, CamelConfiguration.class.getPackage().getName()
        ));
      }

      @Override
      public void afterApplicationStart(CamelContext camelContext) {

      }
    };
  }

}
