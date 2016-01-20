package com.company.student.service;

import java.util.ArrayList;
import java.util.List;

import com.company.student.model.Student;

public class StudentService {
	private List<Student> list;
	
	public StudentService(){
		list = new ArrayList<Student>();//·ºĞÍ
	}
	
	public void add(Student student){
		list.add(student);
	}
	
	public List<Student> getList(){
		return list;
	}
	
}
