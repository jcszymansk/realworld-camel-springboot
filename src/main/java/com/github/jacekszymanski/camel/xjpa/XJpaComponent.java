package com.github.jacekszymanski.camel.xjpa;

import org.apache.camel.Endpoint;
import org.apache.camel.component.jpa.JpaComponent;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.util.PropertiesHelper;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@Component("xjpa")
public class XJpaComponent extends JpaComponent {

  @Override
  protected Endpoint createEndpoint(String uri, String path, Map<String, Object> options) throws Exception {
    XJpaEndpoint endpoint = new XJpaEndpoint(uri, this);
    endpoint.setJoinTransaction(isJoinTransaction());
    endpoint.setSharedEntityManager(isSharedEntityManager());

    Map<String, Object> params = PropertiesHelper.extractProperties(options, "parameters.", true);
    if (!params.isEmpty()) {
      endpoint.setParameters(params);
    }

    // lets interpret the next string as an alias or class
    if (ObjectHelper.isNotEmpty(path)) {
      Class<?> type = getAliases().get(path);
      if (type == null) {
        // provide the class loader of this component to work in OSGi environments as camel-jpa must be able
        // to resolve the entity classes
        type = getCamelContext().getClassResolver().resolveClass(path, JpaComponent.class.getClassLoader());
      }

      if (type != null) {
        endpoint.setEntityType(type);
      }
    }
    setProperties(endpoint, options);
    return endpoint;
  }

}
