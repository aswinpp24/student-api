package com.gpn.studentproducer.service;

import java.util.List;

import com.gpn.studentproducer.model.Student;



public interface StudentService {

	Student saveStudent(Student student);
	String updateStudent(String id, Student student);
	String deleteStudent(String id);
	Student getStudentById(String id);
	List<Student> getAllStudents();
}
