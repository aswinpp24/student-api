package com.gpn.studentproducer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gpn.studentproducer.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
	public void deleteByStudentId(String studentId);
	public Student findByStudentId(String studentId);
}
