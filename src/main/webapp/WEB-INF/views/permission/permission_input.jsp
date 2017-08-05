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
    <script type="text/javascript">
        var setting = {
            check: {
                enable: true,
                chkboxType:{
                    "Y" : "ps",
                    "N" : "ps"
                }
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var zNodes =[
            { id:1, pId:0, name:"随意勾选 1", open:true},
            { id:11, pId:1, name:"随意勾选 1-1", open:true},
            { id:111, pId:11, name:"随意勾选 1-1-1"},
            { id:112, pId:11, name:"随意勾选 1-1-2"},
            { id:12, pId:1, name:"随意勾选 1-2", open:true},
            { id:121, pId:12, name:"随意勾选 1-2-1"},
            { id:122, pId:12, name:"随意勾选 1-2-2"},
            { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
            { id:21, pId:2, name:"随意勾选 2-1"},
            { id:22, pId:2, name:"随意勾选 2-2", open:true},
            { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
            { id:222, pId:22, name:"随意勾选 2-2-2"},
            { id:23, pId:2, name:"随意勾选 2-3"}
        ];

        var code;

        function setCheck() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                py = $("#py").attr("checked")? "p":"",
                sy = $("#sy").attr("checked")? "s":"",
                pn = $("#pn").attr("checked")? "p":"",
                sn = $("#sn").attr("checked")? "s":"",
                type = { "Y":py + sy, "N":pn + sn};
            zTree.setting.check.chkboxType = type;
            showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
        }
        function showCode(str) {
            if (!code) code = $("#code");
            code.empty();
            code.append("<li>"+str+"</li>");
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            setCheck();
            $("#py").bind("change", setCheck);
            $("#sy").bind("change", setCheck);
            $("#pn").bind("change", setCheck);
            $("#sn").bind("change", setCheck);
        });
    </script>
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
                        <a href="#">权限管理</a>
                    </li>
                    <li class="active">权限操作</li>
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
                            <input type="hidden" name="id" value="${dto.id}"/>
                            <div class="form-group">
                                <%--<div class="col-sm-9">
                                    <input type="text" id="usercard" name="usercard" maxlength="30" value="${dto.usercard}" class="col-xs-10 col-sm-5" />
                                </div>--%>
                                    <div class="zTreeDemoBackground left">
                                        <ul id="treeDemo" class="ztree"></ul>
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
        // 手机号码验证
        jQuery.validator.addMethod("mobileReg", function(value, element) {
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");
        // 积分验证
        jQuery.validator.addMethod("blanceReg", function(value, element) {
            var length = value.length;
            var blance =  /(\d{1,}\.\d{2}$)||(\d{1,}\.$)/;
            return this.optional(element) || (blance.test(value));
        }, "请正确填写您的积分");
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
                },
                points: {
                    required: true,
                    number:true
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
                    required: "请输入余额",
                    blanceReg:"余额格式错误"
                },
                points: {
                    required: "请输入积分",
                    number:"格式错误"
                }
            },
            submitHandler: function (form) {
                //
                $(form).ajaxSubmit({
                    success: function (data) {
                       if(data.ok){
                           $.scojs_message(data.msg, $.scojs_message.TYPE_OK);
                           window.location.assign("${ctx}/user/list");
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
</script>
</body>
</html>