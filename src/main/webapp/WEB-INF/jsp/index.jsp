<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="BASE" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html>
 <head>
  <title> Quick4j 演示Demo</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="${BASE}/asset/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="${BASE}/asset/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="${BASE}/asset/bui/css/main.css" rel="stylesheet" type="text/css" />
 </head>
 <body>
   <div class="header">
    <div class="dl-title"><span class="">Quick4j 演示Demo</span></div>

   </div>
   <div class="content">
    <div class="dl-main-nav">
      <ul id="J_Nav"  class="nav-list ks-clear">
        <li class="nav-item dl-selected"><div class="nav-item-inner nav-storage">Quick4j mvc</div></li>
        <li class="nav-item"><div class="nav-item-inner nav-storage">演示案例</div></li>
      </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>

   </div>
  <script type="text/javascript" src="${BASE}/asset/bui/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/config-min.js"></script>
  <script>
     BUI.use('common/main',function(){
      var config = [
            {
                id:'process',
                menu:[
                    {
                      text:'process',
                      items:[
                        {text:'处理流程',href:'${BASE}/asset/quick4j/mvc/1.html'},
                      ]
                    }
                ]
            },
            {
                id:'demo',
                menu:[
                    {
                      text:'学生管理',
                      items:[
                        {id:'main-menu',text:'学生管理',href:'${BASE}/student/studentManage'},
                      ]
                    },
                    {
                      text:'班级管理',
                      items:[
                        {text:'添加班级',href:'${BASE}/stuClass/getAddStuClassPage'},
                        {text:'查看班级',href:'${BASE}/stuClass/stuClassManage'}
                      ]
                    },
                    {
                      text:'Action日志',
                      items:[
                        {text:'Action日志',href:'${BASE}/actionLog/getActionLogHome'}
                      ]
                    },
                    {
                      text:'MVC',
                      items:[
                        {text:'控制器监控',href:'${BASE}/monitor/controllerHome'}
                      ]
                    },
                    {
                      text:'IOC',
                      items:[
                        {text:'IOC容器监控',href:'${BASE}/monitor/iocHome'}
                      ]
                    },
                    {
                      text:'Aop',
                      items:[
                        {text:'Aop容器监控',href:'${BASE}/monitor/aopHome'}
                      ]
                    },
                    {
                      text:'ORM',
                      items:[
                        {text:'ORM容器监控',href:'${BASE}/monitor/ormHome'}
                      ]
                    }
                ]
            }
          ];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });

  </script>
 </body>
</html>

       