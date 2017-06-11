<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/11
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/resources/css/pager/globle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/pager/index.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/pager/css.css">

    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/pager/uploadify.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/pager/jquery.fancybox.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/pager/public3.css">


    <script src="${ctx}/resources/js/pager/jquery.js"></script>
    <script src="${ctx}/resources/js/pager/config.js"></script>
    <script src="${ctx}/resources/js/pager/Pager.js"></script>
    <script src="${ctx}/resources/js/pager/control.js"></script>
    <script src="${ctx}/resources/js/pager/ajaxupload.js"></script>
    <script src="${ctx}/resources/js/pager/editor-common.js"></script>
    <script src="${ctx}/resources/js/pager/my.js"></script>
    <script src="${ctx}/resources/js/pager/public3.js"></script>
    <script src="${ctx}/resources/js/pager/swfobject.js"></script>
    <script src="${ctx}/resources/js/pager/jquery.fancybox.js"></script>
    <script src="${ctx}/resources/js/pager/jquery.uploadify.js"></script>
    <script src="${ctx}/resources/js/pager/showpic.js"></script>
    <script src="${ctx}/resources/js/pager/flexpaper_flash_debug.js" ></script>
    <script src="${ctx}/resources/js/pager/flexpaper_flash.js"></script>
    <script src="${ctx}/resources/js/pager/validate.js"></script>
</head>
<body>
<div class="config_main">
    <!--搜索区域 S-->
    <!-- <table class="config_head">
        <tr>
            <td class="config_new">
                <a>
                    <img src="${URL}cxy/images/icon_05.png" alt="发起会议"/>
                    <p>发起会议</p>
                </a>
            </td>
            <td class="pdl10">
                <div class="config_search" id="schedulingform">
                    <p class="confSear_tit"><span>搜索条件</span></p>
                    <p class="confSear_cont">
                        <span>会议名称：<input type="text" id = "mc" name="mc"  class="confS_input" placeholder="请输入会议名称"/></span>
                        &lt;!&ndash;      &ndash;&gt;
                        <span>
                            会议来源：
                        <select class="confS_input2" id="ly" name="ly">
                        	<option value="">全部</option>
                        	<option>教学会议</option>
                            <option>党政会议</option>
                            <option>专家座谈</option>
                            <option>学生座谈</option>
                            <option>巡检消息</option>
                            <option>其他</option>
                        </select>
                        </span>

                        <span>


                            会议时间：			<input  type="text" title="会议时间" name="sj1" id="sj1"
                                                     placeholder="请输入时间"

                                                     class="confS_input" onClick="WdatePicker()">
                            	 			<span class="tip"></span>

                        </span>

                        <span>
                        发起人：<input type="text" id="fqr" name="fqr"  class="confS_input" placeholder="请输入会议发起人"/>
                        </span>
                        <a class="search_btn che_bth" id="pager_search">查询</a>
                        &lt;!&ndash; <button class="search_btn" onclick="del()">删除</a> &ndash;&gt;
                    </p>

                </div>

            </td>
        </tr>
    </table>-->
    <!--搜索区域 E-->


    <!--表格区域 S-->
    <div class="config_table" id="info">
        <script>
            var pager;
            $(document).ready(function(e) {
                var cm = new Column({
                    columns:[
                        {name:"序号",rowno:true,width:"50px"},
                        {name:"会议名称",value:"name",width:""}
                    ]

                });

                // 该插件规定查询按钮ID 为 pager_search,如 $("#pager_search").load();
                pager = new Pager({
                    cm:cm, // 列名
                    url:"test/testPager",
                    renderTo:"info", // 用于显示数据的容器
                    //prikey:"BH",//checkbox 或 radio显示的value
                    radiobox:true,
                    //checkbox:true, // 列表展示多选,还有单选
                    radiobox:true, // 列表展示多选,还有单选
                    data:{start:0,end:10},
                    formid:"schedulingform",  //与上面div id对应 条件查询
                    searchid:"pager_search",

                });
                pager.paginate();//展示表头
                pager.load(); //加载数据

            });
        </script>

    </div>
    <!--表格区域 E-->


</div>

</body>
</html>
