<#--Created by IntelliJ IDEA.
To change this template use File | Settings | File Templates.-->

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>添加角色</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
  <link rel="stylesheet" href="${re.contextPath}/plugin/ztree/css/metroStyle/metroStyle.css">
  <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/ztree/js/jquery.ztree.core.js"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/ztree/js/jquery.ztree.excheck.js" charset="utf-8"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
    <script  type="text/javascript">
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        var zNodes =${menus};
        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
    </script>
</head>

<body>
<div class="x-body">
  <form class="layui-form layui-form-pane" style="margin-left: 20px;">
    <div style="width:100%;height:400px;overflow: auto;">
    <div class="layui-form-item">
      <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
        <legend style="font-size:16px;">角色信息</legend>
      </fieldset>
    </div>
    <div class="layui-form-item">
      <label for="name" class="layui-form-label">
        <span class="x-red">*</span>角色名称
      </label>
      <div class="layui-input-inline">
        <input type="text"  id="name" name="name"  lay-verify="name"
               autocomplete="off" class="layui-input">
      </div>
      <div id="ms" class="layui-form-mid layui-word-aux">
        <span class="x-red">*</span><span id="ums">角色名称必填</span>
      </div>
    </div>
    <div class="layui-form-item">
    <div class="layui-inline">
      <label for="description" class="layui-form-label">
        <span class="x-red">*</span>角色备注
      </label>
      <div class="layui-input-inline">
        <input type="text" id="description" name="description" lay-verify="description"  autocomplete="off" class="layui-input">
      </div>
    </div>
    </div>
        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">权限</legend>
            </fieldset>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label for="description" class="layui-form-label">
                    <span class="x-red">*</span>权限选择
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" name="menus">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
      <div style="height: 60px"></div>
    </div>
  <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
    <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">

      <button  class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
        增加
      </button>
      <button  class="layui-btn layui-btn-primary" id="close">
        取消
      </button>
    </div>
  </div>
  </form>
</div>
<script>
  layui.use(['form','layer'], function(){
    $ = layui.jquery;
    var form = layui.form
        ,layer = layui.layer;

    //自定义验证规则
    form.verify({
      name: function(value){
        if(value.trim()==""){
          return "角色名不能为空";
        }
      }
    });

   $('#close').click(function(){
     var index = parent.layer.getFrameIndex(window.name);
     parent.layer.close(index);
   });
    //监听提交
    form.on('submit(add)', function(data){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
         var jsonArr= zTree.getCheckedNodes(true);
         var menus=[];
         for(var item in jsonArr){
             menus.push(jsonArr[item].id);
         }
        data.field.menus=menus;
      layerAjax('addRole',data.field,'roleList');
      return false;
    });
  });
</script>
</body>

</html>
