<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                        <a href="#">药水管理</a>
                    </li>
                    <li class="active">药水列表</li>
                </ul><!-- .breadcrumb -->
            </div>
            <!-- 当前页定位结束 -->
            <!-- 右边内容开始 -->
            <div class="page-content">
                <!-- 内容开始区域 -->
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <form class="form-horizontal" id="validation-form" role="form" method="post" action="${ctx}/potion/save" enctype="multipart/form-data" >
                            <input type="hidden" name="id" value="${dto.id}"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" required for="potioncategoryid"> 药水分类 </label>
                                <div class="col-sm-9">
                                    <select id="potioncategoryid" name="potioncategoryid">
                                        <c:forEach items="${potionCategoryList}" var="item">
                                            <option value="${item.id}" <c:if test="${dto.potioncategoryid == item.id}">selected</c:if>>${item.name}</option>
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
                                <label class="col-sm-3 control-label no-padding-right" for="price"> 价格 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="price" name="price" value="${dto.price}" maxlength="8" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> 图片 </label>
                                <div class="col-sm-4">
                                    <input type="file" onchange="previewImage(this)" name="file" value="${dto.pic}"/>
                                </div>
                                <div class="col-sm-5" >
                                    <c:if test="${dto.pic == null}">
                                        <img src="${ctx}/resources/images/default.jpg" style="width: 100px;height: 100%;" id="picimg">
                                    </c:if>
                                    <c:if test="${dto.pic != null}">
                                        <img src="${imgPath}/${dto.pic}"  style="width: 100px;height: 100%;" id="picimg">
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="detail"> 详情 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="detail" name="detail" value="${dto.detail}" maxlength="100" required class="col-xs-10 col-sm-5" autocomplete="off"/>
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
<script src="${ctx}/resources/DatePicker/WdatePicker.js"></script>
<script src="${ctx}/resources/js/jquery.validate.min.js"></script>
<script src="${ctx}/resources/js/messages_zh.js"></script>

<%--<script src="http://www.jq22.com/jquery/1.11.1/jquery.min.js"></script>--%>
<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
<script src="${ctx}/resources/city/js/main.js"></script>
<script src="${ctx}/resources/city/js/distpicker.data.js"></script>
<script src="${ctx}/resources/city/js/distpicker.js"></script>
<script>
    $(function(){
        // 金额验证
        jQuery.validator.addMethod("priceReg", function(value, element) {
            var price =  /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
            return this.optional(element) || (price.test(value));
        }, "请正确填写价格");
        $('#validation-form').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            rules: {
                price: {
                    required: true,
                    priceReg:true
                }
            },
            messages: {
                price: {
                    required: "请输入价格",
                    priceReg:"价格格式错误"
                }
            },
            submitHandler: function (form) {
                //
                $(form).ajaxSubmit({
                    success: function (data) {
                       if(data.ok){
                           $.scojs_message(data.msg, $.scojs_message.TYPE_OK);
                           window.location.assign("${ctx}/potion/list");
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

        $("#id-disable-check").click(function(){
            var _this = $(this);
            var mobile = $("#mobile").val();
            if(_this.is(":checked")){
                $("#usercard").val(mobile);
            }else{
                $("#usercard").val("");
            }
        })
    })

    function previewImage(file)
    {
        var MAXWIDTH  = 382;
        var MAXHEIGHT = 350;
        var div = document.getElementById('picimg');
        if (file.files && file.files[0])
        {
            div.innerHTML ='<img id=picimg>';
            var img = document.getElementById('picimg');
            img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
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
            div.innerHTML = '<img id=picimg>';
            var img = document.getElementById('picimg');
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
</script>
</body>
</html>