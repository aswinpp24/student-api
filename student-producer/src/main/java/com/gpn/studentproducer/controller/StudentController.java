package com.gpn.studentproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gpn.studentproducer.model.Student;
import java.util.List;

import com.gpn.studentproducer.service.StudentService;

@RestController
@RequestMapping("/student-producer")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public List<Student> listStudents() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable("id") String id) {
		return studentService.getStudentById(id);
	}
	
	@PostMapping
	public String AddStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
		return "Successfully Added";
		
	}
	
	@PutMapping("/{id}")
	public String UpdateStudent(@PathVariable("id") String id, @RequestBody Student student) {
		return studentService.updateStudent(id, student);
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteVendor(@PathVariable("id") String id) {
	       return studentService.deleteStudent(id);
	}
}
