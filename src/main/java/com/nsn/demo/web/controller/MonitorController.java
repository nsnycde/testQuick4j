package com.nsn.demo.web.controller;

import com.nsn.quick4j.aop.AopHelper;
import com.nsn.quick4j.aop.annotation.Aspect;
import com.nsn.quick4j.aop.proxy.IAspect;
import com.nsn.quick4j.aop.tx.annotation.Service;
import com.nsn.quick4j.ioc.BeanHelper;
import com.nsn.quick4j.mvc.ControllerHelper;
import com.nsn.quick4j.mvc.annotation.Action;
import com.nsn.quick4j.mvc.annotation.Controller;
import com.nsn.quick4j.mvc.bean.Data;
import com.nsn.quick4j.mvc.bean.Request;
import com.nsn.quick4j.mvc.bean.View;
import com.nsn.quick4j.mvc.handler.Handler;
import com.nsn.quick4j.orm.EntityHelper;

import java.util.*;

/**
 * 用于获取Quick4j各容器数据
 * @author donghao
 * @since 1.0
 */
@Controller
public class MonitorController {


    @Action("GET:/monitor/controllerHome")
    public Object controllerHome(){
        return new View("monitor/controllerInfo.jsp");
    }
    @Action("POST:/monitor/getControllerInfo")
    public Object getControllerInfo(){
        //获取action容器
        Map<Request, Handler> actionMap = ControllerHelper.getActionMap();
        //声明map
        Map<String,List<Map<String,Object>>> clsDataMap = new HashMap<String, List<Map<String, Object>>>();
        //遍历
        for(Map.Entry<Request, Handler> entry : actionMap.entrySet()){
            Request request = entry.getKey();
            Handler handler = entry.getValue();
            List<Map<String,Object>> list;
            String className = handler.getControllerClass().getSimpleName();
            //判断当前key是否存在
            if(!clsDataMap.containsKey(className)){
                list = new ArrayList<Map<String, Object>>();
                clsDataMap.put(className,list);
            }else{
                list = clsDataMap.get(className);
            }
            //放入map
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("methodName",handler.getActionMethod().getName());
            map.put("requestType",request.getRequestMethod());
            map.put("urlPath",request.getRequestPath());
            list.add(map);
        }
        Data data = new Data(clsDataMap);
        return data;
    }

    @Action("GET:/monitor/aopHome")
    public Object aopHome(){
        return new View("monitor/aopInfo.jsp");
    }
    @Action("POST:/monitor/getAopInfo")
    public Object getAopInfo(){
        //获取aop容器
        Map<Class<?>, List<IAspect>> classAspectListMap = AopHelper.getClassAspectListMap();
        //声明map
        Map<String,List<Map<String,Object>>> aspectDataMap = new HashMap<String, List<Map<String, Object>>>();
        //遍历
        for(Map.Entry<Class<?>, List<IAspect>> entry : classAspectListMap.entrySet()){
            Class<?> cls = entry.getKey();
            List<IAspect> aspectList = entry.getValue();
            for(IAspect aspect : aspectList){
                String key = "";
                String aspectName = aspect.getClass().getSimpleName()+"(单实例，已创建)";
                key += aspectName+"@切入配置:@";
                Aspect annoAspect = aspect.getClass().getAnnotation(Aspect.class);
                key += "packagePath:"+annoAspect.pkPath()+"@";
                String annoTypes = "[";
                if(annoAspect.annoType().length>0){
                    for(Class<?> annoClass : annoAspect.annoType()){
                        annoTypes += annoClass.getSimpleName()+", ";
                    }
                    annoTypes = annoTypes.substring(0,annoTypes.length()-2);
                }
                annoTypes += "]";
                key += "annotationTypes:"+ annoTypes+"@";
                String classTypes = "[";
                if(annoAspect.clsArray().length>0){
                    for(Class<?> clz : annoAspect.clsArray()){
                        classTypes += clz.getSimpleName()+", ";
                    }
                    classTypes = classTypes.substring(0,classTypes.length()-2);
                }
                classTypes += "]";
                key += "classTypes:"+classTypes;
                List<Map<String,Object>> list;
                //判断当前key是否存在
                if(!aspectDataMap.containsKey(key)){
                    list = new ArrayList<Map<String, Object>>();
                    aspectDataMap.put(key,list);
                }else{
                    list = aspectDataMap.get(key);
                }
                //放入map
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("className",cls.getSimpleName());
                String classType = null;
                if(cls.isAnnotationPresent(Controller.class)){
                    classType = "Controller类";
                }else if(cls.isAnnotationPresent(Service.class)){
                    classType = "Service类";
                }else{
                    classType = "Bean类";
                }
                map.put("classType",classType);
                map.put("isSingleton",cls.isAnnotationPresent(Controller.class)?"否":"是");
                map.put("isCreateInstance", cls.isAnnotationPresent(Controller.class)?"请求时创建（多例）":"已创建（单例）");
                list.add(map);
            }
        }
        Data data = new Data(aspectDataMap);
        return data;
    }

