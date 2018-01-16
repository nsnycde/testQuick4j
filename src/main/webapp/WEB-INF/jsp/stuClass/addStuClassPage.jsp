<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
 <head>
  <title>编辑</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
       <link href="${BASE}/asset/bui/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${BASE}/asset/bui/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${BASE}/asset/bui/css/page-min.css" rel="stylesheet" type="text/css" />
   <style type="text/css">
    div,h1,h3 {
      padding: 5px 15px;
    }
    #grid{
        padding-top:25px;
        padding-left:50px;
    }
   </style>
 </head>
 <body>
  <div id="grid">
        <form action="${BASE}/stuClass/addStuClass" enctype="multipart/form-data" method="POST">
          <div class="row">
            <div class="control-group span8">
              <label class="control-label"><s>*</s>班级名称:</label>
              <div class="controls">
                <input name="className" type="text" data-rules="{required:true}" class="input-normal control-text">
              </div>
            </div>
          </div>
          <div class="row">
            <div class="control-group span8">
              <label class="control-label"><s>*</s>班级图片：</label>
              <div class="controls">
                <input type="file" name="image"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="control-group span8">
              <div class="controls">
                <input type="submit" value="添加班级"/>
              </div>
            </div>
          </div>
        </div>

        </form>

  </div>


  <script type="text/javascript" src="${BASE}/asset/bui/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/config-min.js"></script>



<body>
</html>  
