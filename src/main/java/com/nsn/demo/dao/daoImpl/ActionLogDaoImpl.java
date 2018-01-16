package com.nsn.demo.dao.daoImpl;

import com.nsn.demo.dao.ActionLogDao;
import com.nsn.demo.entity.ActionLog;
import com.nsn.quick4j.dao.impl.BaseDaoImpl;
import com.nsn.quick4j.ioc.annotation.Bean;

/**
 * @author donghao
 * @since 1.0
 */
@Bean
public class ActionLogDaoImpl extends BaseDaoImpl<ActionLog> implements ActionLogDao{

}
