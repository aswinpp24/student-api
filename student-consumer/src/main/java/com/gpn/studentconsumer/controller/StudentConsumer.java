package com.gpn.studentconsumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpn.studentconsumer.model.Student;
import com.gpn.studentconsumer.service.StudentConsumerService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentConsumer {
	
	@Autowired
	private StudentConsumerService studentConsumerService;

	
	@GetMapping
	public List<Student> getAll(){
		return studentConsumerService.getStudents();
	}
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable("id") String id) {
		return studentConsumerService.getStudentById(id);
	}
	
	@PostMapping
	public String AddStudent(@RequestBody Student student) {
		return studentConsumerService.saveStudent(student);
		
	}
	
	@PutMapping("/{id}")
	public String UpdateStudent(@PathVariable("id") String id, @RequestBody Student student) {
		 return studentConsumerService.updateStudent(id, student); 
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteVendor(@PathVariable("id") String id) {
	      return studentConsumerService.deleteStudent(id);
	}
}
