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
.ui-jqgrid-bdiv {
	height: 100% !important;
}
</style>
</head>
<body>

	<!-- 引入顶部 -->
	<jsp:include page="../header.jsp" />

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<!-- 这个是箭头 -->
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>
			<!-- 引入左边菜单 -->
			<jsp:include page="../lefter.jsp" />
			<!-- 右边内容开始 -->
			<div class="main-content">
				<!-- 当前页定位开始 -->
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>
					<!-- <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li>
                        <a href="#">Tables</a>
                    </li>
                    <li class="active">jqGrid plugin</li>
                </ul>.breadcrumb -->
					<jsp:include page="../location.jsp" />
				</div>
				<!-- 当前页定位结束 -->
				<!-- 右边内容开始 -->
				<div class="page-content">
					<div class="row">
						<jsp:include page="../permission.jsp" />
						<!-- <input type="text" id="searchName" placeholder="请输入菜单名查询"/>
                    <button id="sbtn">测试搜索</button> -->
						<div class="col-xs-12">
							<!-- 显示内容列表的table -->
							<table id="grid-table"></table>
							<!-- 下面页面数一栏的table -->
							<div id="grid-pager"></div>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
		</div>
		<!-- /.main-container-inner -->
	</div>
	<!-- /.main-container -->
	<!-- 引入顶部公共js -->
	<jsp:include page="../bottomjs.jsp" />
	<!-- 分页自定义js -->
	<script type="text/javascript">
		jQuery(function($) {
			// 自定义搜索方法，暂时先用，后期研究jqGrid搜索
			$("#sbtn").click(function() {
				var name = $("#searchName").val();
				$("#grid-table").jqGrid('setGridParam', { // grid-table 这个是表格的id, setGridParam这个值是固定值
					url : "${ctx}/test/testgrid", // 请求url
					postData : {
						"name" : name
					}, // 搜索过滤条件
					page : 1
				// 点击搜索，默认是加载搜索后第一页数据
				}).trigger("reloadGrid"); // 渲染表格数据，这个  reloadGrid  是固定值
			});

			// 数据列表table
			var grid_selector = "#grid-table";
			// 显示分页参数的table
			var pager_selector = "#grid-pager";

			// 配置jqGrid列表table参数
			jQuery(grid_selector).jqGrid({
				url : "${ctx}/category/listgrid",
				datatype : "json",
				//height: 250,
				// jsonReader 这个参数必须和java后台参数一致
				jsonReader : {
					root : "result",
					page : "pageNum",
					total : "pages",
					records : "total",
					repeatitems : false
				},
				// 用于显示列表页table的列头
				//colNames:[' ', '会员卡','姓名','电话','性别','店铺名','用户类别','注册时间'],
				colNames : [ '', 'ID', '名称','创建时间' ],
				// 列表页数据绑定
				colModel : [ {
					name : 'myac',
					index : '',
					width : 80,
					fixed : true,
					sortable : false,
					resize : false,
					formatter : 'actions',
					formatoptions : {
						keys : true,
						delOptions : {
							recreateForm : true,
							beforeShowForm : beforeDeleteCallback
						},
					}
				},
				// 下面是列表页其它数据，name属性与java属性的set匹配
				{
					name : 'id',
					index : 'id',
					width : 150,
					editable : true,
					editoptions : {
						size : "20",
						maxlength : "30"
					}
				}, {
					name : 'name',
					index : 'name',
					width : 150,
					editable : true,
					editoptions : {
						size : "20",
						maxlength : "30"
					}
				}, {
					name : 'createtime',
					index : 'createtime',
					width : 150,
					editable : true,
					editoptions : {
						size : "20",
						maxlength : "30"
					},
					formatter : formatCreateTime
				} ],
				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				prmNames : {
					page : "pageNum",
					rows : "pageSize"
				},
				pager : pager_selector,
				altRows : true,
				//toppager: true,
				multiselect : true,
				//multikey: "ctrlKey",
				multiboxonly : true,
				loadComplete : function() {
					var table = this;
					setTimeout(function() {
						updatePagerIcons(table);
					}, 0);

				},
				autowidth : true
			});
			//格式化创建时间
			function formatCreateTime(cellvalue, options, rowdata) {
				var unixTimestamp = new Date(cellvalue);
				return unixTimestamp.toLocaleString();
			}
			// 配置jqGrid列表下面的分页页数table参数
			jQuery(grid_selector).jqGrid(
					'navGrid',
					pager_selector,
					{
						refresh : true,
						refreshicon : 'icon-refresh green',
						view : true,
						viewicon : 'icon-zoom-in grey',
					},

					{

						recreateForm : true,

						beforeShowForm : function(e) {

							var form = $(e[0]);

							form.closest('.ui-jqdialog').find(
									'.ui-jqdialog-title').wrap(
									'<div class="widget-header" />')

						}

					}

			)

			// 列表中的删除按钮调用的方法
			function style_delete_form(form) {
				var buttons = form.next().find('.EditButton .fm-button');

				buttons.addClass('btn btn-sm').find('[class*="-icon"]')
						.remove();//ui-icon, s-icon

				buttons.eq(0).addClass('btn-danger').prepend(
						'<i class="icon-trash"></i>');

				buttons.eq(1).prepend('<i class="icon-remove"></i>')

			}
			// 此方法在 style_delete_form()方法调用之前调用，如果删除，后期可以考虑只用一个方法
			function beforeDeleteCallback(e) {
				var form = $(e[0]);
				if (form.data('styled'))
					return false;
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
						.wrapInner('<div class="widget-header" />')
				style_delete_form(form);
				form.data('styled', true);
			}
			// 这个方法是 渲染  上一页 下一页的  > >>   <  <<
			function updatePagerIcons(table) {
				var replacement = {
					'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
					'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
					'ui-icon-seek-next' : 'icon-angle-right bigger-140',
					'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
				};
				$(
						'.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
						.each(
								function() {
									var icon = $(this);
									var $class = $.trim(icon.attr('class')
											.replace('ui-icon', ''));
									if ($class in replacement)
										icon.attr('class', 'ui-icon '
												+ replacement[$class]);
								})
			}

		});

		//自定义编辑按钮方法
		$('#editButton').removeAttr('onclick');//移除添加按钮上原有的click事件
		$('#editButton')
				.bind(
						'click',
						function() {
							var grid_selector = "#grid-table";
							var selectedIds = $(grid_selector).jqGrid(
									'getGridParam', 'selarrrow');
							if (selectedIds == undefined
									|| selectedIds.length == 0
									|| selectedIds.length > 1) {
								mydialog("", '对不起，请选择一个选项!');
							} else {
								if (selectedIds.length <= 0) {
									return;
								} else {
									location.href = "${ctx}/category/add?categoryid="
											+ selectedIds[0];
								}
							}
						});

		//自定义删除按钮方法
		$('#removeButton').removeAttr('onclick');//移除元素上原有的click事件
		$('#removeButton').bind(
				'click',
				function() {
					var grid_selector = "#grid-table";
					var selectedIds = $(grid_selector).jqGrid('getGridParam',
							'selarrrow');
					if (selectedIds == undefined || selectedIds.length == 0) {
						mydialog("", '对不起，请选择一个选项!');
					} else {
						if (selectedIds.length <= 0) {
							return;
						} else {
							//var id = $idsCheckedCheck.parent().parent().attr("id");
							var url = '${ctx}/category/remove' + "?categoryid="
									+ selectedIds[0];
							mydialog(url, '您确定要删除吗?');
						}
					}
					return false;

				});
		// 下面是dialog弹窗的
		function mydialog(url, msg) {
			var grid_selector = "#grid-table";
			// 动态生成弹窗内容
			$("#dialog-message p").html(msg);
			// 下面这个方法是dialog样式文件，勿删
			$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
				_title : function(title) {
					var $title = this.options.title || '&nbsp;'
					if (("title_html" in this.options)
							&& this.options.title_html == true)
						title.html($title);
					else
						title.text($title);
				}
			}));
			var dialog = $("#dialog-message")
					.removeClass('hide')
					.dialog(
							{
								modal : true,
								title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>提示信息</h4></div>",
								title_html : true,
								buttons : [
										{
											text : "Cancel",
											"class" : "btn btn-xs",
											click : function() {
												// 取消关闭弹窗
												$(this).dialog("close");
											}
										},
										{
											text : "OK",
											"class" : "btn btn-primary btn-xs",
											click : function() {
												// 确定关闭弹窗，然后执行操作
												$(this).dialog("close");
												// 判断url是否为空
												if (url != "" && url != null) {
													$
															.ajax({
																url : url,
																type : 'GET',
																headers : {
																	Accept : "application/json",
																},
																success : function(
																		data) {
																	if (data.result) {

																		$(
																				grid_selector)
																				.setGridParam(
																						{
																							datatype : 'json',
																							page : 1
																						})
																				.trigger(
																						'reloadGrid');
																	}
																},
																error : function() {

																}
															});
												}
											}
										} ]
							});
		}
	</script>
</body>
</html>