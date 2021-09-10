package com.gpn.studentconsumer;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.gpn.studentconsumer.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentConsumerApplicationTests {
	
	@LocalServerPort
	private int port;
	
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();

	// Test case retrieving all the student records
	@Test
	public void getAllStudents() throws Exception{
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
		          createURLWithPort("/api/v1/student"), HttpMethod.GET, entity, String.class);
		//JSONArray jsonArr = new JSONArray(response.getBody());

		assertEquals(200,response.getStatusCodeValue());
		//assertEquals(3,jsonArr.length());
	}
	
	// Test case retrieving the student record by Id
		@Test 
		public void getStudentById() throws Exception{

			HttpEntity<String> entity = new HttpEntity<String>(null,headers);
			ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/student/103"), HttpMethod.GET, entity, String.class);
			
			String expectedResponse =" {\n"
					+ "        \"studentId\": \"103\",\n"
					+ "        \"studentName\": \"Varsha M\",\n"
					+ "        \"gender\": \"Female\",\n"
					+ "        \"phoneNumber\": \"8921477222\"\n"
					+ "    }";
			
			JSONAssert.assertEquals(expectedResponse,response.getBody(),false);
		}
		
		
		// Test case adding student record
		@Test
		public void addStudent() throws Exception{
			Student student = new Student();
			student.setStudentId("105");
		    student.setStudentName("Aashil");
		    student.setGender("Male");
		    student.setPhoneNumber("7827779222");
			HttpEntity<Student> entity = new HttpEntity<>(student);
			ResponseEntity<String> response = restTemplate.exchange(
			          createURLWithPort("/api/v1/student"), HttpMethod.POST, entity, String.class);

			assertEquals(200,response.getStatusCodeValue());
			assertEquals("Successfully Added",response.getBody());
		}
		
		// Test case for updating student record by Id
		@Test
		public void updateStudent() throws Exception{
			Student student = new Student();
		    student.setStudentName("Amal Krishna");
			HttpEntity<Student> entity = new HttpEntity<>(student);

			ResponseEntity<String> response = restTemplate.exchange(
			          createURLWithPort("/api/v1/student/102"), HttpMethod.PUT, entity, String.class);

			assertEquals(200,response.getStatusCodeValue());
			assertEquals("Successfully Updated!!!",response.getBody());
		}
		
		// Test case for deleting student record by Id
		@Test
		public void deleteStudent() throws Exception{
			HttpEntity<String> entity = new HttpEntity<String>(null,headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
			          createURLWithPort("/api/v1/student/104"), HttpMethod.DELETE,entity, String.class);

			assertEquals(200,response.getStatusCodeValue());
			assertEquals("Deleted Successfully!!!",response.getBody());
		}

	
	@Test
	private String createURLWithPort(String uri) {
	        return "http://127.0.0.1:" + port + uri;
	    }

}
