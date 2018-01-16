<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
 <head>
  <title>班级管理</title>
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
    <div class="search-grid-container">
      <div id="grid"></div>
    </div>

  </div>
  <script type="text/javascript" src="${BASE}/asset/bui/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/bui-min.js"></script>
  <script type="text/javascript" src="${BASE}/asset/bui/js/config-min.js"></script>
  <script type="text/javascript">
    BUI.use('common/page');
  </script>
<script type="text/javascript">
  BUI.use('common/search',function (Search) {

      editing = new BUI.Grid.Plugins.DialogEditing({
        contentId : 'content', //设置隐藏的Dialog内容
        autoSave : true, //添加数据或者修改数据时，自动保存
        triggerCls : 'btn-edit'
      }),
      columns = [
          {title:'班级ID',dataIndex:'cid',width:250},
          {title:'班级名称',dataIndex:'className',width:200},
          {title:'班级图片',dataIndex:'imagePath',width:350},
        ],
      store = Search.createStore('${BASE}/stuClass/listStuClass',{
        proxy : {
          save : { //也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
            removeUrl : '${BASE}/stuClass/deleteStuClass'
          },
          method : 'POST',
          limitParam : 'page.pageSize', //一页多少条记录
          startParam : 'page.startNum' //起始记录
        },
        autoSync : true, //保存数据后，自动更新
        pageSize : 2
      }),
      gridCfg = Search.createGridCfg(columns,{
        tbar : {
          items : [

          ]
        },
        plugins : [editing,BUI.Grid.Plugins.CheckSelection,BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
      });

      var search = new Search({
        store : store,
        gridCfg : gridCfg
      }),
      grid = search.get('grid');

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
