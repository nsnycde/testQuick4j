package com.nsn.demo.web.controller;

import com.nsn.demo.dao.bean.Page;
import com.nsn.demo.entity.StuClass;
import com.nsn.demo.service.StuClassService;
import com.nsn.quick4j.ioc.annotation.Inject;
import com.nsn.quick4j.mvc.DataContext;
import com.nsn.quick4j.mvc.ModelDriven;
import com.nsn.quick4j.mvc.annotation.Action;
import com.nsn.quick4j.mvc.annotation.Controller;
import com.nsn.quick4j.mvc.bean.Data;
import com.nsn.quick4j.mvc.bean.View;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 学生班级控制器类
 *
 * @author donghao
 * @since 1.0
 */
@Controller
public class StuClassController implements ModelDriven<StuClass> {

    @Inject
    private StuClassService stuClassService;
    private StuClass stuClass = new StuClass();//驱动模型对象

    private String imageFileStoreDir = DataContext.getServletContext().getRealPath("/asset/images");//指定文件存储位置
    private File imageFile;//用于注入文件

    private String ids;//用于注入选中的id

    /**
     * 分页对象
     */
    Page<StuClass> page = new Page<StuClass>();

    /**
     * 添加班级
     * @return
     */
    @Action("POST:/stuClass/addStuClass")
    public Object addStuClass(){
        //添加班级
        if(imageFile!=null){
            String imagePath = "<img src='"+DataContext.getServletContext().getContextPath()+"/asset/images/"+imageFile.getName()+"'/>";
            stuClass.setImagePath(imagePath);
        }
        stuClassService.addStuClass(stuClass);
        return stuClassManage();
    }

    @Action("GET:/stuClass/getAddStuClassPage")
    public Object getAddStuClassPage(){
        View view = new View("stuClass/addStuClassPage.jsp");
        return view;
    }

    /**
     * 删除班级
     * @return
     */
    @Action("POST:/stuClass/deleteStuClass")
    public Object deleteStuClass(){
        //获取删除id
        String[] idArray = ids.split(",");
        stuClassService.deleteStuClass(idArray);
        //返回成功标识
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("success",true);
        return new Data(map);
    }

    /**
     * 班级管理主界面
     * @return
     */
    @Action("GET:/stuClass/stuClassManage")
    public Object stuClassManage(){
        return new View("stuClass/stuClassManage.jsp");
    }

    /**
     * 显示班级列表
     * @return
     */
    @Action("POST:/stuClass/listStuClass")
    public Object listStuClass(){
        //查询所有
        stuClassService.queryPage(page);
        return new Data(page);
    }

    @Action("POST:/stuClass/getClassList")
    public Object getStuClassList(){
        //查询所有
        return new Data(stuClassService.queryAll());
    }
    public StuClass getModel() {
        return stuClass;
    }
}
