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
                        <form class="form-horizontal" id="validation-form" role="form" method="post" action="${ctx}/user/save">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="idcard"> 会员卡号 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="usercard" name="usercard" maxlength="30" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="name"> 会员姓名 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="name" name="name" required maxlength="30" class="col-xs-10 col-sm-5" />
                                    <%--<span class="help-inline col-xs-12 col-sm-7">
                                        <label class="middle">
                                            <input class="ace" type="checkbox" id="id-disable-check" />
                                            <span class="lbl"> 手机号当做会员卡号!</span>
                                        </label>
                                    </span>--%>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="mobile"> 联系电话 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="mobile" name="mobile" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" required for="sex"> 会员性别 </label>
                                <div class="col-sm-9">
                                    <select id="sex" name="sex">
                                        <option value="1">男</option>
                                        <option value="0">女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" required for="storeid"> 会员分类 </label>
                                <div class="col-sm-9">
                                    <select id="storeid" name="storeid">
                                        <c:forEach items="${typeList}" var="item">
                                            <option value="${item.id}">${item.typename}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="blance"> 余额 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="blance" name="blance" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="points"> 积分 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="points" name="points" class="col-xs-10 col-sm-5" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="birthday"> 生日 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="birthday" class="confS_input pg_input" required name="birthday" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="city"> 省市区 </label>
                                <div class="col-sm-9">
                                   <input type="text" id="province" name="province" required class="col-xs-10 col-sm-2" placeholder="省" />
                                    <input type="text" id="city" name="city" required class="col-xs-10 col-sm-2" placeholder="市"/>
                                    <input type="text" id="area" name="area" required class="col-xs-10 col-sm-2" placeholder="区"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="address"> 详细地址 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="address" name="address" required class="col-xs-10 col-sm-5" autocomplete="off" tip="请输入邮箱"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="idcard">身份证 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="idcard" name="idcard" class="col-xs-10 col-sm-5" simple="{emp:true,empmsg:'请输入登录账号'}"/>
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
<script src="${ctx}/resources/DatePicker/WdatePicker.js"></script>
<script src="${ctx}/resources/js/messages_zh.js"></script>
<script src="${ctx}/resources/js/jquery.validate.min.js"></script>
<script>
    $(function(){
        // 手机号码验证
        jQuery.validator.addMethod("mobileReg", function(value, element) {
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");
        // 余额验证
        jQuery.validator.addMethod("blanceReg", function(value, element) {
            var length = value.length;
            var blance =  /^d*(?:.d{0,2})?$/;
            return this.optional(element) || (length == 11 && blance.test(value));
        }, "余额格式不正确");
        $('#validation-form').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            rules: {
                usercard: {
                    required: true,
                    number:true
                },
                mobile: {
                    required: true,
                    mobileReg:true
                },
                blance: {
                    required: true,
                    blanceReg:true
                }
            },
            messages: {
                usercard: {
                    required: "请输入会员卡号",
                    number:"数字"
                },
                mobile: {
                    required: "请输入电话",
                    number:"电话格式错误"
                },
                blance: {
                    required: "请输入金额",
                    blanceReg:"格式错误"
                }
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
</script>
</body>
</html>