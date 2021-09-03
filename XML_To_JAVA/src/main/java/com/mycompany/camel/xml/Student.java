package com.mycompany.camel.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student 
{
	@XmlElement
	private String name;
	
	@XmlElement
	private String div;
	
	@XmlElement
	private Integer rollnumber;
	
	@XmlElementWrapper(name = "subjects")	//root tag for list of subjects
	@XmlElement(name =  "Subject")
	private List<Subject> subjects = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public Integer getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(Integer rollnumber) {
		this.rollnumber = rollnumber;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Student(String name, String div, Integer rollnumber, List<Subject> subjects) {
		super();
		this.name = name;
		this.div = div;
		this.rollnumber = rollnumber;
		this.subjects = subjects;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
	
	
	

}
