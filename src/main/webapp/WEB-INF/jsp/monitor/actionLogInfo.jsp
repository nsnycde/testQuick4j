<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
 <head>
  <title> 搜索表单</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
       <link href="${BASE}/asset/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${BASE}/asset/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${BASE}/asset/bui/css/page-min.css" rel="stylesheet" type="text/css" />
   <style type="text/css">
    div,h1,h3 {
      padding: 5px 15px;
    }
   </style>
 </head>
 <body>
  <div id="grid"></div>
  <script type="text/javascript" src="${BASE}/asset/bui/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/config-min.js"></script>
<script type="text/javascript">
    BUI.use('common/page');
</script>
<script type="text/javascript">
  $.post("${BASE}/actionLog/getAll", function(data){
      $("#grid").append("<h1>Action日志</h1>");
      BUI.use('bui/grid',function (Grid) {

        grid = new Grid.SimpleGrid({
          render : '#grid', //显示Grid到此处
          width : 950,      //设置宽度
          columns : [
            {title:'id',dataIndex:'id',width:50},
            {title:'请求控制器',dataIndex:'actionClass',width:100},
            {title:'请求方法',dataIndex:'actionMethod',width:100},
            {title:'请求方式',dataIndex:'requestType',width:100},
            {title:'请求Url',dataIndex:'requestPath',width:200},
            {title:'执行时间/ms',dataIndex:'executeTime',width:100},
            {title:'是否成功',dataIndex:'isSuccess',width:100}
          ]
        });
        grid.render();
        grid.showData(data);
      });
  },"json");

</script>

<body>
</html>  
