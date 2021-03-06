<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 引入公共js css -->
    <jsp:include page="../jscss.jsp" />
    <style>
        /* 下面这个css代码是对列表表格样式的调整，以前列表有滚动条，加上这个css可以根据内容高度自动适应*/
        .ui-jqgrid-bdiv{
            height:100%!important;
        }
    </style>
    <script src="${ctx}/resources/DatePicker/WdatePicker.js"></script>
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
            <jsp:include page="../location.jsp"/>
            <!-- 当前页定位结束 -->
            <!-- 右边内容开始 -->
            <div class="page-content">
                <div class="row">
                    <!-- 引入权限jsp -->
                    <jsp:include page="../permission.jsp"/>
                    <div class="col-xs-12">
                        <!-- 显示内容列表的table -->
                        <table id="grid-table"></table>
                        <!-- 下面页面数一栏的table -->
                        <div id="grid-pager"></div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container-inner -->
</div><!-- /.main-container -->
<!-- 引入顶部公共js -->
<jsp:include page="../bottomjs.jsp" />
<!-- 分页自定义js -->
<script type="text/javascript">
    jQuery(function($) {

        // 数据列表table
        var grid_selector = "#grid-table";
        // 显示分页参数的table
        var pager_selector = "#grid-pager";
        var id = "${id}";
        // 配置jqGrid列表table参数
        jQuery(grid_selector).jqGrid({
            url: "${ctx}/appointment/appointmentDetailList",
            datatype: "json",
            postData:{"id":id},
            mtype:"POST",
            //height: 250,
            // jsonReader 这个参数必须和java后台参数一致
            jsonReader : {
                root : "list",
                page : "pageNum",
                total : "pages",
                records : "total",
                repeatitems : false
            },
            // 用于显示列表页table的列头
            colNames:[' ', '用户','店铺','技师','开始时间','结束时间','使用药水'],
            // 列表页数据绑定
            colModel:[
                {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                    formatter:'actions',
                    formatoptions:{
                        keys:true,
                        delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
                    }
                },
                // 下面是列表页其它数据，name属性与java属性的set匹配
                {name:'uname',index:'uname', width:150},
                {name:'bname',index:'bname', width:150},
                {name:'tname',index:'tname', width:150},
                {name:'starttime',index:'starttime', width:150},
                {name:'endtime',index:'endtime', width:150},
                {name:'solution',index:'solution', width:150,editoptions:{value:'1:是;0:否'}}
            ],
            viewrecords : true,
            rowNum:10,
            rowList:[10,20,30],
            prmNames:{page:"pageNum",rows:"pageSize"},
            pager : pager_selector,
            altRows: true,
            //toppager: true,
            multiselect: true,// 显示列中复选框
            //multikey: "ctrlKey",
            multiboxonly: true,
            loadComplete : function() {
                var table = this;
                setTimeout(function(){
                    updatePagerIcons(table);
                }, 0);

            },
            autowidth: true
        });

        // 配置jqGrid列表下面的分页页数table参数
        jQuery(grid_selector).jqGrid('navGrid',pager_selector,
            {
                refresh: true,
                refreshicon : 'icon-refresh green',
                view: true,
                viewicon : 'icon-zoom-in grey',
            },
            {
                recreateForm: true,
                beforeShowForm: function(e){
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                }
            }
        )


        // 列表中的删除按钮调用的方法
        function style_delete_form(form) {
            var buttons = form.next().find('.EditButton .fm-button');

            buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon

            buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');

            buttons.eq(1).prepend('<i class="icon-remove"></i>')

        }
        // 此方法在 style_delete_form()方法调用之前调用，如果删除，后期可以考虑只用一个方法
        function beforeDeleteCallback(e) {
            var form = $(e[0]);
            if(form.data('styled')) return false;
            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
            style_delete_form(form);
            form.data('styled', true);
        }
        // 这个方法是 渲染  上一页 下一页的  > >>   <  <<
        function updatePagerIcons(table) {
            var replacement =
                {
                    'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
                    'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
                    'ui-icon-seek-next' : 'icon-angle-right bigger-140',
                    'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
                };
            $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
                var icon = $(this);
                var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
                if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
            })
        }

    });

</script>
</body>
</html>

