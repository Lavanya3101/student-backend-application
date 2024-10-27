package com.example.StudentApplication.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentApplication.model.Student;
import com.example.StudentApplication.service.IStudentService;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/students")
public class StudentController {
	
	private final IStudentService iStudentService;
	
	public StudentController(IStudentService iStudentService) {
		this.iStudentService=iStudentService;
	}
	@PostMapping("/save")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		return new ResponseEntity<>(iStudentService.addStudent(student),HttpStatus.CREATED);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Student>> getStudents(){
		return new ResponseEntity<>(iStudentService.getStudents(),HttpStatus.FOUND);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable Long id){
		return new ResponseEntity<Student>(iStudentService.updateStudent(student, id),HttpStatus.OK);
		
	}
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable Long id) {
		iStudentService.deleteStudent(id);
	}
	@GetMapping("/getById/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return iStudentService.getStudentById(id);
	}

}
