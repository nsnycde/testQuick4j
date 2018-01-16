package com.nsn.demo.dao.daoImpl;

import com.nsn.demo.dao.StuClassDao;
import com.nsn.demo.entity.StuClass;
import com.nsn.quick4j.dao.impl.BaseDaoImpl;
import com.nsn.quick4j.ioc.annotation.Bean;

/**
 * @author donghao
 * @since 1.0
 */
@Bean
public class StuClassDaoImpl extends BaseDaoImpl<StuClass> implements StuClassDao{
}
