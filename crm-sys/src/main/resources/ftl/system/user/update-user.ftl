<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>用戶管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
  <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/tools/update-setting.js"></script>
</head>

<body>
<div class="x-body">
  <form class="layui-form layui-form-pane" style="margin-left: 20px;">
    <div style="width:100%;height:400px;overflow: auto;">
      <div class="layui-form-item">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
          <legend style="font-size:16px;">頭像上傳</legend>
        </fieldset>
        <div class="layui-input-inline">
          <div class="layui-upload-drag" style="margin-left:10%;" id="test10">
            <i style="font-size:30px;" class="layui-icon"></i>
            <p style="font-size: 10px">點擊上傳，或將文件拖拽到此處</p>
          </div>
        </div>
        <div class="layui-input-inline">
          <div id="demo2" style="margin-top: 20px;margin-left: 50px">
            <img src="/images/${re.contextPath}/${user.photo}" width="100px" height="100px" class="layui-upload-img layui-circle">
          </div>

        </div>
      </div>
      <div class="layui-form-item">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
          <legend style="font-size:16px;">基礎信息</legend>
        </fieldset>
      </div>
      <div class="layui-form-item">
        <label for="username" class="layui-form-label">
          <span class="x-red">*</span>用戶名
        </label>
        <div class="layui-input-inline">
          <input value="${user.uid}" type="hidden" name="uid">
          <input type="text"  id="username" value="${user.account}" readonly lay-verify="username"
                 autocomplete="off" class="layui-input">
        </div>
        <div id="ms" class="layui-form-mid layui-word-aux">
          <span class="x-red">*</span><span id="ums">將會成為您唯一的登入名</span>
        </div>
      </div>

      <div class="layui-form-item">
       <div class="layui-inline">
         <label for="lName" class="layui-form-label">
           <span class="x-red">*</span>姓
         </label>
         <!-- TODO fName 讀取不到 -->
         <div class="layui-input-inline">
           <input type="text" id="lName" name="fName" lay-verify="fName" value="${user.firstName}" autocomplete="off" class="layui-input">
         </div>
       </div>

       <div class="layui-inline">
         <label for="lName" class="layui-form-label">
            <span class="x-red">*</span>名
         </label>
         <div class="layui-input-inline">
           <input type="text" id="lName" name="lName" lay-verify="lName" value="${user.lastName}" autocomplete="off" class="layui-input">
      </div>

     </div>

      <div class="layui-form-item">
<#--        <div class="layui-inline">-->
<#--          <label for="birthday" class="layui-form-label">-->
<#--            <span class="x-red">*</span>出生日-->
<#--          </label>-->
<#--          <div class="layui-input-inline">-->
<#--            <input type="text" class="layui-input" id="birthday" name="birthday" placeholder="yyyy-MM-dd" value="${user.birthday}">-->
<#--          </div>-->
<#--        </div>-->

        <div class="layui-inline">
          <label for="idNum" class="layui-form-label">
            <span class="x-red">*</span>身分證號
          </label>
          <div class="layui-input-inline">
            <input type="text" id="idNum" name="idNum" lay-verify="idNum" value="${user.idNum}" autocomplete="off" class="layui-input">
          </div>
        </div>
      </div>

      <div class="layui-form-item">
        <label for="email" class="layui-form-label">
          <span class="x-red"></span>郵箱
        </label>
        <div class="layui-input-block">
          <input type="email" id="email" value="${user.email}" style="width: 93%" name="email"  lay-verify="email"
                 autocomplete="off" class="layui-input">
          <input id="photo" value="${user.photo}" name="photo" type="hidden">
        </div>
      </div>

      <div class="layui-form-item">
        <div class="layui-inline">
          <label for="countries" class="layui-form-label">
            <span class="x-red"></span>國家
          </label>
          <div class="layui-input-inline">
            <select name="countriesId" id="countries" lay-verify="countries"  lay-filter="countries">
              <option value="">請選擇</option>
            </select>
          </div>
        </div>

        <div class="layui-inline">
          <label for="states" class="layui-form-label">
            <span class="x-red"></span>省/區
          </label>
          <div class="layui-input-inline">
            <select name="statesId" id="states" lay-verify="states"  lay-filter="states">
              <option value="">請選擇</option>
            </select>
          </div>
        </div>
      </div>

      <div class="layui-form-item">
        <div class="layui-inline">
          <label for="cities" class="layui-form-label">
            <span class="x-red"></span>城市
          </label>
          <div class="layui-input-block">
            <select name="citiesId" id="cities" lay-verify="cities"  lay-filter="cities">
              <option value="">請選擇</option>
            </select>
          </div>
        </div>

        <div class="layui-inline">
          <label for="postcode" class="layui-form-label">
            <span class="x-red">*</span>郵遞區號
          </label>
          <div class="layui-input-inline">
            <input type="text" id="postcode" name="postcode" lay-verify="postcode"  autocomplete="off" class="layui-input" value="${user.postcode}">
          </div>
        </div>
      </div>

      <div class="layui-form-item">
        <div class="layui-inline">
          <label for="address" class="layui-form-label">
            <span class="x-red">*</span>地址
          </label>
          <div class="layui-input-block">
            <input type="text" id="address" name="address" lay-verify="address"  autocomplete="off" class="layui-input" value="${user.address}">
          </div>
        </div>
      </div>


      <div class="layui-form-item">
        <label class="layui-form-label">角色選擇</label>
        <div class="layui-input-block">
          <#list boxJson as json>
            <input type="checkbox" name="role" lay-filter="check" value="${json.id}" title="${json.name}" <#if json.check?string=='true'>checked</#if>>
          </#list>
        </div>
      </div>
      <div style="height: 60px"></div>
    </div>
    <#if !detail>
      <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
      position: fixed;bottom: 1px;margin-left:-20px;">
        <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
          <button  class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
            確定
          </button>
          <button  class="layui-btn layui-btn-primary" id="close">
            取消
          </button>

        </div>
      </div>
    </#if>
  </form>
