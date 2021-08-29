package com.mycompany.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		

		from("file:work/multicast/input")
			.log("Receiving data from -> ${file:name}")
			.multicast()
			.to("file:work/multicast/o1","file:work/multicast/o2","file:work/multicast/o3");
		 
		
	}

}
