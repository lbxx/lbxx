<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ex" uri="tag" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 引入公共js css -->
    <jsp:include page="../jscss.jsp" />
    <link href="http://www.jq22.com/jquery/bootstrap-3.3.4.css" rel="stylesheet">
</head>
<body>

<!-- 引入顶部 -->
<jsp:include page="../header.jsp"/>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <div class="main-container-inner">
        <!-- 这个是箭头 -->
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>
        <!-- 引入左边菜单 -->
        <jsp:include page="../lefter.jsp" />
        <!-- 右边内容开始 -->
        <div class="main-content">
            <!-- 当前页定位开始 -->
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li>
                        <a href="#">知识库</a>
                    </li>
                    <li class="active">编辑/新增</li>
                </ul><!-- .breadcrumb -->
            </div>
            <!-- 当前页定位结束 -->
            <!-- 右边内容开始 -->
            <div class="page-content">
                <!-- 内容开始区域 -->
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <form class="form-horizontal" id="validation-form" role="form" method="post" enctype="multipart/form-data" action="${ctx}/knowledge/save">
                            <input type="hidden" name="id" value="${dto.id}"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="title"> 标题 </label>
                                <div class="col-sm-8">
                                    <input type="text" id="title" name="title" value="${dto.title}" maxlength="30" required class="col-xs-4 col-sm-4" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" required for="typeid"> 分类 </label>
                                <div class="col-sm-9">
                                    <select id="typeid" name="typeid">
                                        <c:forEach items="${typeList}" var="item">
                                            <option value="${item.id}" <c:if test="${dto.typeid == item.id}">selected</c:if>>${item.typename}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">导航图片</label>
                                <input type="file" onchange="previewImage(this)" name="file" accept="image/*" style="margin: 0 0 0 228px;" 
                                <c:if test='${dto.id == null}'>required</c:if>
                                />                             
                            </div>
                            <div id="preview"
                                    style="width: 40%; height: 100%;margin:0 0 0 228px;">
                                    <img alt="导航图片" id="storeimg"
                                        <%-- src="${ctx}/resources/images/default.jpg"  --%>
                                        <%-- src="<ex:imageTag key='${imgUrl}'/>" width="100%" --%>
                                        <c:choose>
                                            <c:when test='${imgUrl == null}'>
										              src="${ctx}/resources/images/default.jpg"
										       </c:when>
										       <c:otherwise>
										        src="<ex:imageTag key='${imgUrl}'/>"      
										       </c:otherwise>
                                        </c:choose>
                                        width="100%" height="300px;" >
                                </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> 内容 </label>
                                <div class="col-sm-8">
                                        <script id="content" type="text/plain" style="width:924px;height:500px;" name="content"></script>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="sort"> 排序 </label>
                                <div class="col-sm-8">
                                    <input type="text" id="sort" name="sort" value="${dto.sort}" maxlength="2" required class="col-xs-4 col-sm-4" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> 状态 </label>
                                <div class="col-sm-8">
                                    <label class="inline">
                                        <input type="radio" value="true" required <c:if test="${dto.state == true}">checked</c:if> class="ace" name="state">
                                        <span class="lbl">启用</span>
                                    </label>
                                    <label class="inline">
                                        <input type="radio" value="false" required <c:if test="${dto.state == false}">checked</c:if> class="ace" name="state">
                                        <span class="lbl">禁用</span>
                                    </label>
                                </div>
                            </div>
                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">
                                    <button class="btn btn-info" type="submit">
                                        <i class="icon-ok bigger-110"></i>
                                        提交
                                    </button>
                                    &nbsp; &nbsp; &nbsp;
                                    <button class="btn" type="reset">
                                        <i class="icon-undo bigger-110"></i>
                                        重置
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div><!-- /.col -->
                </div>
                <!-- 内容结束 -->

            </div><!-- /.page-content -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container-inner -->
</div><!-- /.main-container -->
<jsp:include page="../footer.jsp"></jsp:include>
<!-- 引入顶部公共js -->
<jsp:include page="../bottomjs.jsp" />
<!-- 自定义js -->
<script src="${ctx}/resources/js/jquery.validate.min.js"></script>
<script src="${ctx}/resources/js/messages_zh.js"></script>
<script src="${ctx}/resources/ueditor/ueditor.config.js"></script>
<script src="${ctx}/resources/ueditor/ueditor.all.min.js"></script>
<script src="${ctx}/resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet" href="${ctx}/resources/ueditor/themes/default/css/ueditor.css">
<script>
    $(function(){
        $('#validation-form').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            rules: {
                sort:{
                    required:true,
                    number:true
                }
            },
            messages: {
                sort:{
                    required: "请输入排序",
                    number:"数字"
                }
            },
            submitHandler: function (form) {
                //
                $(form).ajaxSubmit({
                    success: function (data) {
                       if(data.ok){
                           $.scojs_message(data.msg, $.scojs_message.TYPE_OK);
                           window.location.assign("${ctx}/knowledge/list");
                       }else{
                           $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
                       }
                    },error:function (data) {
                        $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
                    }
                });
            },
            invalidHandler: function (event, validator) { //display error alert on form submit
            },
            highlight: function (e) {
                // 验证不通过，加入css   .form-group就是每个元素的div的class
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                // 验证成功后，将css 删除掉
                $(e).closest('.form-group').removeClass('has-error').addClass('has-info');
                $(e).remove();
                // 验证通过，它会自己提交form的action
            }
        });

    })
    
        //页面图上上传预览//
        //图片上传预览    IE是用了滤镜。
        function previewImage(file)
        {
          var MAXWIDTH  = 382; 
          var MAXHEIGHT = 350;
          var div = document.getElementById('preview');
          if (file.files && file.files[0])
          {
              div.innerHTML ='<img id=storeimg>';
              var img = document.getElementById('storeimg');
              img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
                //img.style.marginTop = rect.top+'px';
              }
              var reader = new FileReader();
              reader.onload = function(evt){img.src = evt.target.result;}
              reader.readAsDataURL(file.files[0]);
          }
          else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=storeimg>';
            var img = document.getElementById('storeimg');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
          }
        }
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                 
                if( rateWidth > rateHeight )
                {
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else
                {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
             
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }
      //页面图上上传预览//
</script>
<script type="text/javascript">
 var content = '${dto.content}';
//实例化编辑器
var ue = UE.getEditor('content');
 ue.ready(function() {
     //设置编辑器的内容
     ue.setContent(content);
 });
</script>
</body>
</html>