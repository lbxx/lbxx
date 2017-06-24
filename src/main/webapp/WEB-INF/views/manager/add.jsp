<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 引入公共js css -->
    <jsp:include page="../jscss.jsp" />
    <style>
        /* 下面这个css代码是对列表表格样式的调整，以前列表有滚动条，加上这个css可以根据内容高度自动适应*/
        .ui-jqgrid-bdiv {
            height: 100% !important;
        }
        .noInput{
            color:red;
        }

    </style>
    <!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script> -->
    <script src="http://api.map.baidu.com/api?v=2.0&ak=EZPCgQ6zGu6hZSmXlRrUMTpr"></script>
</head>
<body>

<!-- 引入顶部 -->
<jsp:include page="../header.jsp" />

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
                        <a href="#">Tables</a>
                    </li>
                    <li class="active">jqGrid plugin</li>
                </ul><!-- .breadcrumb -->
            </div>
            <!-- 当前页定位结束 -->
            <!-- 右边内容开始 -->
            <div class="page-content">
                <!-- 内容开始区域 -->
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <form class="form-horizontal" role="form" id="addForm" method="post" action="${ctx}/manage/add">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 账号 </label>
                                <div class="col-sm-9">
                                    <input type="text" name="account" id="form-field-1" placeholder="请输入账号" required minlength="4" maxlength="32" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-2">密码 </label>
                                <div class="col-sm-9">
                                    <input type="password" name="password" id="form-field-2" placeholder="请输入密码" required minlength="4" maxlength="32" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-3">昵称 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-3" name="nickname" placeholder="请输入昵称" minlength="4"maxlength="20"required class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-select-1" >账号类型</label>
                                <div class="col-sm-9">
                                <select class=" col-xs-10 col-sm-5" id="form-field-select-1" name="role"  required >
                                    <c:forEach items="${roleList}" var="role">
                                        <option value="${role.code}">${role.name}</option>
                                    </c:forEach>
                                </select>
                                </div>

                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-4"> 手机号 </label>
                                <div class="col-sm-9">
                                    <input type="text" name="mobile" id="form-field-4" placeholder="请输入手机号" required minlength="11" maxlength="11" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-5"> QQ </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-5" name="qq" placeholder="请输入QQ" minlength="4" maxlength="32" required class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-6"> 邮箱 </label>
                                <div class="col-sm-9">
                                    <input type="email" id="form-field-6" required minlength="4" name="email" maxlength="32"   placeholder="请输入邮箱" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">性别</label>
                                <div class="col-xs-12 col-sm-9">
                                    <div>
                                        <label class="blue">
                                            <input name="sex" value="0" type="radio" class="ace" checked>
                                            <span class="lbl"> 男</span>
                                        </label>
                                    </div>
                                    <div>
                                        <label class="blue">
                                            <input name="sex" value="1" type="radio" class="ace">
                                            <span class="lbl"> 女</span>
                                        </label>
                                    </div>
                                </div>

                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">上传头像</label>
                                <div class="col-sm-9">
                                    <input type="file" name="file"  placeholder="请上传头像" required class="col-xs-10 col-sm-5" />

                                </div>
                            </div>
                            <div class="space-4"></div>

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
<!-- 引入顶部公共js -->
<jsp:include page="../bottomjs.jsp" />
<script>
    $(function () {
        var contextPath = $('input[name="contextPath"]').val();
         $("#addForm").validate({
         submitHandler: function (form) {
         $(form).ajaxSubmit({
         success: function (data) {
         if(data.result){
         $.scojs_message(data.msg, $.scojs_message.TYPE_OK);
         window.location.reload();
         }else{
         $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
         }
         },error:function (data) {

         }
         });
         }

         , errorPlacement: function (error, element) {

         if ($(element).prop('name') == 'kaptcha') {
         error.appendTo(element.parent())

         } else
         error.appendTo(element.parent())
         }
         });
    });
</script>
</body>

</html>