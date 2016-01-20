package com.company.student.controll;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.student.model.Student;
import com.company.student.service.StudentService;

public class StudentControll extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	StudentService studentService;
	
	
	
	public StudentControll() {
		
		this.studentService = new StudentService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * url��������ʽʱ����ת��save()����
		 *http://localhost:8080/stu/student.do?a=save
		*/
		String a = req.getParameter("a");
		if(a!=null && a.equals("save"))
		{
			save(req,resp);
			return;
		}
		
		list(req,resp);
	}

	private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		
		//������view/add.html�д������� ������req�е�����
		Student student = new Student();
		student.setId(Integer.parseInt(req.getParameter("id")));
		student.setName(req.getParameter("name"));
		student.setScore(Float.parseFloat(req.getParameter("score")));
		studentService.add(student);
		
		
		//��������Ժ���ת
		resp.sendRedirect("index.html");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//��index.html �е�<a href="list">�鿴ѧԱ</a> ��ת������
		List<Student> list = studentService.getList();
		
		//System.out.println("StudentControll--fun()--��������Ѿ���ã�");
		//�����������������ﲻ������ת��view/list.jsp
		req.setAttribute("list", list);//�������list���õ�req�� ��ת��
		req.getRequestDispatcher("view/list.jsp").forward(req, resp);
		//System.out.println("StudentControll--fun()--��������Ѿ���ã�");
	}
}
