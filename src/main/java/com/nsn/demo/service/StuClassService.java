package com.nsn.demo.service;

import com.nsn.demo.dao.StuClassDao;
import com.nsn.demo.dao.bean.Page;
import com.nsn.demo.entity.StuClass;
import com.nsn.quick4j.aop.tx.annotation.Service;
import com.nsn.quick4j.aop.tx.annotation.Transaction;
import com.nsn.quick4j.ioc.annotation.Inject;

import java.io.Serializable;
import java.util.List;

/**
 * 学生班级业务逻辑类
 * @author donghao
 * @since 1.0
 */
@Service
public class StuClassService {

    @Inject
    private StuClassDao stuClassDao;//注入dao


    /**
     * 分页查询
     * @param page
     */
    public void queryPage(Page<StuClass> page)
    {
        //sql语句
        String sql = "select * from t_stuClass limit ?,?";
        //查询当前页数据
        List<StuClass> stuClassList = stuClassDao.getBeanList(sql,page.getStartNum(),page.getPageSize());
        page.setRows(stuClassList);
        //查询记录总数
        page.setResults(stuClassDao.recordCount());
    }

    public List<StuClass> queryAll(){
        return stuClassDao.getBeanList();
    }

    @Transaction
    public void addStuClass(StuClass stuClass){
        //添加用户
        stuClassDao.save(stuClass);
    }

    @Transaction
    public void updateStuClass(StuClass stuClass){
        //更新用户
        stuClassDao.update(stuClass);
    }

    @Transaction
    public void deleteStuClass(Serializable[] ids){
        stuClassDao.deleteByIds(ids);
    }
}
