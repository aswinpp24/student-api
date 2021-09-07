package com.gpn.studentproducer.model;

import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonIgnore;

//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
@Document("student_info")
public class Student {
	@Id
	private String studentId;
	
	private String studentName;
	
	private String gender;
	
	private String phoneNumber;
	
	

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	
	


}
