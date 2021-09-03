package com.mycompany.camel;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.mycompany.camel.xml.Student;

public class java_to_xml  implements Processor
{
	  Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void process(Exchange exchange) throws Exception {
		Student s = new Student();
		s.setName("Yashu");
		s.setRollnumber(20);
		s.setDiv("B");
		
		List l = new ArrayList();
		l.add("JAVA");
		l.add("C");
		l.add("CPP");
		s.setSubjects(l);
		
		  logger.info("In Marshaling - Students Data :");
		
		  exchange.getIn().setBody(s);
		
		
	}
	
	
}
