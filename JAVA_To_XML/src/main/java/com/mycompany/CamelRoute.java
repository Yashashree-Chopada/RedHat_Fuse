package com.mycompany;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		    
		    JAXBContext obj2 = JAXBContext.newInstance(Student.class);
		    Marshaller mobj = obj2.createMarshaller();  
		    mobj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
		    
		    Subject s1 = new Subject("JAVA");
		    Subject s2 = new Subject ("CPP");
		    Subject s3 = new Subject("Python");
		    
		    ArrayList <Subject> ls = new ArrayList<Subject>();
		    ls.add(s1);
		    ls.add(s2);
		    ls.add(s3);
		
		  Student s = new Student(1,"Yashu",ls);
		  mobj.marshal(s, new FileOutputStream("student.xml")); 
		    
		  
		
	}
}
