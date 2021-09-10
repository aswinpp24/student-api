package com.gpn.studentconsumer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gpn.studentconsumer.model.Student;




@Service
public class StudentConsumerService {
	

	@Value("${studentapi.studenturl}")
	String studentUrl;
	@Value("${studentapi.studentidurl}")
	String studentIdUrl;
	
	
	private RestTemplate restTemplate;

	@Autowired
	public StudentConsumerService(RestTemplateBuilder restTemplateBuilder) {
	
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public List<Student> getStudents(){
		return restTemplate.exchange(studentUrl,HttpMethod.GET,null,List.class).getBody();
	}
	
	public Student getStudentById(String id) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		return restTemplate.getForObject(studentIdUrl, Student.class, param);
	}
	
	public String saveStudent(Student student) {
		HttpEntity<Student> entity = new HttpEntity<>(student);
		return restTemplate.exchange(studentUrl,HttpMethod.POST,entity, String.class).getBody();	
	}
	
	public String updateStudent(String id, Student student) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		restTemplate.put(studentIdUrl, student, param);
		return "Successfully Updated!!!";
	}
	
	public String deleteStudent(String id) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		restTemplate.delete(studentIdUrl, param);
		return "Deleted Successfully!!!";
	}
	
	

}
