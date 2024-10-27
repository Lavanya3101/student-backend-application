package com.example.StudentApplication.service;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentApplication.exception.StudentAlreadyExistsException;
import com.example.StudentApplication.exception.StudentNotFoundException;
import com.example.StudentApplication.model.Student;
import com.example.StudentApplication.repository.StudentRepository;
@Service
public class StudentServiceImpl implements IStudentService{

	private final StudentRepository studentRepository ;
//	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository=studentRepository;
	}
	
	@Override
	public Student addStudent(Student student) {
		if(studentAlreadyExist(student.getEmail())) {
			throw new StudentAlreadyExistsException(student.getEmail()+"Already exists");
		}
		return studentRepository.save(student);
	}

	

	@Override
	public List<Student> getStudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public Student updateStudent(Student student, Long id) {
		
		return studentRepository.findById(id).map(st->{
			st.setFirstName(student.getFirstName());
			st.setLastName(student.getLastName());
			st.setEmail(student.getEmail());
			st.setDepartment(student.getDepartment());
			return studentRepository.save(st);
		}).orElseThrow(()->new StudentNotFoundException("Sorry,this student could not be found"));
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(()->new StudentNotFoundException("Sorry,this student could not be found with this id:"+id));
	}

	@Override
	public void deleteStudent(Long id) {
	    if (!studentRepository.existsById(id)) {
	        throw new StudentNotFoundException("Student not found");
	    }
	    studentRepository.deleteById(id); // Perform the deletion
	}

private boolean studentAlreadyExist(String email) {
		
		return studentRepository.findByEmail(email).isPresent();
	}

}
