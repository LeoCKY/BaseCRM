<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>組織管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;height:400px;overflow: auto;">
           <div class="layui-form-item">
               <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                   <legend style="font-size:16px;">組織/部門信息</legend>
               </fieldset>
           </div>
               <div class="layui-form-item">
                   <label for="name" class="layui-form-label">
                       <span class="x-red">*</span>名稱
                   </label>
                   <div class="layui-input-inline">
                       <input type="text"  value="${sysOrg.name}" id="name" name="name"  lay-verify="name"
                              autocomplete="off" class="layui-input">
                   </div>
                   <div id="ms" class="layui-form-mid layui-word-aux">
                       <span class="x-red">*</span><span id="ums">必須填寫</span>
                   </div>
               </div>

               <div class="layui-form-item">
                   <label for="description" class="layui-form-label">
                       敘述
                   </label>
                   <div class="layui-input-block">
                       <input type="text" id="description"  value="${sysOrg.description}" style="width: 93%" name="description"  lay-verify="description"
                              autocomplete="off" class="layui-input">
                   </div>
               </div>

               <div class="layui-form-item">
                   <label for="orgType" class="layui-form-label">
                       <span class="x-red">*</span>類型
                   </label>
                   <div class="layui-input-block" style="width:190px;">
                       <select  disabled id="orgType" lay-verify="orgType"  lay-filter="orgType">
                           <option <#if sysOrg.type == '1'>selected</#if> value="1">總公司</option>
                           <option <#if sysOrg.type == '2'>selected</#if> value="2">分公司</option>
                           <option <#if sysOrg.type == '3'>selected</#if> value="3">部門</option>
                       </select>
                   </div>
               </div>

               <div class="layui-form-item" id="pDiv">
                   <label for="pName" class="layui-form-label">
                       父級組織
                   </label>
                   <div class="layui-input-inline">
                       <#--保留 但不做更新-->
                       <input type="text" id="pName" disabled value="${pName}" onclick="showTree();"  lay-verify="pName"
                              class="layui-input">
                   </div>
                   <div id="treeNode"  style="display:none; position: absolute;z-index:1000;background-color: white;">
                       <div id="tree"></div>
                   </div>
               </div>
           </div>

        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button  class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
                    更新
                </button>
                <button  class="layui-btn layui-btn-primary"  id="close">
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
        layui.tree({
            elem:'#tree',
            nodes:${orgs}
            ,click: function(node){
                if(node.orgType=='1'){
                    layer.msg('請勿選擇按鈕', {icon: 5,anim:6});
                    return false;
                }
                $('#pId').val(node.id);
                $('#pName').val(node.name);
            }
        });


        //自定義驗證規則
        var type=$('#orgType');
        form.verify({
            orgType:function(v){
                if( v== ''){
                    return '類型不能為空';
                }
            }
            ,pName:function(v){
                if(type.val() != '1' && v.trim() == ''){
                    return '父菜單不能為空';
                }
            }
            ,name:function(v){
                if(v.trim()==''){
                    return '名稱不能為空';
                }
            }
        });

        form.on('select(orgType)', function(data){
            if(data.value=='1'){
                $('#pId').val('');
                dOs('pName',true);
            } else {
                dOs('pName',false);
            }
        });

        /**
         * id :元素id
         * flag true:禁止輸入，false 允許輸入
         */
        function dOs(id,flag){
            var $id= $("#"+id);
            if(flag){
                $id.val('');
                $id.attr('disabled','disabled').css('background','#e6e6e6');
            }
            else
                $id.removeAttr('disabled').css('background','white')
        }

        $('#close').click(function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            return false;
        });

        //監聽提交
        form.on('submit(add)', function(data){
            data.field['icon']=$('#icon').text();
            data.field['id']='${sysOrg.id}';
            $.ajax({
                url:'updateOrg',
                type:'post',
                data:data.field,
                async:false, dataType: "json", traditional: true,
                success:function(data){
                    var index = parent.layer.getFrameIndex(window.name);
                    window.top.layer.msg(data.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                    parent.layer.close(index);
                    parent.location.replace(parent.location.href);
                },error:function(){
                    var index = parent.layer.getFrameIndex(window.name);
                    window.top.layer.msg('請求失敗',{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    parent.layer.close(index);
                }
            });
            return false;
        });
        form.render();
    });

    function showTree(){
        var p=$('#pName');
        var cityObj = p;
        var cityOffset =p.offset();
        var width=p.css('width');
        $('#treeNode').css({
            left: cityOffset.left + 'px',
            top: cityOffset.top + cityObj.outerHeight() + 'px',
            width:width,
            border:'1px solid #e6e6e6'
        }).slideDown('fast');
        $('body').bind('mousedown', onBodyDown);
        $('#treeNode').css('display','inline');
    }

    function hideOrg() {
        $('#treeNode').fadeOut('fast');
        $('body').unbind('blur', onBodyDown);
    }

    function onBodyDown(event) {
        if (! ( event.target.id == 'treeNode' || $(event.target).parents('#treeNode').length > 0)) {
            hideOrg();
        }
    }
</script>
</body>

</html>