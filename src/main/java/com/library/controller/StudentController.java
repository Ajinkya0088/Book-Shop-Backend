package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Student;
import com.library.repository.StudentRepository;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/student")
	public Student add(@RequestBody Student student)
	{
		return studentRepository.save(student);
		
	}
	
	@GetMapping("/students")
	public List<Student> getUsers()
	{
		return studentRepository.findAll();
	}
	
	@GetMapping("/students/{id}")
	public Student getUser(@PathVariable int id)
	{
		return studentRepository.getOne(id);
	}
	 
	@DeleteMapping("/students/{id}")
	public  void delete(@PathVariable int id)
	{
		studentRepository.deleteById(id);
	}
	
	@PutMapping("/students/{id}")
	public  void updateStudent (@RequestBody Student student,@PathVariable int id)
	{
		Student s = studentRepository.getOne(id);
		s.setName(student.getName());
		s.setDays(student.getDays());
		s.setBook(student.getBook());
		//s.setId(student.getId());
		s.setDivision(student.getDivision());
		s.setStandard(student.getStandard());
		
		studentRepository.save(s);
		
	}
	
}
