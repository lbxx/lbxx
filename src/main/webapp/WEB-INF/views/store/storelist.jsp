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
					<div class="breadcrumbs ace-save-state">
						<li
							style="list-style: none; padding-left: 20px; font-size: 16px; float: left;"><span
							class="dropdown-toggle"> <i class=""> </i>基础信息
						</span></li>
						<ul class="breadcrumb" style="float: right; line-height: 40px;">
							您的当前位置:
							<li><a href="#">基础信息</a></li>
							<li class="active">我的账号</li>
						</ul>
					</div>
					<!-- .breadcrumb -->
				</div>
				<!-- 当前页定位结束 -->
				<!-- 右边内容开始 -->
				<div class="page-content">
					<div class="row">
						<input type="text" id="searchName" placeholder="请输入菜单名查询" />
						<button id="sbtn">测试搜索</button>
						<!-- ================= -->
						<div style="padding: 10px;">
							<button id="refesh" class="btn btn-success"
								style="border-radius: 5px;">刷新</button>
							<button class="btn btn-success" style="border-radius: 5px;">导入店铺</button>
							<button class="btn btn-success" style="border-radius: 5px;">导出店铺</button>
							<div style="display: inline; float: right;">
								<label for="form-field-select-1" style="font-size: 20px;">门店</label>
								<select class="form-control" id="form-field-select-1"
									style="width: 150px; display: inline;">
									<!-- <option value=""></option> -->

								</select> <select class="form-control" id="form-field-select-2"
									style="width: 200px; display: inline;">
									<!-- <option value=""></option> -->

								</select>
								<button id="queryBtn" class="btn btn-success btn-xs"
									style="border-radius: 5px;">查询</button>
							</div>
						</div>
						<!-- ================= -->
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
	<jsp:include page="../footer.jsp"></jsp:include>
	<!-- 引入顶部公共js -->
	<jsp:include page="../bottomjs.jsp" />
	<!-- 分页自定义js -->
	<script type="text/javascript">
		/* var selection2; */
		jQuery(function($) {
			$
					.ajax({
						url : "${ctx}/store/option",
						type : 'GET',
						headers : {
							/* Accept: "application/xml", */
							Accept : "application/json",
						},
						success : function(data, textStatus) {
							$('#form-field-select-1').html(
									"<option value=''></option>");
							var selection1 = data[0];
							for (var i = 0; i < selection1.length; i++) {
								$('#form-field-select-1').append(
										"<option value='"+selection1[i].id+"'>"
												+ selection1[i].name
												+ "</option>");
							}
							var selection2 = objOfPropertyToArr(data[1]);
							$('#form-field-select-1')
									.change(
											function() {
												$('#form-field-select-2').html(
														"");
												var id = $(this).val();
												for (var i = 0; i < selection2.length; i++) {
													for (var j = 0; j < selection2[i].length; j++) {
														if (selection2[i][j].chainstoreid == id) {
															$(
																	'#form-field-select-2')
																	.append(
																			"<option value='"+selection2[i][j].id+"'>"
																					+ selection2[i][j].name
																					+ "</option>")
														}
													}
												}
											});
						},
						error : function(data, textStatus, errorThrown) {
							console.log(data);
						},
					});
			// 自定义搜索方法，暂时先用，后期研究jqGrid搜索
			$("#queryBtn").click(function() {
				
				var id = $("#form-field-select-2").val();
				if(id == null || id == ''){
					alert("请先选择门店");
					return;
				}
				$("#grid-table").jqGrid('setGridParam', { // grid-table 这个是表格的id, setGridParam这个值是固定值
					url : "${ctx}/store/query", // 请求url
					postData : {
						"id" : id
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
				url : "${ctx}/store/list",
				editUrl : "${ctx}/store/update",
				datatype : "json",
				height : 250,
				// jsonReader 这个参数必须和java后台参数一致
				jsonReader : {
					root : "result",
					page : "pageNum",
					total : "pages",
					records : "total",
					repeatitems : false
				},
				// 用于显示列表页table的列头
				colNames : [ '管理', 'ID', '店铺名称', '店铺地址', '联系方式' ],
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
						 editformbutton:true,
						 editOptions : {
							 url : '${ctx}/store/update',
							 closeAfterEdit:true,
							 //recreateForm: true,
							 //beforeShowForm:beforeEditCallback
							 //afterShowForm:$.GridUtils.afterEditCallback,
							 afterSubmit: function(response, formid) {
	                                var data = JSON.parse(response.responseText);
	                                if (data.result) {
	                                    return [ true, "OK", data.id ];
	                                } else {
	                                    return [ false, data.errmsg ];
	                                }
	                            },
		                     //afterComplete:afterCompleteCallback
		                     },
						delOptions : {
							url : '${ctx}/store/delete',
							recreateForm : true,
							beforeShowForm : beforeDeleteCallback,
							afterSubmit : function(response, formid) {
								var data = JSON.parse(response.responseText);
								if (data.result) {
									return [ true, "OK", data.id ];
								} else {
									return [ false, data.errmsg ];
								}
							}
						}
					}
				},
				// 下面是列表页其它数据，name属性与java属性的set匹配
				{
					name : 'id',
					index : 'id',
					width : 60,
					sorttype : "int",
					editable : false
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
					name : 'location',
					index : 'location',
					width : 150,
					editable : true
				}, {
					name : 'telephone',
					index : 'telephone',
					width : 150,
					editable : true
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
				autowidth : true,
			});
			// 配置jqGrid列表下面的分页页数table参数
			jQuery(grid_selector).jqGrid(
					'navGrid',
					pager_selector,
					{
						refresh : true,
						refreshicon : 'icon-refresh green',
						view : true,
						viewicon : 'icon-zoom-in grey',
						edit : false,
						//addfunc : openDialog4Adding,    // (1) 点击添加按钮，则调用openDialog4Adding方法  
						//editfunc : openDialog4Updating, // (2) 点击编辑按钮，则调用openDialog4Updating方法  
						//delfunc : openDialog4Deleting, // (3) 点击删除按钮，则调用openDialog4Deleting方法  
						alerttext : "请选择需要操作的数据行!" // (4) 当未选中任何行而点击编辑、删除、查看按钮时，弹出的提示信息
					},
					{
						reloadAfterSubmit : true,
                        //closeOnEscape : true,
                        closeAfterEdit:true,
                        url : '${ctx}/store/update',
                        beforeShowForm : function(form) {
                        },
                        afterComplete : function() {
                            //jQuery(grid_selector).jqGrid('clearGridData', true);
                            //jQuery("#shipFactorList_d2").jqGrid('clearGridData', true);
                        },
                        afterSubmit : function(response, formid) {
                            var data = JSON.parse(response.responseText);
                            if (data.result) {
                                return [ true, "OK", data.id ];
                            } else {
                                return [ false, data.errmsg ];
                            }
                        }
					},
					{},
					{
						reloadAfterSubmit : true,
						closeOnEscape : true,
						url : '${ctx}/store/delete',
						beforeShowForm : function(form) {
						},
						afterComplete : function() {
							//jQuery("#shipFactorList_d").jqGrid('clearGridData', true);
							//jQuery("#shipFactorList_d2").jqGrid('clearGridData', true);
						},
						afterSubmit : function(response, formid) {
							var data = JSON.parse(response.responseText);
							if (data.status) {
								return [ true, "OK", data.id ];
							} else {
								return [ false, data.errmsg ];
							}
						}
					},
					{

						recreateForm : true,

						beforeShowForm : function(e) {
							var form = $(e[0]);

							form.closest('.ui-jqdialog').find(
									'.ui-jqdialog-title').wrap(
									'<div class="widget-header" />')

						}

					});

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

			//将object 转换成数组
			function objOfPropertyToArr(object) {
				var arr = [];
				var i = 0;
				for ( var item in object) {
					arr[i] = object[item];
					i++;
				}
				return arr;
			}
			
			function beforeEditCallback(){
				alert('beforeEdit');
			}
			function afterSubmitCallback(response,formid){
				var data = JSON.parse(response.responseText);
                console.log(data);
                if (data.result) {
                    return [ true, "OK", data.id ];
                } else {
                    return [ false, data.errmsg ];
                }
			}

		});
	</script>
</body>
</html>