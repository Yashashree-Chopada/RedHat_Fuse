package com.mycompany.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
	

		from("file:work/filter/input?noop=true")
			.log("Receiving -> ${file:name}")
			.filter(body().startsWith("Hello"))
			.to("file:work/filter/output");
	}

}
