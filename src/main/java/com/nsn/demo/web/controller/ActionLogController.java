package com.nsn.demo.web.controller;

import com.nsn.demo.entity.ActionLog;
import com.nsn.demo.service.ActionLogService;
import com.nsn.quick4j.ioc.annotation.Inject;
import com.nsn.quick4j.mvc.annotation.Action;
import com.nsn.quick4j.mvc.annotation.Controller;
import com.nsn.quick4j.mvc.bean.Data;
import com.nsn.quick4j.mvc.bean.View;

import java.util.List;

/**
 * action日志控制器
 * @author donghao
 * @since 1.0
 */
@Controller
public class ActionLogController {

    @Inject
    private ActionLogService actionLogService;

    @Action("GET:/actionLog/getActionLogHome")
    public Object getActionLogHome(){
        return new View("monitor/actionLogInfo.jsp");
    }

    @Action("POST:/actionLog/getAll")
    public Object getAll(){
        List<ActionLog> actionLogList = actionLogService.getAllLog();
        Data data = new Data(actionLogList);
        return data;
    }

    public ActionLogService getActionLogService() {
        return actionLogService;
    }

    public void setActionLogService(ActionLogService actionLogService) {
        this.actionLogService = actionLogService;
    }
}