</div>
<script>

  layui.use(['form','layer','upload'], function(){
    $ = layui.jquery;
    var form = layui.form
            ,layer = layui.layer,
            upload = layui.upload,
            laydate = layui.laydate;
    upload.render({
      elem: '#test10'
      ,url: 'upload'
      ,before: function(obj){
        //預讀，不支持ie8
        obj.preview(function(index, file, result){
          $('#demo2').find('img').remove();
          $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" width="100px" height="100px" class="layui-upload-img layui-circle">');
        });
      },done: function(res){
        if(!res.flag){
          layer.msg(res.msg,{icon: 5,anim: 6});
        }else{
          console.log(res);
          $("#photo").val(res.msg);
          console.info($('#photo').val());
        }
      }
    });

    // 出生日
     laydate.render({
       elem: '#birthday'
       ,lang: 'en'
       ,type: 'datetime'
       ,zIndex: 99999999
     });

    //自定義驗證規則
    form.verify({
      email:function(value){
        if(value!=""){
          if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(value)){
            return "郵箱格式不正確";
          }
        }
      }
    });

    $('#close').click(function(){
      var index = parent.layer.getFrameIndex(window.name);
      parent.layer.close(index);
    });
    //監聽提交
    form.on('submit(add)', function(data){
      var r=document.getElementsByName("role");
      var role=[];
      for(var i=0;i<r.length;i++){
        if(r[i].checked){
          console.info(r[i].value);
          role.push(r[i].value);
        }
      }
      data.field.role=role;
      layerAjax('updateUser', data.field, 'userList');
      return false;
    });

    $.ajax({
      url: '/place/countries', async: false, type: 'get', success: function (data) {
        if (data.flag) {
          var countries = data.data;
          for(var k in countries) {
            $('#countries').append($('<option>', {
              value: countries[k].id,
              text: countries[k].name
            }));
          }
          form.render('select');//  註意：數據賦值完成之後必須調用該方法，進行layui的渲染，否則數據出不來
        }
      },beforeSend:function(){
      }
    });

    var countriesId = '${user.countriesId}';
    var statesId = '${user.statesId}';
    var citiesId = '${user.citiesId}';

    if(countriesId != ''){
      $.ajax({
        url: '/place/states/'+countriesId, async: false, type: 'get', success: function (data) {
          if (data.flag) {
            var states = data.data;
            for(var k in states) {
              $('#states').append($('<option>', {
                value: states[k].id,
                text: states[k].name
              }));
            }
            form.render('select');//  註意：數據賦值完成之後必須調用該方法，進行layui的渲染，否則數據出不來
          }
        },beforeSend:function(){
        }
      });

      if(statesId!=''){
        $.ajax({
          url:  '/place/cities/' + countriesId + '/' + statesId , async: false, type: 'get', success: function (data) {
            if (data.flag) {
              var cities = data.data;
              for(var k in cities) {
                $('#cities').append($('<option>', {
                  value: cities[k].id,
                  text: cities[k].name
                }));
              }
              form.render('select');//  註意：數據賦值完成之後必須調用該方法，進行layui的渲染，否則數據出不來
            }
          },beforeSend:function(){
          }
        });
      }
    }

    if(countriesId != null && countriesId != ''){
      $('#countries option[value=${user.countriesId}]').attr('selected', 'selected');
    }

    if(statesId != null && statesId != ''){
      $('#states option[value=${user.statesId}]').attr('selected', 'selected');
    }

    if(citiesId != null && citiesId != ''){
      $('#cities option[value=${user.citiesId}]').attr('selected', 'selected');
    }

    form.on('select(countries)', function(data){
      $('#states').empty();
      $('#cities').empty();
      $('#states').append($('<option>', {
        value: '',
        text: '請選擇'
      }));
      $('#cities').append($('<option>', {
        value: '',
        text: '請選擇'
      }));
      form.render('select');//select是固定寫法 不是選擇器
      if(data.value != ''){
        $.ajax({
          url: '/place/states/'+data.value, async: false, type: 'get', success: function (data) {
            if (data.flag) {
              var states = data.data;
              for(var k in states) {
                $('#states').append($('<option>', {
                  value: states[k].id,
                  text: states[k].name
                }));
              }
              form.render('select');//  註意：數據賦值完成之後必須調用該方法，進行layui的渲染，否則數據出不來
            }
          },beforeSend:function(){
          }
        });
      }
    });

    form.on('select(states)', function(data){
      $('#cities').empty();
      $('#cities').append($('<option>', {
        value: '',
        text: '請選擇'
      }));
      form.render('select');//select是固定寫法 不是選擇器
      if(data.value != ''){
        var countriesId = $('#countries').val();
        var statesId = data.value;
        var citiesUrl = '/place/cities/' + countriesId + '/' + statesId;
        $.ajax({
          url:  citiesUrl, async: false, type: 'get', success: function (data) {
            if (data.flag) {
              var cities = data.data;
              for(var k in cities) {
                $('#cities').append($('<option>', {
                  value: cities[k].id,
                  text: cities[k].name
                }));
              }
              form.render('select');//  註意：數據賦值完成之後必須調用該方法，進行layui的渲染，否則數據出不來
            }
          },beforeSend:function(){
          }
        });
      }
    });


    var flag='${detail}';
    if(flag){
      $("form").disable();
    }
    form.render();
  });

  $(document).ready(function() {

  });
</script>
</body>

</html>
