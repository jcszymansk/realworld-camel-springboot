package com.github.jacekszymanski.camel.xjpa;

import lombok.Getter;
import lombok.Setter;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Message;
import org.apache.camel.component.jpa.JpaConstants;
import org.apache.camel.component.jpa.JpaProducer;
import org.apache.camel.component.jpa.TransactionStrategy;
import org.apache.camel.language.simple.SimpleLanguage;
import org.apache.camel.support.ExchangeHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class XJpaProducer extends JpaProducer {

  private static final Logger LOG = LoggerFactory.getLogger(XJpaProducer.class);

  @Getter @Setter
  private String outputTarget;

  @Getter @Setter
  private XJpaOutputType outputType;

  private final TransactionStrategy transactionStrategy;

  public XJpaProducer(XJpaEndpoint endpoint, Expression expression) {
    super(endpoint, expression);
    transactionStrategy = endpoint.getTransactionStrategy();
  }

  // This is unfortunately private in the superclass.
  @SuppressWarnings({"unchecked", "deprecation"})
  private void configureParameters(Query query, Exchange exchange) {
    int maxResults = getEndpoint().getMaximumResults();
    if (maxResults > 0) {
      query.setMaxResults(maxResults);
    }
    final int firstResult = ((XJpaEndpoint) getEndpoint()).getFirstResult();
    if (firstResult > 0) {
      query.setFirstResult(firstResult);
    }
    // setup the parameters
    Map<String, ?> params;
    final java.util.Map<String, Object> parameters = getParameters();
    if (parameters != null) {
      params = parameters;
    } else {
      params = exchange.getIn().getHeader(JpaConstants.JPA_PARAMETERS_HEADER, Map.class);
    }
    if (params != null) {
      params.forEach((key, value) -> {
        Object resolvedValue = value;
        if (value instanceof String) {
          resolvedValue = SimpleLanguage.expression((String) value).evaluate(exchange, Object.class);
        }
        query.setParameter(key, resolvedValue);
      });
    }
  }

  @Override
  protected void processQuery(Exchange exchange, EntityManager entityManager) {
    Query innerQuery = getQueryFactory().createQuery(entityManager);
    configureParameters(innerQuery, exchange);

    transactionStrategy.executeInTransaction(() -> {
      if (getEndpoint().isJoinTransaction()) {
        entityManager.joinTransaction();
      }
      final Object answer;

      if (isUseExecuteUpdate()) {
        answer = innerQuery.executeUpdate();
      } else {
        if (outputType == XJpaOutputType.SelectOne) {
          final var ansList = Objects.requireNonNullElse(innerQuery.getResultList(), Collections.emptyList());
          if (ansList.size() == 1) {
            answer = ansList.get(0);
          }
          else {
            throw new NonUniqueResultException("non unique result: " + ansList.toString());
          }
        }
        else {
          answer = innerQuery.getResultList();
        }
      }

      putAnswer(exchange, answer);

      if (getEndpoint().isFlushOnSend()) {
        entityManager.flush();
      }
    });
  }

  @Override
  protected void processFind(Exchange exchange, EntityManager entityManager) {
    final Object key = exchange.getMessage().getBody();

    if (key != null) {
      transactionStrategy.executeInTransaction(() -> {
        if (getEndpoint().isJoinTransaction()) {
          entityManager.joinTransaction();
        }

        final Object answer = entityManager.find(getEndpoint().getEntityType(), key);
        LOG.debug("Find: {} -> {}", key, answer);

        if (getOutputType() == XJpaOutputType.SelectOne && answer == null) {
          throw new NonUniqueResultException(
              String.format("no result for key %s and SelectOne is in force", key));
        }

        putAnswer(exchange, answer);

        if (getEndpoint().isFlushOnSend()) {
          entityManager.flush();
        }
      });
    }
  }

  private void putAnswer(final Exchange exchange, final Object answer) {
    if (StringUtils.isBlank(outputTarget)) {
      getTargetMessage(exchange).setBody(answer);
    }
    else if (outputTarget.startsWith("%")) {
      exchange.setProperty(outputTarget.substring(1), answer);
    }
    else {
      getTargetMessage(exchange).setHeader(outputTarget, answer);
    }
  }

  private static Message getTargetMessage(Exchange exchange) {
    final Message target;
    if (ExchangeHelper.isOutCapable(exchange)) {
      target = exchange.getMessage();
      // preserve headers
      target.getHeaders().putAll(exchange.getIn().getHeaders());
    } else {
      target = exchange.getIn();
    }
    return target;
  }

}
