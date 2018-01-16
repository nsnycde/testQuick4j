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
    <link href="${BASE}/asset/bui/css/page-min.css" rel="stylesheet" type="text/css" />   <!-- 下面的样式，仅是为了显示代码，而不应该在项目中使用-->
   <link href="${BASE}/asset/bui/css/prettify.css" rel="stylesheet" type="text/css" />
   <style type="text/css">
    code {
      padding: 0px 4px;
      color: #d14;
      background-color: #f7f7f9;
      border: 1px solid #e1e1e8;
    }
   </style>
 </head>
 <body>
  
  <div class="container">
    <div class="row">
      <form id="searchForm" class="form-horizontal span24">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label">学生姓名：</label>
            <div class="controls">
              <input type="text" class="control-text" name="queryName">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">性别：</label>
            <div class="controls" >
              <select value="-1" name="querySex">
                <option value="-1">请选择</option>
                <option value="1">男</option>
                <option value="0">女</option>
              </select>
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label">学生班级：</label>
            <div class="controls">
                <select name="classId" id="classList">
                  <option value="">请选择</option>
                </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="control-group span9">
            <label class="control-label">学生生日：</label>
            <div class="controls">
              <input type="text" class=" calendar" name="startDate"><span> - </span><input name="endDate" type="text" class=" calendar">
            </div>
          </div>
          <div class="span3 offset2">
            <button  type="button" id="btnSearch" class="button button-primary">搜索</button>
          </div>
        </div>
      </form>
    </div>
    <div class="search-grid-container">
      <div id="grid"></div>
    </div>

  </div>
  <div id="content" class="hide">
      <form id="J_Form" class="form-horizontal" action="${BASE}/user/updateUser?a=1">
        <input type="hidden" name="a" value="3">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>学生姓名</label>
            <div class="controls">
              <input name="name" type="text" data-rules="{required:true}" class="input-normal control-text">
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label"><s>*</s>学生性别：</label>
            <div class="controls">
              <select  data-rules="{required:true}"  name="sex" class="input-normal">
                <option value="">请选择</option>
                <option value="1">男</option>
                <option value="0">女</option>
              </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>学生班级</label>
            <div class="controls">
                <select  data-rules="{required:true}"  name="classId" class="input-normal" id="stuClassList">
                  <option value="">请选择</option>
                </select>
            </div>
          </div>
          <div class="control-group span8">
            <label class="control-label"><s>*</s>学生生日</label>
            <div class="controls">
              <input name="birthday" type="text" data-rules="{required:true}" class="calendar">
            </div>
          </div>
          
        </div>
        <div class="row">
          <div class="control-group span15">
            <label class="control-label">学生住址</label>
            <div class="controls control-row4">
              <textarea name="address" class="input-large" type="text"></textarea>
            </div>
          </div>
        </div>
      </form>
    </div>
  <script type="text/javascript" src="${BASE}/asset/bui/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/config-min.js"></script>
  <script type="text/javascript">
    BUI.use('common/page');
  </script>
<script type="text/javascript">

  $.post("${BASE}/stuClass/getClassList", function(data){
        for(var i=0;i<data.length;i++){
            $("#stuClassList").append("<option value='"+data[i].cid+"'>"+data[i].className+"</option>");
            $("#classList").append("<option value='"+data[i].cid+"'>"+data[i].className+"</option>");
        }


  },"json");

  BUI.use('common/search',function (Search) {
    
      var enumSex = {"1":"男","0":"女"},
      editing = new BUI.Grid.Plugins.DialogEditing({
        contentId : 'content', //设置隐藏的Dialog内容
        autoSave : true, //添加数据或者修改数据时，自动保存
        triggerCls : 'btn-edit'
      }),
      columns = [
          {title:'学生编号',dataIndex:'id',width:80},
          {title:'学生姓名',dataIndex:'name',width:120},
          {title:'学生生日',dataIndex:'birthday',width:200,renderer:BUI.Grid.Format.dateRenderer},
          {title:'学生性别',dataIndex:'sex',width:100,renderer:BUI.Grid.Format.enumRenderer(enumSex)},
          {title:'学生班级',dataIndex:'className',width:120},
          {title:'学生家庭住址',dataIndex:'address',width:300},
          {title:'操作',dataIndex:'',width:100,renderer : function(value,obj){
              editStr = '<span class="grid-command btn-edit" title="编辑学生信息">编辑</span>',
              delStr = '<span class="grid-command btn-del" title="删除学生信息">删除</span>';//页面操作不需要使用Search.createLink
              return editStr + delStr;
          }}
        ],
      store = Search.createStore('${BASE}/student/listStudent',{
        proxy : {
          save : { //也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
            addUrl : '${BASE}/student/addStudent',
            updateUrl : '${BASE}/student/updateStudent',
            removeUrl : '${BASE}/student/deleteStudent'
          },
          method : 'POST',
          limitParam : 'page.pageSize', //一页多少条记录
          startParam : 'page.startNum' //起始记录
        },
        autoSync : true, //保存数据后，自动更新
        pageSize : 5
      }),
      gridCfg = Search.createGridCfg(columns,{
        tbar : {
          items : [
            {text : '<i class="icon-plus"></i>新建',btnCls : 'button button-small',handler:addFunction},
            {text : '<i class="icon-remove"></i>删除',btnCls : 'button button-small',handler : delFunction}
          ]
        },
        plugins : [editing,BUI.Grid.Plugins.CheckSelection,BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
      });

      var search = new Search({
        store : store,
        gridCfg : gridCfg
      }),
      grid = search.get('grid');

    function addFunction(){
      var newData = {isNew : true}; //标志是新增加的记录
      editing.add(newData,'name'); //添加记录后，直接编辑
    }

    //删除操作
    function delFunction(){
      var selections = grid.getSelection();
      delItems(selections);
    }

    function delItems(items){
      var ids = [];
      BUI.each(items,function(item){
        ids.push(item.id);
      });

      if(ids.length){
        BUI.Message.Confirm('确认要删除选中的记录么？',function(){
          store.save('remove',{ids : ids});
        },'question');
      }
    }

    //监听事件，删除一条记录
    grid.on('cellclick',function(ev){
      var sender = $(ev.domTarget); //点击的Dom
      if(sender.hasClass('btn-del')){
        var record = ev.record;
        delItems([record]);
      }
    });
  });
</script>

<body>
</html>  
