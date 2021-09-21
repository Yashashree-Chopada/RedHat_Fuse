package com.mycompany.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor1 implements Processor {

    public void process(Exchange exchange) throws Exception {
        System.out.println("----------------- Exception Thrown from MyProcessor 1-------------");
        throw new ExceptionOnMyprocessor1();
    }

}
