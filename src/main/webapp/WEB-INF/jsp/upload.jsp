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
  <div id="grid">
        <form action="${BASE}/fileUpload/uploadFile" enctype="multipart/form-data" method="POST">
            <input type="text" name="name"/>
            <input type="text" name="user.age"/>
            <input type="file" name="file"/>
            <input type="submit"/>
        </form>
  </div>


  <script type="text/javascript" src="${BASE}/asset/bui/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/config-min.js"></script>



<body>
</html>  
