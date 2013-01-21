package com.fly.hotelmis.student.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Component;

import com.fly.common.action.BaseAction;
import com.fly.hotelmis.student.entity.Student;
import com.fly.hotelmis.student.service.StudentService;

@Component
public class QueryStudentAction extends BaseAction {
	private List<Student> studentList;

	@Resource
	private StudentService studentService;

	@Action(value = "queryStudent", results = { @Result(name = SUCCESS, location = "/frames/student/main.jsp") })
	public String execute() throws Exception {
		studentList = studentService.queryStudent();
		for (int i = 0; i < studentList.size(); i++) {
			Student student = studentList.get(i);
			System.out.println(student.getId() + "   :   " + student.getAge());
		}
		return SUCCESS;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

}