    @Action("GET:/monitor/iocHome")
    public Object iocHome(){
        return new View("monitor/iocInfo.jsp");
    }
    @Action("POST:/monitor/getIocInfo")
    public Object getIocInfo(){
        //获取bean容器
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        //声明map
        Map<String,List<Map<String,Object>>> typeDataMap = new HashMap<String, List<Map<String, Object>>>();
        //遍历
        for(Class<?> cls : beanMap.keySet()){
            List<Map<String,Object>> list;
            String classType = null;
            if(cls.isAnnotationPresent(Controller.class)){
                classType = "Controller类:";
            }else if(cls.isAnnotationPresent(Service.class)){
                classType = "Service类:";
            }else if(cls.isAnnotationPresent(Aspect.class)){
                classType = "Aspect类:";
            }else{
                classType = "Bean类:";
            }
            //判断当前key是否存在
            if(!typeDataMap.containsKey(classType)){
                list = new ArrayList<Map<String, Object>>();
                typeDataMap.put(classType,list);
            }else{
                list = typeDataMap.get(classType);
            }
            //放入map
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("className",cls.getSimpleName());
            map.put("isSingleton",cls.isAnnotationPresent(Controller.class)?"否":"是");
            List<IAspect> aspectList = AopHelper.getAspectProxyList(cls);
            map.put("isProxy",aspectList==null?"否":"是");
            List<String> aspectNameList = null;
            if(aspectList != null){
                aspectNameList = new ArrayList<String>();
                for(IAspect aspect : aspectList){
                    aspectNameList.add(aspect.getClass().getSimpleName());
                }
            }
            map.put("aspectList",aspectList==null?"无":aspectNameList.toString());
            map.put("isCreateInstance", cls.isAnnotationPresent(Controller.class)?"请求时创建（多例）":"已创建（单例）");
            list.add(map);
        }
        Data data = new Data(typeDataMap);
        return data;
    }

    @Action("GET:/monitor/ormHome")
    public Object ormHome(){
        return new View("monitor/ormInfo.jsp");
    }
    @Action("POST:/monitor/getOrmInfo")
    public Object getOrmInfo(){
        //获取entity容器
        Map<Class<?>,Map<String,String>> entityTableMap = EntityHelper.getEntityTableMap();
        //声明map
        Map<String,List<Map<String,Object>>> entityDataMap = new HashMap<String, List<Map<String, Object>>>();
        //遍历
        for(Map.Entry<Class<?>,Map<String,String>> entry : entityTableMap.entrySet()){
            List<Map<String,Object>> list;
            Class<?> entityClass = entry.getKey();
            Map<String,String> fieldColumnMap = entry.getValue();
            //创建key
            String key = entityClass.getSimpleName();
            key += "@"+EntityHelper.getTableName(entityClass);
            //判断当前key是否存在
            if(!entityDataMap.containsKey(key)){
                list = new ArrayList<Map<String, Object>>();
                entityDataMap.put(key,list);
            }else {
                list = entityDataMap.get(entityDataMap);
            }
            //放入map
            for(Map.Entry<String,String> ecEntry : fieldColumnMap.entrySet()){
                String fieldName = ecEntry.getKey();
                String columnName = ecEntry.getValue();
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("fieldName",fieldName);
                map.put("columnName",columnName);
                map.put("isPrimaryKey",EntityHelper.getEntityPKey(entityClass).equals(fieldName)?"主键":"否");
                list.add(map);
            }
        }
        Data data = new Data(entityDataMap);
        return data;
    }
}
