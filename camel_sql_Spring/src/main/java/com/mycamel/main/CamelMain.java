package com.mycamel.main;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycamel.domain.Employee;

public class CamelMain {
	 public static void main(String[] args) {

	        try {
ApplicationContext springCtx = new ClassPathXmlApplicationContext("camel-context.xml");

	            CamelContext context = springCtx.getBean("employeeContext", CamelContext.class);

	            context.start();

	            ProducerTemplate producerTemplate = context.createProducerTemplate();

	            // Insert Employee 1
	            Employee emp1 = getEmployee1();
	            String resp = producerTemplate.requestBody("direct:insert", emp1, String.class);

	            // Insert Employee 2
	            Employee emp2 = getEmployee2();
	            resp = producerTemplate.requestBody("direct:insert", emp2, String.class);

	            // Get Employee of inserted employees
	            List<Employee> employees = producerTemplate.requestBody("direct:select", null, List.class);
	            System.out.println("employees:" + employees);

	        }
	        catch (Exception e) {

	            e.printStackTrace();

	        }
	    }

	    private static Employee getEmployee1() {

	        Employee emp = new Employee();

	        emp.setEmpId("empId1");
	        emp.setEmpName("emp1");
	        return emp;

	    }

	    private static Employee getEmployee2() {

	        Employee emp = new Employee();

	        emp.setEmpId("empId2");
	        emp.setEmpName("emp2");
	        return emp;

	    }

}
