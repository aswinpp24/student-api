package com.gpn.studentconsumer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gpn.studentconsumer.model.Student;

@Service
public class StudentConsumerService {
	
	private final String GET_ALL_STUDENTS_API ="http://localhost:8081/student-producer";
	private final String GET_STUDENT_BY_ID_API ="http://localhost:8081/student-producer/{id}";
	private final String ADD_STUDENT_API ="http://localhost:8081/student-producer";
	private final String DELETE_STUDENT_BY_ID_API ="http://localhost:8081/student-producer/{id}";
	private final String UPDATE_STUDENT_BY_ID_API ="http://localhost:8081/student-producer/{id}";
	
	
	private RestTemplate restTemplate;

	@Autowired
	public StudentConsumerService(RestTemplateBuilder restTemplateBuilder) {
	
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public List<Student> getStudents(){
		return restTemplate.exchange(GET_ALL_STUDENTS_API,HttpMethod.GET,null,List.class).getBody();
	}
	
	public Student getStudentById(String id) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		return restTemplate.getForObject(GET_STUDENT_BY_ID_API, Student.class, param);
	}
	
	public String saveStudent(Student student) {
		HttpEntity<Student> entity = new HttpEntity<>(student);
		return restTemplate.exchange(ADD_STUDENT_API,HttpMethod.POST,entity, String.class).getBody();	
	}
	
	public String updateStudent(String id, Student student) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		restTemplate.put(UPDATE_STUDENT_BY_ID_API, student, param);
		return "Successfully Updated!!!";
	}
	
	public String deleteStudent(String id) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		restTemplate.delete(DELETE_STUDENT_BY_ID_API, param);
		return "Deleted Successfully!!!";
	}
	
	

}
