package com.fly.hotelmis.student.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fly.common.dao.impl.BaseDaoImpl;
import com.fly.common.page.Page;
import com.fly.hotelmis.student.dao.StudentDao;
import com.fly.hotelmis.student.entity.Student;

@Repository("studentDao")
public class StudentDaoImpl extends BaseDaoImpl implements StudentDao {

	@Override
	public List<Student> queryStudent() {
		String hql = "select student from Student student";
		List<Student> list = this.findAll(hql);
		for (int i = 0; i < list.size(); i++) {
			Student stu = (Student) list.get(i);
			System.out.println(stu.getAge() + stu.getCardname());
		}
		return list;

	}

	@Override
	public void saveStudent(Student student) {
		this.insert(student);
	}

	@Override
	public void updateStudent(Student student) {

		this.update(student);
	}

	@Override
	public void deleteStudent(Student student) {
		this.delete(student);

	}

}
