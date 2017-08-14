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
                        <a href="#">图片</a>
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
                        <form class="form-horizontal" id="validation-form" role="form" 
                        method="post" action="${ctx}/image/save" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="${dto.id}"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" required for="knowledgeid"> 标题 </label>
                                <div class="col-sm-9">
                                    <select id="knowledgeid" name="knowledgeid">
                                        <c:forEach items="${knowledgeList}" var="item">
                                            <option value="${item.id}" <c:if test="${dto.knowledgeid == item.id}">selected</c:if>>${item.title}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="name"> 名称 </label>
                                <div class="col-sm-8">
                                    <input type="text" id="name" name="name" value="${dto.name}" maxlength="30" required class="col-xs-4 col-sm-4" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for=""> 图片 </label>
                                <div class="col-sm-8">
                                    <%-- <input type="text" id="source" name="source" value="${dto.source}" maxlength="30" required class="col-xs-4 col-sm-4" /> --%>
                                    <input type="file" onchange="previewImage(this)" name="file" accept="image/*" style=""
                                        <c:if test='${dto.id == null}'>
                                            required
                                        </c:if>
                                    />
                                </div>
                                <div id="preview" class="col-sm-3 control-label no-padding-right">
                                    <c:if test="${imgUrl == null}">
                                        <img alt="知识库图片" id="knowledgeimg" src="${ctx}/resources/images/default.jpg" width="100%" height="300px;"/>
                                    </c:if>
                                    <c:if test="${imgUrl != null}">
                                        <img id="knowledgeimg" src="${imgUrl}" width="100%" height="300px;"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="sort"> 排序 </label>
                                <div class="col-sm-8">
                                    <input type="text" id="sort" name="sort" value="${dto.sort}" maxlength="2" required class="col-xs-4 col-sm-4" />
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
                           window.location.assign("${ctx}/image/list");
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
        
        //页面加载渲染图片
        //loadImageInfo();

    })
    
    /*function loadImageInfo(){
    	var id = queryValueByKey("id");
    	if(id){
    		$('#knowledgeimg').attr('src',img_server+'${dto.source}');
    	}
    }*/
    //页面图上上传预览//
        //图片上传预览    IE是用了滤镜。
        function previewImage(file)
        {
          var MAXWIDTH  = 382; 
          var MAXHEIGHT = 350;
          var div = document.getElementById('preview');
          if (file.files && file.files[0])
          {
              div.innerHTML ='<img id=knowledgeimg>';
              var img = document.getElementById('knowledgeimg');
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
            div.innerHTML = '<img id=knowledgeimg>';
            var img = document.getElementById('knowledgeimg');
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
      
      function queryValueByKey(name) {
            var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
            if (result == null || result.length < 1) {
                return null;
            }
            var value = decodeURIComponent(result[1]);
            if ("null" == value) {
                value = null;
            }
            return value;
        }
</script>
</body>
</html>