package com.mycompany;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement  
public class Student 
{
	private int rollnumber;  
	private String sname;  
	private List<Subject> subject;  
	public Student() {}
	public Student(int rollnumber, String sname, List<Subject> subject) {
		super();
		this.rollnumber = rollnumber;
		this.sname = sname;
		this.subject = subject;
	}
	@XmlAttribute
	public int getRollnumber() {
		return rollnumber;
	}
	public void setRollnumber(int rollnumber) {
		this.rollnumber = rollnumber;
	}
	@XmlElement
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	@XmlElement
	public List<Subject> getSubject() {
		return subject;
	}
	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}  
	
	
	
}
