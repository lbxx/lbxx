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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
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
							class="dropdown-toggle"> <i class=""> </i>店铺管理
						</span></li>
						<ul class="breadcrumb" style="float: right; line-height: 40px;">
							您的当前位置:
							<li><a href="#">店铺管理</a></li>
							<li class="active">添加门店</li>
						</ul>

					</div>
					<ul class="breadcrumb"
						style="padding-left: 10px;; line-height: 40px;">
						<li>添加门店</li>
					</ul>
					<!-- .breadcrumb -->
				</div>
				<!-- 当前页定位结束 -->
				<!-- 右边内容开始 -->
				<div class="page-content">
					<div class="row">
						<!-- ================= -->
						<form class="form-horizontal" role="form"
							enctype="multipart/form-data" action="">
							<!--左上  -->
							<div id="addStore_up_left"
								style="border: 1px solid #797979; width: 50%; float: left;">
								<div style="margin: 10px 0px;">
									<label for="form-field-select-1"
										style="float: left; line-height: 2em; width: 15%;">店铺名称</label>
									<select class="form-control" id="form-field-select-1"
										style="width: 150px; margin-top: -3px; display: inline;">
										<!-- <option value=""></option> -->
									</select> <input type="text" id="form-field-1" placeholder="连锁下属门店名称 "
										class="" style="height: 32px; width: 56%;">
									<div style="color: #AEAEAE;">单店连锁店名称请填写单店名称</div>
								</div>
								<div style="margin-bottom: 10px;">
									<input type="text" id="form-field-1" placeholder="区号" class=""
										style="width: 10%;">一 <label for="form-field-select-1"
										style="float: left; line-height: 2em; width: 15%;">店铺座机</label>
									<input type="text" id="form-field-1" placeholder="座机号码"
										class="" style="width: 70%;">
									<div style="color: #AEAEAE;">区号座机号分开填写</div>
								</div>
								<div style="margin-bottom: 24px;">
									<label for="form-field-select-1"
										style="float: left; line-height: 2em; width: 15%;">手机</label>
									<input type="text" id="form-field-1" placeholder="请填写负责人手机号码"
										class="" style="width: 83%;">
								</div>
								
							</div>
							<!--右上  -->
							<div id="addStore_up_right"
								style="border: 1px solid #797979; width: 50%; height: 100%; float: right;">
								<div
									style="width: 40%; height: inherit; float: left; margin: 10px 0px;">
									<label for="form-field-select-1"
										style="line-height: 2em; font-size: 20px; padding-left: 30px;">店面图</label>

									<div style="margin: 15px 0px;">
										<label for="form-field-select-1"
											style="font-size: 14px; padding-left: 30px;">支持jpg、jpeg、jpe、png、pns等格式</label>
									</div>
									<!-- <input type="file" id="file"
										style="margin: 15px 30px; width: 100%;" /> -->
                                    <div class="form-group" style="border:1px solid red;padding-left:100px;margin-bottom:-30px;">
                                        <div class="col-xs-12">
                                            <label class="ace-file-input"><input style="border:1px solid red;" type="file"
                                                id="id-input-file-2"><span
                                                class="ace-file-container" data-title="Choose"><span
                                                    class="ace-file-name" data-title="No File ..."><i
                                                        class=" ace-icon fa fa-upload"></i></span></span><a class="remove"
                                                href="#"><i class=" ace-icon fa fa-times"></i></a></label>
                                        </div>
                                    </div>
									

								</div>
								<div style="width: 60%; float: right; height: 100%; max-height:">
									<img alt="店铺店面图"
										src="${ctx}/resources/images/gallery/image-1.jpg" width="100%"
										height="180px;">
								</div>
							</div>
							<!--左下  -->
							<div id="addStore_bottom_left"
								style="border: 1px solid #797979; width: 50%; float: left;">
								<label class="" for="form-field-1-1"
									style="line-height: 2em; padding-right: 27px;"> 店铺地址 </label> <input
									type="text" id="cityName" name="location" placeholder="请填写店铺的详细地址" class=""
									style="width: 70%;">
								<!-- <button class="btn btn-sm btn-primary" style="border-radius: 5px;" onclick="setCity()">定位</button> -->
								<input type="button" id="gps" value="定位" />
								<div id="container" style="width: 100%; height: 250px;"></div>
							</div>
							<!--右下  -->
							<div id="addStore_bottom_right"
								style="border: 1px solid #797979; width: 50%; float: right; clear: right;">
								<div>
									<!--门店设置  -->
									<div
										style="background-color: #438EB9; color: white; font-size: 16px; padding: 5px;">门店设置</div>
								</div>
								<!--门店设置  -->
								<div class="control-group"
									style="float: left; margin-left: 30px;">
									<label class="control-label bolder"
										style="vertical-align: middle; padding-right: 20px;">是否支持游泳</label>

									<label style="padding-right: 27px;"> <input
										name="swim" type="radio" class="ace"> <span
										class="lbl">是</span>
									</label> <label> <input name="swim" type="radio"
										class="ace"> <span class="lbl">否</span>
									</label>
								</div>
								<div class="control-group"
									style="float: right; margin-right: 30px;">
									<label class="control-label bolder"
										style="vertical-align: middle; padding-right: 20px;">是否支持游泳预约</label>

									<label style="padding-right: 27px;"> <input
										name="swimOrder" type="radio" class="ace"> <span
										class="lbl">是</span>
									</label> <label> <input name="swimOrder" type="radio"
										class="ace"> <span class="lbl">否</span>
									</label>
								</div>
								<div class="control-group"
									style="float: left; margin-left: 30px;">
									<label class="control-label bolder"
										style="vertical-align: middle; padding-right: 20px;">是否支持洗澡</label>
									<label style="padding-right: 27px;"> <input
										name="bathe" type="radio" class="ace"> <span
										class="lbl">是</span>
									</label> <label> <input name="bathe" type="radio"
										class="ace"> <span class="lbl">否</span>
									</label>
								</div>
								<div class="control-group"
									style="float: right; margin-right: 30px;">
									<label class="control-label bolder"
										style="vertical-align: middle; padding-right: 20px;">是否支持洗澡预约</label>

									<label style="padding-right: 27px;"> <input
										name="batheOrder" type="radio" class="ace"> <span
										class="lbl">是</span>
									</label> <label> <input name="batheOrder" type="radio"
										class="ace"> <span class="lbl">否</span>
									</label>
								</div>
								<div class="control-group"
									style="float: left; margin-left: 30px;">
									<label class="control-label bolder"
										style="vertical-align: middle; padding-right: 20px;">预约打印小票</label>

									<label style="padding-right: 27px;"> <input
										name="printReceipt" type="radio" class="ace"> <span
										class="lbl">是</span>
									</label> <label> <input name="printReceipt" type="radio"
										class="ace"> <span class="lbl">否</span>
									</label>

								</div>
								<div class="control-group"
									style="float: right; margin-right: 30px;">
									<label class="control-label bolder"
										style="vertical-align: middle; padding-right: 20px;">自动接收预约</label>

									<label style="padding-right: 27px;"> <input
										name="autoReceive" type="radio" class="ace"> <span
										class="lbl">是</span>
									</label> <label> <input name="autoReceive" type="radio"
										class="ace"> <span class="lbl">否</span>
									</label>
								</div>
								<div style="float: left; margin-left: 30px; width: 30%;">
									<label class="control-label bolder"
										style="vertical-align: middle; padding-right: 20px;">上班时间</label>
									<div class="input-group bootstrap-timepicker">
										<input id="timepickerOn" type="text" name="openingHours" /> <span
											class="input-group-addon"> <i
											class="fa fa-clock-o bigger-110"></i>
										</span>
									</div>
								</div>
								<div style="float: right; margin-right: 30px; width: 30%;">
									<label class="control-label bolder"
										style="vertical-align: middle; padding-right: 20px;">下班时间</label>
									<div class="input-group bootstrap-timepicker">
										<input id="timepickerOff" type="text" name="closingHours"/> <span
											class="input-group-addon"> <i
											class="fa fa-clock-o bigger-110"></i>
										</span>
									</div>
								</div>
								<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
								<div>
									<!--店铺说明  -->
									<div
										style="background-color: #438EB9; color: white; font-size: 16px; padding: 5px;">店铺说明</div>
								</div>
								<!--店铺说明  -->
								<div>
									<textarea class="form-control" id="form-field-8"
										placeholder="请输入..." style="height: 63px;" name="description"></textarea>
								</div>
							</div>

						</form>
						<div
							style="margin: 30px auto; padding: 10px 0px; text-align: center; clear: both;">
							<button class="btn btn-info" type="button">
								<i class="ace-icon fa fa-check bigger-110"></i> 确认添加
							</button>

							&nbsp; &nbsp; &nbsp;
							<button class="btn" type="reset">
								<i class="ace-icon fa fa-undo bigger-110"></i> 取消
							</button>
						</div>
					</div>

				</div>
				<!-- ================= -->
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
				if (id == null || id == '') {
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
						editformbutton : true,
						editOptions : {
							url : '${ctx}/store/update',
							closeAfterEdit : true,
							//recreateForm: true,
							//beforeShowForm:beforeEditCallback
							//afterShowForm:$.GridUtils.afterEditCallback,
							afterSubmit : function(response, formid) {
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
						closeAfterEdit : true,
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

			function beforeEditCallback() {
				alert('beforeEdit');
			}
			function afterSubmitCallback(response, formid) {
				var data = JSON.parse(response.responseText);
				console.log(data);
				if (data.result) {
					return [ true, "OK", data.id ];
				} else {
					return [ false, data.errmsg ];
				}
			}

			function selectFile() {
				alert(1);
				//触发 文件选择的click事件  
				$("#file").trigger("click");

				//其他code如 alert($("#file").attr("value"))  
			}

			/*==================百度地图  */
			var map = new BMap.Map("container"); //在container容器中创建一个地图,参数container为div的id属性;

			var point = new BMap.Point(120.2, 30.25); //创建点坐标

			map.centerAndZoom(point, 14); //初始化地图，设置中心点坐标和地图级别

			map.enableScrollWheelZoom(); //激活滚轮调整大小功能

			map.addControl(new BMap.NavigationControl()); //添加控件：缩放地图的控件，默认在左上角；

			map.addControl(new BMap.MapTypeControl()); //添加控件：地图类型控件，默认在右上方；

			map.addControl(new BMap.ScaleControl()); //添加控件：地图显示比例的控件，默认在左下方；

			map.addControl(new BMap.OverviewMapControl()); //添加控件：地图的缩略图的控件，默认在右下方； TrafficControl    

			var search = new BMap.LocalSearch("中国", {

				onSearchComplete : function(result) {

					if (search.getStatus() == BMAP_STATUS_SUCCESS) {

						var res = result.getPoi(0);

						var point = res.point;

						map.centerAndZoom(point, 11);

					}

				},
				renderOptions : { //结果呈现设置，

					map : map,

					autoViewport : true,

					selectFirstResult : true

				},
				onInfoHtmlSet : function(poi, html) {//标注气泡内容创建后的回调函数，有了这个，可以简单的改一下返回的html内容了。

					// alert(html.innerHTML)

				}//这一段可以不要，只不过是为学习更深层次应用而加入的。

			});

			$('#gps').click(function setCity() {
				alert(document.getElementById("cityName").value);
				search.search(document.getElementById("cityName").value);

			});

			search.search(document.getElementById("cityName").value);
			/*==================百度地图  */
			//上班时间选择
			$('#timepickerOn').timepicker({
				minuteStep : 1,
				secondStep : 1,
				showSeconds : true,
				showMeridian : false,
				disableFocus : true,
				icons : {
					up : 'fa fa-chevron-up',
					down : 'fa fa-chevron-down'
				}
			}).on('focus', function() {
				$('#timepickerOn').timepicker('showWidget');
			}).next().on(ace.click_event, function() {
				$(this).prev().focus();
			});

			//下班时间选择
			$('#timepickerOff').timepicker({
				minuteStep : 1,
				secondStep : 1,
				showSeconds : true,
				showMeridian : false,
				disableFocus : true,
				icons : {
					up : 'fa fa-chevron-up',
					down : 'fa fa-chevron-down'
				}
			}).on('focus', function() {
				$('#timepickerOff').timepicker('showWidget');
			}).next().on(ace.click_event, function() {
				$(this).prev().focus();
			});
		    
			$('#id-input-file-1 , #id-input-file-2').ace_file_input({
                no_file:'No File ...',
                btn_choose:'Chosen',
                btn_change:'Change',
                droppable:false,
                onchange:null,
                thumbnail:false, 
                //| true | large
                whitelist:'gif|png|jpg|jpeg'
                //blacklist:'exe|php'
                //onchange:''
                //
            });

		});
	</script>
</body>
</html>