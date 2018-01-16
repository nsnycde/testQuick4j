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
  $.post("${BASE}/monitor/getOrmInfo", function(data){

      for (var key in data) {
          //alert(key);
          var gridData = data[key];
          var keyArray = key.split("@");
          $("#grid").append("<h1>实体:"+keyArray[0]+"&nbsp;&nbsp;&nbsp;映射&nbsp;&nbsp;&nbsp;表:"+keyArray[1]+"</h1>");
          BUI.use('bui/grid',function (Grid) {

            grid = new Grid.SimpleGrid({
              render : '#grid', //显示Grid到此处
              width : 950,      //设置宽度
              columns : [
                {title:'实体字段名',dataIndex:'fieldName',width:200},
                {title:'表列名',dataIndex:'columnName',width:200},
                {title:'是否主键',dataIndex:'isPrimaryKey',width:100},
              ]
            });
            grid.render();
            grid.showData(gridData);
          });
      }

  },"json");

</script>

<body>
</html>  
