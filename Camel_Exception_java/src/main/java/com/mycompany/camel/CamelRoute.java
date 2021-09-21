package com.mycompany.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		onException(CamelCustomException.class).process(new Processor() {
			@Override
            public void process(Exchange exchange) throws Exception {
                System.out.println("handling ex");
            }

			
        }).log("Received body ").handled(true);

		from("file:work/cbr/input")
			.log("Receiving data ${file:name}")
			.process(new MyProcessor())
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
		
		
		//2nd Route
		from("file:work/cbr/Send?noop=true")
		.onException(ExceptionOnMyprocessor1.class)
			.log("Received body ")
			.handled(true)
		.end()
		.to("file:work/cbr/Received");
	

}
	}

/*
 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
	at java.base/java.lang.Thread.run(Thread.java:831)
[ead #2 - file://work/cbr/input] GenericFileOnCompletion        WARN  Rollback file strategy: org.apache.camel.component.file.strategy.GenericFileRenameProcessStrategy@7dc1b706 for file: GenericFile[order4.xml]
[ead #2 - file://work/cbr/input] route1                         INFO  Receiving data order5.xml
Exception Thrown
[ead #2 - file://work/cbr/input] DefaultErrorHandler            ERROR Failed delivery for (MessageId: ID-ychopada-pnq-csb-1632199331624-0-40 on ExchangeId: ID-ychopada-pnq-csb-1632199331624-0-39). Exhausted after delivery attempt: 1 caught: com.mycompany.camel.CamelCustomException

Message History
---------------------------------------------------------------------------------------------------------------------------------------
RouteId              ProcessorId          Processor                                                                        Elapsed (ms)
[route1            ] [route1            ] [file://work/cbr/input                                                         ] [         2]
[route1            ] [log1              ] [log                                                                           ] [         0]
[route1            ] [process1          ] [Processor@0x6e493c24                                                          ] [         0]

Stacktrace
---------------------------------------------------------------------------------------------------------------------------------------
com.mycompany.camel.CamelCustomException
	at com.mycompany.camel.MyProcessor.process(MyProcessor.java:11)
	at org.apache.camel.processor.DelegateSyncProcessor.process(DelegateSyncProcessor.java:63)
	at org.apache.camel.processor.RedeliveryErrorHandler.process(RedeliveryErrorHandler.java:548)
	at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:201)
	at org.apache.camel.processor.Pipeline.process(Pipeline.java:138)
	at org.apache.camel.processor.Pipeline.process(Pipeline.java:101)
	at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:201)
	at org.apache.camel.component.file.GenericFileConsumer.processExchange(GenericFileConsumer.java:454)
	at org.apache.camel.component.file.GenericFileConsumer.processBatch(GenericFileConsumer.java:223)
	at org.apache.camel.component.file.GenericFileConsumer.poll(GenericFileConsumer.java:187)
	at org.apache.camel.impl.ScheduledPollConsumer.doRun(ScheduledPollConsumer.java:174)
	at org.apache.camel.impl.ScheduledPollConsumer.run(ScheduledPollConsumer.java:101)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.runAndReset(FutureTask.java:305)
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:305)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
	at java.base/java.lang.Thread.run(Thread.java:831)
[ead #2 - file://work/cbr/input] GenericFileOnCompletion        WARN  Rollback file strategy: org.apache.camel.component.file.strategy.GenericFileRenameProcessStrategy@7dc1b706 for file: GenericFile[order5.xml]
[ead #2 - file://work/cbr/input] route1                         INFO  Receiving data order1.xml
Exception Thrown
[ead #2 - file://work/cbr/input] DefaultErrorHandler            ERROR Failed delivery for (MessageId: ID-ychopada-pnq-csb-1632199331624-0-42 on ExchangeId: ID-ychopada-pnq-csb-1632199331624-0-41). Exhausted after delivery attempt: 1 caught: com.mycompany.camel.CamelCustomException

Message History
---------------------------------------------------------------------------------------------------------------------------------------
RouteId              ProcessorId          Processor                                                                        Elapsed (ms)
[route1            ] [route1            ] [file://work/cbr/input                                                         ] [         0]
[route1            ] [log1              ] [log                                                                           ] [         0]
[route1            ] [process1          ] [Processor@0x6e493c24                                                          ] [         0]

Stacktrace
---------------------------------------------------------------------------------------------------------------------------------------
 
 using ONException
 
 [                          main] DefaultCamelContext            INFO  Apache Camel 2.23.2.fuse-780036-redhat-00001 (CamelContext: camel-1) is starting
[                          main] ManagedManagementStrategy      INFO  JMX is enabled
[                          main] DefaultTypeConverter           INFO  Type converters loaded (core: 195, classpath: 0)
[                          main] DefaultCamelContext            INFO  StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
[                          main] XPathBuilder                   INFO  Created default XPathFactory com.sun.org.apache.xpath.internal.jaxp.XPathFactoryImpl@3c9bfddc
[                          main] DefaultCamelContext            INFO  Route: route1 started and consuming from: file://work/cbr/input
[                          main] DefaultCamelContext            INFO  Total 1 routes, of which 1 are started
[                          main] DefaultCamelContext            INFO  Apache Camel 2.23.2.fuse-780036-redhat-00001 (CamelContext: camel-1) started in 0.374 seconds
[ead #2 - file://work/cbr/input] route1                         INFO  Receiving data order1.xml
Exception Thrown
handling ex
[ead #2 - file://work/cbr/input] route1                         INFO  Received body 
[ead #2 - file://work/cbr/input] route1                         INFO  Receiving data order2.xml
Exception Thrown
handling ex
[ead #2 - file://work/cbr/input] route1                         INFO  Received body 
[ead #2 - file://work/cbr/input] route1                         INFO  Receiving data order3.xml
Exception Thrown
handling ex
[ead #2 - file://work/cbr/input] route1                         INFO  Received body 
[ead #2 - file://work/cbr/input] route1                         INFO  Receiving data order4.xml
Exception Thrown
handling ex
[ead #2 - file://work/cbr/input] route1                         INFO  Received body 
[ead #2 - file://work/cbr/input] route1                         INFO  Receiving data order5.xml
Exception Thrown
handling ex
[ead #2 - file://work/cbr/input] route1                         INFO  Received body 
 
 
 */

// 2nd route  -- Route ExceptionHandling
