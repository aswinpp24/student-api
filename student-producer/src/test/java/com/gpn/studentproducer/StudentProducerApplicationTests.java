package com.gpn.studentproducer;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

//import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.gpn.studentproducer.model.Student;
import com.gpn.studentproducer.repo.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentProducerApplicationTests {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	StudentRepository studentRepository; 
	
	
    @BeforeEach
    public void setup() {

    Student student1 = new Student();
    student1.setStudentId("200");
    student1.setStudentName("Aswin");
    student1.setGender("Male");
    student1.setPhoneNumber("8921499022");
    
    Student student2 = new Student();
    student2.setStudentId("201");
    student2.setStudentName("Reshmi");
    student2.setGender("Female");
    student2.setPhoneNumber("9921498922");
    
    Student student3 = new Student();
    student3.setStudentId("202");
    student3.setStudentName("Roshan");
    student3.setGender("Male");
    student3.setPhoneNumber("7829929222");
    
    
    
    studentRepository.save(student1);
    studentRepository.save(student2);
    studentRepository.save(student3);
    
    }
	
	// Test case retrieving all the student records
	@Test
	public void getAllStudents() throws Exception{
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
		          createURLWithPort("/student-producer"), HttpMethod.GET, entity, String.class);
		//JSONArray jsonArr = new JSONArray(response.getBody());

		assertEquals(200,response.getStatusCodeValue());
		//assertEquals(3,jsonArr.length());
	}
	
	// Test case retrieving the student record by Id
	@Test 
	public void getStudentById() throws Exception{

		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/student-producer/200"), HttpMethod.GET, entity, String.class);
		
		String expectedResponse =" {\n"
				+ "        \"studentId\": \"200\",\n"
				+ "        \"studentName\": \"Aswin\",\n"
				+ "        \"gender\": \"Male\",\n"
				+ "        \"phoneNumber\": \"8921499022\"\n"
				+ "    }";
		
		JSONAssert.assertEquals(expectedResponse,response.getBody(),false);
	}
	
	// Test case adding student record
	@Test
	public void addStudent() throws Exception{
		Student student = new Student();
		student.setStudentId("203");
	    student.setStudentName("Aashil");
	    student.setGender("Male");
	    student.setPhoneNumber("7827779222");
		HttpEntity<Student> entity = new HttpEntity<>(student);
		ResponseEntity<String> response = restTemplate.exchange(
		          createURLWithPort("/student-producer"), HttpMethod.POST, entity, String.class);

		assertEquals(200,response.getStatusCodeValue());
		assertEquals("Successfully Added",response.getBody());
	}
	
	// Test case for updating student record by Id
	@Test
	public void updateStudent() throws Exception{
		Student student = new Student();
	    student.setStudentName("Aswin P P");
		HttpEntity<Student> entity = new HttpEntity<>(student);
		
		ResponseEntity<String> response = restTemplate.exchange(
		          createURLWithPort("/student-producer/200"), HttpMethod.PUT, entity, String.class);

		assertEquals(200,response.getStatusCodeValue());
		assertEquals("Successfully Updated!!!",response.getBody());
	}
	
	// Test case for updating student with invalid Id
		@Test
		public void updateStudentWithInvalidId() throws Exception{
			Student student = new Student();
		    student.setStudentName("Aswin P P");
			HttpEntity<Student> entity = new HttpEntity<>(student);
			
			ResponseEntity<String> response = restTemplate.exchange(
			          createURLWithPort("/student-producer/300"), HttpMethod.PUT, entity, String.class);

			assertEquals("Id does not exist",response.getBody());
		}

	
	// Test case for deleting student record by Id
	@Test
	public void deleteStudent() throws Exception{
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
		          createURLWithPort("/student-producer/201"), HttpMethod.DELETE,entity, String.class);

		assertEquals(200,response.getStatusCodeValue());
		assertEquals("Successfully deleted",response.getBody());
	}
	
	// Test case for deleting student record with Invalid Id
	@Test
	public void deleteStudentWithInvalidId() throws Exception{
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
		          createURLWithPort("/student-producer/301"), HttpMethod.DELETE,entity, String.class);

		assertEquals("Id does not exist",response.getBody());
	}
	
	
	@Test
	private String createURLWithPort(String uri) {
	        return "http://127.0.0.1:" + port + uri;
	    }

}
