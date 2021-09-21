package com.mycompany.camel;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class ExceptionOnMyprocessor1 extends Exception implements AggregationStrategy
{
	  public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
	        System.out.println("Exception Handled!..");
	        Object oldBody = oldExchange.getIn().getBody();
	        oldExchange.getIn().setBody(oldBody+"Changed");
	        return oldExchange;
	    }

}
