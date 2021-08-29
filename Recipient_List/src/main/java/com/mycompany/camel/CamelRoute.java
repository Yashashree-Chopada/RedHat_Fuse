package com.mycompany.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		

		from("file:work/Rlist/input").split().tokenize("\n").to("direct:test");
		        from("direct:test")
				.log("Receiving data from -> ${file:name}")
		        .process(new Processor() {
		            public void process(Exchange exchange) throws Exception {
		               String recipient = exchange.getIn().getBody().toString();
		               String recipientQueue="file:work/Rlist/output"+recipient;
		               exchange.getIn().setHeader("filter", recipientQueue);
		      }

		
		      }).recipientList(header("filter"))
		        .log("Processing done -> ${file:name}");

		        
	}

}
