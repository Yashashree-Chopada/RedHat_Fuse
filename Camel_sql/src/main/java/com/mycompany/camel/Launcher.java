package com.mycompany.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;


import com.mysql.cj.jdbc.MysqlDataSource;


public class Launcher {
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String args[]) throws Exception {
    	
    	MysqlDataSource dataSource = new MysqlDataSource();
    	
    	
    	dataSource.setURL("jdbc:mysql://localhost:3306/new_db");
    	dataSource.setUser("root");
    	dataSource.setPassword("Oracle_1");
    	
    	SimpleRegistry re = new SimpleRegistry();
    	re.put("myDataSource",dataSource);
    	
    	CamelContext context = new DefaultCamelContext(re);
    	context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception 
			{
				from("direct:start")
				.to("jdbc:myDataSource")
				.bean(new ResultHandler(),"printResult");
				
			}
		});
    	 
    	context.start();
    	Thread.sleep(30000);
    	
    	
    	ProducerTemplate pt = context.createProducerTemplate();
    	pt.sendBody("direct:start","select * from customer");
        
    }
}
