package com.nsn.demo.service;

import com.nsn.demo.dao.ActionLogDao;
import com.nsn.demo.entity.ActionLog;
import com.nsn.quick4j.aop.tx.annotation.Service;
import com.nsn.quick4j.aop.tx.annotation.Transaction;
import com.nsn.quick4j.ioc.annotation.Inject;

import java.util.List;

/**
 * @author donghao
 * @since 1.0
 */
@Service
public class ActionLogService {

    @Inject
    private ActionLogDao actionLogDao;

    /**
     * 插入log信息
     * @param actionLog
     */
    @Transaction
    public void insertLog(ActionLog actionLog){
        actionLogDao.save(actionLog);
    }

    /**
     * 获取所有log信息
     * @return
     */
    public List<ActionLog> getAllLog(){
        return actionLogDao.getBeanList();
    }
}
