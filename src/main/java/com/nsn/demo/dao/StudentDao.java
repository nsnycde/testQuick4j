package com.nsn.demo.dao;

import com.nsn.demo.dao.daoImpl.StudentDaoImpl;
import com.nsn.demo.entity.Student;
import com.nsn.quick4j.dao.BaseDao;
import com.nsn.quick4j.ioc.annotation.Impl;

/**
 * @author donghao
 * @since 1.0
 */
@Impl(StudentDaoImpl.class)
public interface StudentDao extends BaseDao<Student>{
}
