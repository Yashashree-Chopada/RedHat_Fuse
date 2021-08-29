package com.camelHello.example1;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class Helloworld
{
    public static void main(String[] args) throws Exception
    {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new HelloWorldRoute());
        context.start();
    }
}
