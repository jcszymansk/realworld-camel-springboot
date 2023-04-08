package com.github.jacekszymanski.realcamel;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class ListAggregationStrategy implements AggregationStrategy {

  @Override
  public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
    if (oldExchange == null) {
      final List<Object> nlist = new ArrayList<>();
      nlist.add(newExchange.getIn().getBody());
      newExchange.getIn().setBody(nlist);

      return newExchange;
    }

    @SuppressWarnings("unchecked")
    final List<Object> olist = oldExchange.getIn().getBody(List.class);
    olist.add(newExchange.getIn().getBody());

    return oldExchange;
  }

}
