package com.nsn.demo.dao;

import com.nsn.demo.dao.daoImpl.StuClassDaoImpl;
import com.nsn.demo.entity.StuClass;
import com.nsn.quick4j.dao.BaseDao;
import com.nsn.quick4j.ioc.annotation.Impl;

/**
 * 学生班级dao
 * @author donghao
 * @since 1.0
 */
@Impl(StuClassDaoImpl.class)
public interface StuClassDao extends BaseDao<StuClass>{
}
