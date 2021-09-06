package com.mycompany.camel;


import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class CamelRoute extends RouteBuilder {

		
		  private static SampleAggregator aggregationStrategy = new SampleAggregator();

			public void configure() throws Exception {

		        //from("timer:enrich?period=5s")
			//	from("timer://file:/work/enrichA?fixedRate=true&period=30s")
				from("file:work/enrich/in")
		            .log(">> Before enrichment. File content is : ${body}")
		            .enrich("direct:resource", aggregationStrategy)
		         
		            .log(">> After enrichment. File content : ${body}")   .to("file:work/enrich/out");

				/*
				 * from("direct:resource") .setExchangePattern(ExchangePattern.InOut)
				 * .transform().constant("Enriching text");
				 */
				
				from("direct:resource")
				.process(new Processor() {
				    public void process(Exchange exchange) {
				        Message in = exchange.getIn();
				        in.setBody(in.getBody(String.class) + " World!");
				    }
				});
			}
	}

/*
 [ychopada@ychopada Camel_Enrich]$ mvn exec:java
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< com.mycompany:camel-java-cbr >--------------------
[INFO] Building Fuse CBR Quickstart - Java 1.0.0-SNAPSHOT
[INFO] -------------------------------[ bundle ]-------------------------------
[INFO] 
[INFO] --- exec-maven-plugin:1.6.0:java (default-cli) @ camel-java-cbr ---
[ycompany.camel.Launcher.main()] DefaultCamelContext            INFO  Apache Camel 2.23.2.fuse-790054-redhat-00001 (CamelContext: camel-1) is starting
[ycompany.camel.Launcher.main()] ManagedManagementStrategy      INFO  JMX is enabled
[ycompany.camel.Launcher.main()] DefaultTypeConverter           INFO  Type converters loaded (core: 195, classpath: 0)
[ycompany.camel.Launcher.main()] DefaultCamelContext            INFO  StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
[ycompany.camel.Launcher.main()] DefaultCamelContext            INFO  Route: route1 started and consuming from: file://work/enrich/in
[ycompany.camel.Launcher.main()] DefaultCamelContext            INFO  Route: route2 started and consuming from: direct://resource
[ycompany.camel.Launcher.main()] DefaultCamelContext            INFO  Total 2 routes, of which 2 are started
[ycompany.camel.Launcher.main()] DefaultCamelContext            INFO  Apache Camel 2.23.2.fuse-790054-redhat-00001 (CamelContext: camel-1) started in 0.293 seconds
[ead #2 - file://work/enrich/in] route1                         INFO  >> Before enrichment. File content is : Hello

[ead #2 - file://work/enrich/in] route1                         INFO  >> After enrichment. File content : GenericFile[enrichTest.txt]:Hello
 World!

 */
