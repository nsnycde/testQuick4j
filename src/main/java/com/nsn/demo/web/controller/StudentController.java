package com.nsn.demo.web.controller;

import com.nsn.demo.dao.bean.Page;
import com.nsn.demo.entity.Student;
import com.nsn.quick4j.ioc.annotation.Inject;
import com.nsn.quick4j.kit.CastKit;
import com.nsn.quick4j.mvc.ModelDriven;
import com.nsn.quick4j.mvc.annotation.Action;
import com.nsn.quick4j.mvc.annotation.Controller;
import com.nsn.quick4j.mvc.bean.Data;
import com.nsn.quick4j.mvc.bean.View;
import com.nsn.demo.service.StudentService;

import java.util.*;

/**
 * @author donghao
 * @since 1.0
 */

@Controller
public class StudentController implements ModelDriven<Student>{

    @Inject
    private StudentService studentService;

    /**
     * 用户对象
     */
    private Student student = new Student();

    /**
     * 删除id
     */
    private String ids;

    /**
     * 查询条件
     */
    private String queryName;
    private int querySex = -1;
    private Date startDate;
    private Date endDate;
    private String classId;

    /**
     * 分页对象
     */
    Page<Map<String,Object>> page = new Page<Map<String,Object>>();

    /**
     * 添加用户
     * @return
     */
    @Action("POST:/student/addStudent")
    public Object addStudent(){
        //添加用户
        studentService.addUser(student);
        //返回成功标识
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("success",true);
        return new Data(map);
    }

    /**
     * 更新用户
     * @return
     */
    @Action("POST:/student/updateStudent")
    public Object updateStudent(){
        //更新用户
        studentService.updateUser(student);
        //返回成功标识
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("success",true);
        return new Data(map);
    }

    /**
     * 删除用户
     * @return
     */
    @Action("POST:/student/deleteStudent")
    public Object deleteStudent(){
        //获取删除id
        String[] idStrArray = ids.split(",");
        Integer[] idArray = CastKit.castIntegerArray(idStrArray);
        studentService.deleteUser(idArray);
        //返回成功标识
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("success",true);
        return new Data(map);
    }

    /**
     * 用户管理主界面
     * @return
     */
    @Action("GET:/student/studentManage")
    public Object studentManage(){
        return new View("student/studentManage.jsp");
    }

    /**
     * 显示用户列表
     * @return
     */
    @Action("POST:/student/listStudent")
    public Object listStudent(){
        //封装条件map
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("queryName",queryName);
        map.put("querySex",querySex);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("classId",classId);
        //查询所有
        studentService.queryPage(page,map);
        return new Data(page);
    }


    public Student getModel() {
        return student;
    }
}
