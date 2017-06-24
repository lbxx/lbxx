<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 引入公共js css -->
    <jsp:include page="../jscss.jsp" />
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
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 账号 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-1" placeholder="请输入账号" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-2">密码 </label>
                                <div class="col-sm-9">
                                    <input type="password" id="form-field-2" placeholder="请输入密码" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-3">昵称 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-3" placeholder="请输入昵称" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-select-1" >账号类型</label>
                                <div class="col-sm-9">
                                <select class=" col-xs-10 col-sm-5" id="form-field-select-1"   >
                                    <option hidden >请选择</option>
                                    <option value="">管理员</option>
                                    <option value="AL">连锁店店长</option>
                                    <option value="AK">门店店长</option>
                                    <option value="AZ">收营员</option>
                                </select>
                                </div>

                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-4"> 手机号 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-4" placeholder="请输入手机号" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-5"> QQ </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-5" placeholder="请输入QQ" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-6"> 邮箱 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-6" placeholder="请输入邮箱" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">性别</label>
                                <div class="col-xs-12 col-sm-9">
                                    <div>
                                        <label class="blue">
                                            <input name="gender" value="1" type="radio" class="ace" checked>
                                            <span class="lbl"> 男</span>
                                        </label>
                                    </div>
                                    <div>
                                        <label class="blue">
                                            <input name="gender" value="2" type="radio" class="ace">
                                            <span class="lbl"> 女</span>
                                        </label>
                                    </div>
                                </div>

                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">上传头像</label>
                                <div class="col-sm-9">
                            <div class="ace-file-input ace-file-multiple col-xs-10 col-sm-5">
                                <input multiple="" type="file" id="id-input-file-3"  >
                                <label class="file-label" data-title="Drop images here or click to choose">
                                    <span class="file-name" data-title="No File ..."><i class="icon-picture"></i></span></label>
                                <a class="remove" href="#"><i class="icon-remove"></i></a></div>
                                </div>
                            </div>
                            <div class="space-4"></div>

                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">
                                    <button class="btn btn-info" type="button">
                                        <i class="icon-ok bigger-110"></i>
                                        Submit
                                    </button>
                                    &nbsp; &nbsp; &nbsp;
                                    <button class="btn" type="reset">
                                        <i class="icon-undo bigger-110"></i>
                                        Reset
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


</body>
</html>