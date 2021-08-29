package com.mycompany.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		

		from("file:work/wiretap/input").split().tokenize("\n").to("direct:test1");
		
		from("direct:test1")
		.log("Receiving order ${file:name}")
		.wireTap("file:work/wiretap/tapped")
		.to("direct:test2");
		
		from ("direct:test2")
		.process(new Processor() {
			public void process(Exchange arg0) throws Exception
			{
				System.out.println(arg0.getIn().getBody().toString());
			}
		})
		.log("Done processing ${file:name}");
	}

}
