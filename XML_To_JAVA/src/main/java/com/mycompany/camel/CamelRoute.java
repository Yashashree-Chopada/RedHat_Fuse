package com.mycompany.camel;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;


import com.mycompany.camel.xml.Student;



public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception 
	{
		JAXBContext jc = JAXBContext.newInstance(Student.class);
		JaxbDataFormat jd = new JaxbDataFormat(jc);
		
			from("file:xml").routeId("processXmlFile") .unmarshal(jd)
		  .log("processing Student name: ${body.getName}")
		  .log("Rollnumber ${body.getRollnumber}") .log("Div: ${body.getDiv}")
		  
		  .split().simple("${body.subjects}")
		  
		  .log("Subject List: ${body.getName}")
			.end();
    		
    	
    	
    	
		
		
		
	}

}

/*  XML To JAVA
	Output
	
	[                          main] DefaultCamelContext            INFO  Apache Camel 2.23.2.fuse-790054-redhat-00001 (CamelContext: camel-1) is starting
[                          main] ManagedManagementStrategy      INFO  JMX is enabled
[                          main] DefaultTypeConverter           INFO  Type converters loaded (core: 195, classpath: 0)
[                          main] DefaultCamelContext            INFO  StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
[                          main] DefaultCamelContext            INFO  Route: processXmlFile started and consuming from: file://xml
[                          main] DefaultCamelContext            INFO  Total 1 routes, of which 1 are started
[                          main] DefaultCamelContext            INFO  Apache Camel 2.23.2.fuse-790054-redhat-00001 (CamelContext: camel-1) started in 0.440 seconds
[amel-1) thread #2 - file://xml] StaxConverter                  INFO  Created XMLInputFactory: com.sun.xml.internal.stream.XMLInputFactoryImpl@74f0740. DOMSource/DOMResult may have issues with com.sun.xml.internal.stream.XMLInputFactoryImpl@74f0740. We suggest using Woodstox.
[amel-1) thread #2 - file://xml] processXmlFile                 INFO  processing Student name: Yashashree
[amel-1) thread #2 - file://xml] processXmlFile                 INFO  Rollnumber 1
[amel-1) thread #2 - file://xml] processXmlFile                 INFO  Div: A
[amel-1) thread #2 - file://xml] processXmlFile                 INFO  Subject List: Math
[amel-1) thread #2 - file://xml] processXmlFile                 INFO  Subject List: Science
[amel-1) thread #2 - file://xml] processXmlFile                 INFO  Subject List: English


*/
