package com.mycompany.pojo;

public class Employee
{
	
	private int empID;
	
	private String name;
	
	private String cmpName;

	public Employee(int empID, String name, String cmpName) {
		super();
		this.empID = empID;
		this.name = name;
		this.cmpName = cmpName;
	}

	public Employee() {
		
	}

	public long getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCmpName() {
		return cmpName;
	}

	public void setCmpName(String cmpName) {
		this.cmpName = cmpName;
	}
	
	
}
