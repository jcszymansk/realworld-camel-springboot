package com.github.jacekszymanski.camel.xjpa;

import lombok.Getter;
import lombok.Setter;
import org.apache.camel.Producer;
import org.apache.camel.component.jpa.JpaConstants;
import org.apache.camel.component.jpa.JpaEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;

/**
 * XJPA extends the Camel's JPA endpoint with three new options:
 * - outputTarget, to put the result in a header/property instead of the message body
 * - outputType, if SelectOne, it ensures that exactly one result is returned (or an exception is thrown).
 * - firstResult, the index of the first result to return.
 */
@UriEndpoint(scheme = "xjpa", syntax = "xjpa:entityType", title = "XJPA", headersClass = JpaConstants.class)
public class XJpaEndpoint extends JpaEndpoint {

  @Getter @Setter
  @UriParam(name="outputTarget",
      label = "producer",
      description = "Where to put the query result instead of body:\n"
                  + "if it starts with . (a dot), it is interpreted as an exchange property, "
                  + "otherwise as a message header."
  )
  private String outputTarget;

  @Getter @Setter
  @UriParam(name="outputType",
      label = "producer",
      description = "What type of output to produce on queries/finds:\n"
                  + "Default: the default, the answer is a list of results (or a single result/null in finds)\n"
                  + "SelectOne: exactly one result, if the query returns no results or "
                  + "more than one, an exception is thrown."
  )
  private XJpaOutputType outputType;

  @Getter @Setter
  @UriParam(name="firstResult",
      label = "producer",
      description = "The index of the first result to return."
  )
  private int firstResult;

  public XJpaEndpoint(final String uri, final XJpaComponent component) {
    super(uri, component);
  }

  @Override
  public Producer createProducer() throws Exception {
    validate();
    XJpaProducer producer = new XJpaProducer(this, getProducerExpression());
    producer.setQuery(getQuery());
    producer.setNamedQuery(getNamedQuery());
    producer.setNativeQuery(getNativeQuery());
    producer.setParameters(getParameters());
    producer.setResultClass(getResultClass());
    producer.setFindEntity(isFindEntity());
    producer.setUseExecuteUpdate(getUseExecuteUpdate());
    producer.setOutputTarget(getOutputTarget());
    producer.setOutputType(getOutputType());
    return producer;
  }
}
