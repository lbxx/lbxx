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
                                    <button class="btn btn-info" type="button" id="submitBtn">
                                        <i class="icon-ok bigger-110"></i>
                                        提交
                                    </button>
                                    <button class="btn btn-info" type="button" id="getChecked">
                                        <i class="icon-ok bigger-110"></i>
                                        获取选中
                                    </button>
                                </div>
                            </div>
                        <!-- 存入选中的修改的角色编码 -->
                        <input type="hidden" name="code" id="code" value="${code}"/>
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
<script src="${ctx}/resources/js/messages_zh.js"></script>

<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>

<link rel="stylesheet" href="${ctx}/resources/zTree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${ctx}/resources/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/resources/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/resources/zTree/js/jquery.ztree.excheck.js"></script>


</body>
<script type="text/javascript">
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

    /*var zNodes =[
     { id:1, pId:0, name:"随意勾选 1", open:true},
     { id:111, pId:1, name:"随意勾选 1-1"},
     { id:112, pId:1, name:"随意勾选 1-2"},
     { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
     { id:21, pId:2, name:"随意勾选 2-1"},
     { id:22, pId:2, name:"随意勾选 2-2", open:true},
     { id:23, pId:2, name:"随意勾选 2-3"}
     ];*/
    var zNodes =   ${permissionStr};
    var code;
    function setCheck() {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            type = { "Y":"ps", "N":"ps"};
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
    });

    // 提交权限
    $("#submitBtn").click(function(){
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var nodesstr="";
        for(var i = 0; i < nodes.length; i++){
            nodesstr+=nodes[i].pId+"&"+nodes[i].id+",";
        }
        var code = $("#code").val();
        $.ajax({
            url:"${ctx}/role/savePermission",
            type:"POST",
            data:{"permissions":nodesstr,"code":code},
            dataType:"",
            success:function(data){
                if(data){
                    $.scojs_message(data.msg, $.scojs_message.TYPE_OK);
                    window.location.assign("${ctx}/role/listIndex");
                }else{
                    $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
                }
            }
        })
    });

</script>
</html>