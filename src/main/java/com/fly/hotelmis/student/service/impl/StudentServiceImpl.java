package com.fly.hotelmis.student.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fly.hotelmis.student.dao.StudentDao;
import com.fly.hotelmis.student.entity.Student;
import com.fly.hotelmis.student.service.StudentService;
import com.fly.hotelmis.student.vo.StudentVo;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;

	@Override
	public List<Student> queryStudent() {
		return studentDao.queryStudent();
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveStudent(StudentVo studentvo) {
		Student student = new Student();
		try {
			BeanUtils.copyProperties(student, studentvo);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		studentDao.saveStudent(student);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateStudent(StudentVo studentvo) {
		Student student = new Student();
		try {
			BeanUtils.copyProperties(student, studentvo);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		studentDao.updateStudent(student);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteStudent(StudentVo studentvo) {
		Student student = new Student();
		try {
			BeanUtils.copyProperties(student, studentvo);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		studentDao.deleteStudent(student);
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

}
