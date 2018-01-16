package com.nsn.demo.service;

import com.nsn.demo.dao.bean.Page;
import com.nsn.demo.entity.Student;
import com.nsn.demo.dao.daoImpl.StudentDaoImpl;
import com.nsn.quick4j.aop.tx.annotation.Service;
import com.nsn.quick4j.aop.tx.annotation.Transaction;
import com.nsn.quick4j.ioc.annotation.Inject;
import com.nsn.quick4j.kit.StringKit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author donghao
 * @since 1.0
 */
@Service
public class StudentService {

    @Inject
    StudentDaoImpl studentDao;

    public void queryPage(Page<Map<String,Object>> page, Map<String,Object> map){
        //条件查询
        String sql = "select * from t_student a left join t_stuClass b on a.s_classId=b.c_id";
        String queryName = (String)map.get("queryName");
        String classId = (String)map.get("classId");
        int querySex = (Integer) map.get("querySex");
        Date startDate = (Date)map.get("startDate");
        Date endDate = (Date)map.get("endDate");
        List<String> condition = new ArrayList<String>();
        List<Object> values = new ArrayList<Object>();
        if(StringKit.isNotEmpty(queryName)){
            condition.add("s_name like ?");
            values.add("%"+queryName+"%");
        }
        if(StringKit.isNotEmpty(classId)){
            condition.add("s_classId = ?");
            values.add(classId);
        }
        if(querySex != -1){
            condition.add("s_sex = ?");
            values.add(querySex);
        }
        if(startDate!=null){
            condition.add("s_birthday >= ?");
            values.add(startDate);
        }
        if(endDate!=null){
            condition.add("s_birthday <= ?");
            values.add(endDate);
        }
        //拼接条件
        if(values.size()>0){
            sql += " where ";
            for(int i=0;i<values.size();i++){
                sql += condition.get(i);
                if(i!=values.size()-1){
                    sql += " and ";
                }
            }
        }
        //拼接分页条件
        sql += " limit ?,?";
        values.add(page.getStartNum());
        values.add(page.getPageSize());
        List<Map<String,Object>> mapList = studentDao.getMapList(sql,values.toArray());
        page.setRows(mapList);
        //查询总共记录数
        int recordCount = studentDao.recordCount();
        page.setResults(recordCount);
    }

    @Transaction
    public void addUser(Student student){
        //添加用户
        studentDao.save(student);
    }

    @Transaction
    public void updateUser(Student student){
        //更新用户
        studentDao.update(student);
    }

    @Transaction
    public void deleteUser(Serializable[] ids){
        studentDao.deleteByIds(ids);
    }
}
