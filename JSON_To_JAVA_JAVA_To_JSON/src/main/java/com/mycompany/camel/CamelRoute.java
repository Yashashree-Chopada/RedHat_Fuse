package com.mycompany.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import com.mycompany.pojo.Employee;

public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception 
	{
		JacksonDataFormat js = new JacksonDataFormat(Employee.class);
		
		from("file:work/jsontojava/input")
		.log("Receiving data ${file:name}")
		.unmarshal(js)
		.log("Processing . . . . ")
		.log("Employee Id - ${body.getEmpID}")
		.log("Employee Name - ${body.getName}")
		.log("Employee Company Name - ${body.getCmpName}")
		.marshal(js)
		.log("Processing . . . . ")
		.log("Marshelled .... ${body}")
		.end();
	}

}

/*
[ychopada@ychopada JSON_To_JAVA]$ mvn exec:java
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
[ycompany.camel.Launcher.main()] DefaultCamelContext            INFO  Route: route1 started and consuming from: file://work/jsontojava/input
[ycompany.camel.Launcher.main()] DefaultCamelContext            INFO  Total 1 routes, of which 1 are started
[ycompany.camel.Launcher.main()] DefaultCamelContext            INFO  Apache Camel 2.23.2.fuse-790054-redhat-00001 (CamelContext: camel-1) started in 0.239 seconds
[- file://work/jsontojava/input] route1                         INFO  Receiving data test.json
[- file://work/jsontojava/input] route1                         INFO  Processing . . . . 
[- file://work/jsontojava/input] route1                         INFO  Employee Id - 1
[- file://work/jsontojava/input] route1                         INFO  Employee Name - Yashashree
[- file://work/jsontojava/input] route1                         INFO  Employee Company Name - RedHat
[- file://work/jsontojava/input] route1                         INFO  Processing . . . . 
[- file://work/jsontojava/input] route1                         INFO  Marshelled .... {"empID":1,"name":"Yashashree","cmpName":"RedHat"}

*/