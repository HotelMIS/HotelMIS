package com.fly.hotelmis.student.dao;

import java.util.List;
import java.util.Map;

import com.fly.common.dao.BaseDao;
import com.fly.hotelmis.student.entity.Student;
import com.fly.hotelmis.test.entity.Person;
import com.fly.hotelmis.test.vo.PersonVo;

public interface StudentDao extends BaseDao {
	public List<Student> queryStudent();

	public void saveStudent(Student student);

	public void updateStudent(Student student);

	public void deleteStudent(Student student);

}
