package com.gpn.studentproducer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpn.studentproducer.model.Student;
import com.gpn.studentproducer.repo.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		student.setStudentId(student.getStudentId());
		return studentRepository.save(student);
	}

	@Override
	public String updateStudent(String id, Student student) {
		// TODO Auto-generated method stub
		Student studentData = studentRepository.findByStudentId(id);
		if(studentData == null) {
			return "Id does not exist";
		}
		else { 
			studentData.setStudentName(student.getStudentName());
			studentData.setGender(student.getGender());
			studentData.setPhoneNumber(student.getPhoneNumber());
			studentData.setStudentId(id);
			studentRepository.save(studentData);
		return "Successfully Updated!!!";	
		}
		
	}

	@Override
	public String deleteStudent(String id) {
		// TODO Auto-generated method stub
		Student studentData = studentRepository.findByStudentId(id);
		if(studentData == null) {
			return "Id does not exist";
		}
		else {
		studentRepository.deleteByStudentId(id);
		return "Successfully deleted";
		}

	}

	@Override
	public Student getStudentById(String id) {
		// TODO Auto-generated method stub
		
		return studentRepository.findByStudentId(id);
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

}
