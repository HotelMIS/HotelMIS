package com.fly.hotelmis.student.service;

import java.util.List;
import java.util.Map;

import com.fly.hotelmis.student.entity.Student;
import com.fly.hotelmis.student.vo.StudentVo;
import com.fly.hotelmis.test.vo.PersonVo;

/**
 * ----------------------------------------------------------------------------
 * ----- Confidential and Proprietary Copyright 2010 By SGAI & Hewlett-Packard
 * Development Company, L.P. All Rights Reserved
 * 
 * Project Name : SGAI MES Class Name : PersonService.java Package :
 * com.fly.project.test.service
 * 
 * @version $Id$
 * @author 刘志才
 * @since 2011-5-20
 */
public interface StudentService {
	public List<Student> queryStudent();

	public void saveStudent(StudentVo studentvo);

	public void updateStudent(StudentVo studentvo);

	public void deleteStudent(StudentVo studentvo);
}
