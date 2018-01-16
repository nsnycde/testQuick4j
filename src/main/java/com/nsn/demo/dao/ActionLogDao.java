package com.nsn.demo.dao;

import com.nsn.demo.dao.daoImpl.ActionLogDaoImpl;
import com.nsn.demo.entity.ActionLog;
import com.nsn.quick4j.dao.BaseDao;
import com.nsn.quick4j.ioc.annotation.Impl;

/**
 * @author donghao
 * @since 1.0
 */
@Impl(ActionLogDaoImpl.class)
public interface ActionLogDao extends BaseDao<ActionLog>{
}
