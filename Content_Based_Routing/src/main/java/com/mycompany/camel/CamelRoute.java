package com.mycompany.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		/* When this route is started, it will automatically create the work/cbr/input directory 
		 * where you can drop the file that need to be processed.

        The <log/> elements are used to add human-friendly business logging statements. They 
        make it easier to see what the route is doing.

        The <choice/> element contains the content based router. The two <when/> clauses use 
        XPath to define the criteria for entering that part of the route. When the country in 
        the XML message is set to UK or US, the file will be moved to a directory for that country. 
        The <otherwise/> element ensures that any file that does not meet the requirements for 
        either of the <when/> elements will be moved to the work/cbr/output/others directory.
        
		 */

		from("file:work/cbr/input")
			.log("Receiving data ${file:name}")
			.choice()
				.when().xpath("/Orders/Order/Name/text()='Veg'")
					.log("Sending  ${file:name} to the Veg")
					.to("file:work/cbr/output/veg")
				.when().xpath("/Orders/Order/Name/text()='NonVeg'")
					.log("Sending  ${file:name} to the NonVeg")
					.to("file:work/cbr/output/nonveg")
				.otherwise()
					.log("Sending order ${file:name} to another")
					.to("file:work/cbr/output/others")
			.log("Done processing ${file:name}");
	}

}
