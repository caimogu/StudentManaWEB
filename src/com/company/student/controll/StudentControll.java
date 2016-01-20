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
		 * url是如下形式时，跳转至save()函数
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
		
		//解析从view/add.html中传过来的 保存在req中的数据
		Student student = new Student();
		student.setId(Integer.parseInt(req.getParameter("id")));
		student.setName(req.getParameter("name"));
		student.setScore(Float.parseFloat(req.getParameter("score")));
		studentService.add(student);
		
		
		//保存结束以后跳转
		resp.sendRedirect("index.html");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//从index.html 中的<a href="list">查看学员</a> 跳转过来的
		List<Student> list = studentService.getList();
		
		//System.out.println("StudentControll--fun()--基本框架已经搭好！");
		//发过来的请求我这里不作处理，转到view/list.jsp
		req.setAttribute("list", list);//把上面的list设置到req中 再转移
		req.getRequestDispatcher("view/list.jsp").forward(req, resp);
		//System.out.println("StudentControll--fun()--基本框架已经搭好！");
	}
}
