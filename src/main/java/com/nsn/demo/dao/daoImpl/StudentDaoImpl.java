package com.nsn.demo.dao.daoImpl;

import com.nsn.demo.dao.StudentDao;
import com.nsn.demo.entity.Student;
import com.nsn.quick4j.dao.impl.BaseDaoImpl;
import com.nsn.quick4j.ioc.annotation.Bean;

/**
 * @author donghao
 * @since 1.0
 */
@Bean
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

}
