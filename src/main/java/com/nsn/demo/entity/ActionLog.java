package com.nsn.demo.entity;

import com.nsn.quick4j.orm.annotation.Entity;
import com.nsn.quick4j.orm.annotation.PKey;
import com.nsn.quick4j.orm.annotation.Table;

/**
 * 控制器log
 * @author donghao
 * @since 1.0
 */
@Entity
@Table("action_log")
public class ActionLog {

    @PKey("id")
    private int id;//主键
    private String actionClass;//请求类名
    private String actionMethod;//请求方法名
    private String requestType;//请求方式
    private String requestPath;//请求路径
    private int executeTime;//执行时间
    private String isSuccess;//是否执行成功

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getActionClass() {
        return actionClass;
    }

    public void setActionClass(String actionClass) {
        this.actionClass = actionClass;
    }

    public String getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(String actionMethod) {
        this.actionMethod = actionMethod;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public int getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(int executeTime) {
        this.executeTime = executeTime;
    }
}
