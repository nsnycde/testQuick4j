package com.nsn.demo.aspect;

import com.nsn.demo.entity.ActionLog;
import com.nsn.demo.service.ActionLogService;
import com.nsn.demo.web.controller.ActionLogController;
import com.nsn.quick4j.aop.AbstractAspect;
import com.nsn.quick4j.aop.annotation.Aspect;
import com.nsn.quick4j.aop.annotation.AspectOrder;
import com.nsn.quick4j.ioc.annotation.Inject;
import com.nsn.quick4j.mvc.ControllerHelper;
import com.nsn.quick4j.mvc.DataContext;

import java.lang.reflect.Method;

/**
 * 控制器类切面
 * @author donghao
 * @since 1.0
 */
@AspectOrder(1)
@Aspect(pkPath = "com.nsn.demo.web.controller")
public class ControllerAspect extends AbstractAspect {

    private long currentTimeMillis;
    @Inject
    private ActionLogService actionLogService;

    @Override
    public void begin() {
        currentTimeMillis = System.currentTimeMillis();
    }

    @Override
    public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams,Object targetObject) {
        System.out.println("当前请求执行："+targetClass.getSimpleName()+"@"+targetMethod.getName());
        System.out.println(".");
        System.out.println("...");
        System.out.println(".....");
    }

    @Override
    public void after(Class<?> targetClass, Method targetMethod, Object[] methodParams,Object targetObject) {
        //创建actionLog对象
        ActionLog actionLog = new ActionLog();
        actionLog.setActionClass(targetClass.getSimpleName());
        actionLog.setActionMethod(targetMethod.getName());
        actionLog.setRequestType(DataContext.getDataContext().getRequest().getMethod());
        actionLog.setRequestPath(DataContext.getDataContext().getRequest().getRequestURI());
        actionLog.setExecuteTime((int)(System.currentTimeMillis()-currentTimeMillis));
        actionLog.setIsSuccess("执行成功");
        //存入数据库
        actionLogService.insertLog(actionLog);
        System.out.println("方法执行完毕！总计耗时："+(System.currentTimeMillis()-currentTimeMillis)+"ms");
    }

    @Override
    public void error(Class<?> targetClass, Method targetMethod, Object[] methodParams,Object targetObject, Exception e) {
        //创建actionLog对象
        ActionLog actionLog = new ActionLog();
        actionLog.setActionClass(targetClass.getSimpleName());
        actionLog.setActionMethod(targetMethod.getName());
        actionLog.setRequestType(DataContext.getDataContext().getRequest().getMethod());
        actionLog.setRequestPath(DataContext.getDataContext().getRequest().getRequestURI());
        actionLog.setExecuteTime((int)(System.currentTimeMillis()-currentTimeMillis));
        actionLog.setIsSuccess("执行失败");
        //存入数据库
        actionLogService.insertLog(actionLog);
        System.out.println("方法执行异常！总计耗时："+(System.currentTimeMillis()-currentTimeMillis)+"ms，"+e);
    }

    @Override
    public void end() {

    }

    @Override
    public boolean filter(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
        if(targetMethod.getName().equals("getModel")
                ||targetMethod.getName().equals("finalize")){
            return false;
        }
        return true;
    }
}
