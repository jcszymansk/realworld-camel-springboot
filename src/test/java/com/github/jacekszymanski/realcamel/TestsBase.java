package com.github.jacekszymanski.realcamel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class TestsBase {

  @Autowired
  protected CamelContext camelContext;

  @Autowired
  protected ProducerTemplate producerTemplate;


}
