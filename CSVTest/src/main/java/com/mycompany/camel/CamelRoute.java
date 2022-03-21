package com.mycompany.camel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CsvDataFormat;

import com.sun.istack.logging.Logger;

public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
Logger logger = Logger.getLogger(this.getClass());
		
		CsvDataFormat cs = new CsvDataFormat();
	
		from("timer:start?period=10s")
			.log("Receiving order ${file:name}")
			.process(new Processor()
			{
				public void process(Exchange exchange) throws Exception {


					logger.info("CSVGenProcessor process Start");

					List<Map<String, Object>> Data1=new ArrayList<Map<String, Object>>();
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					Map<String, Object> map1 = new LinkedHashMap<String, Object>();
					Map<String, Object> map2 = new LinkedHashMap<String, Object>();
					Map<String, Object> map3 = new LinkedHashMap<String, Object>();
					Map<String, Object> map4 = new LinkedHashMap<String, Object>();
					map.put("NAME","NAME");
					map.put("DOB","DOB");
					map.put("ORG","ORG");
					map.put("AGE","AGE");
					map.put("PLACE","PLACE");
					Data1.add(map);
					map4.put("NAME","k");
					map4.put("DOB","124");
					map4.put("ORG","sopra steria");
					map4.put("AGE", null);
					map4.put("PLACE",null);
					Data1.add(map4);
					map3.put("NAME","A");
					map3.put("DOB","12");
					map3.put("ORG","sopra steria");
					map3.put("AGE", null);
					map3.put("PLACE",null);
					Data1.add(map3);
					map2.put("NAME","b");
					map2.put("DOB","24");
					map2.put("ORG","sopra steria");
					map2.put("AGE", null);
					map2.put("PLACE",null);
					Data1.add(map2);
					map1.put("NAME","i");
					map1.put("DOB","4");
					map1.put("ORG","sopra steria");
					map1.put("AGE", null);
					map1.put("PLACE",null);
					Data1.add(map1);

					logger.info(""+Data1);

					exchange.getIn().setBody(Data1);
				}
			})
		
		.log("Processing file CSV Data").streamCaching()
	        //.marshal(cs.setQuoteDisabled(true))
	      //.marshal(new CsvDataFormat().setQuoteDisabled(true))
	       .marshal(cs)
	        .log("${body}");
			
				
				
				

	
	
		
	}

}
