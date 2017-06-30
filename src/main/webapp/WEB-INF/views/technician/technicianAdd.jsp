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

.noInput {
	color: red;
}
</style>
<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script> -->
<script
	src="http://api.map.baidu.com/api?v=2.0&ak=EZPCgQ6zGu6hZSmXlRrUMTpr"></script>
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
							<li><a href="#">预约管理</a></li>
							<li class="active">技师设置</li>
						</ul>

					</div>
					<ul class="breadcrumb"
						style="padding-left: 10px;; line-height: 40px;">
						<li id="subTitle">技师设置</li>
					</ul>
					<!-- .breadcrumb -->
				</div>
				<!-- 当前页定位结束 -->
				<!-- 右边内容开始 -->
				<div class="page-content">
					<div class="row"
						style="width: 1100px; height: 768px; overflow: hidden;">
						<!-- ================= -->
						<form id="technicianForm" name="technicianForm"
							class="form-horizontal" role="form" enctype="multipart/form-data"
							action="${ctx}/technician/add" method="POST">
							<input type="hidden" name="id" id="id" value="" />
							<!-- -->
							<div style="border: 1px solid #797979; border-bottom: none;">
								<div style="display: inline-block;">
								<label style="line-height: 2em; width: 15%;">技师姓名:</label>
								<input type="text" id="name" name="name" placeholder="请输入技师姓名 "
                                        class="required" style="height: 32px; width: 56%;">
                                </div>
								<div style="display: inline-block;">
								<label style="line-height: 2em; width: 15%;">技师姓名:</label>
								<input type="text" id="name" name="name" placeholder="请输入技师姓名 "
                                        class="required" style="height: 32px; width: 56%;">
                                </div>
							</div>
							<div
								style="margin: 50px auto 0 auto; padding-top: 10px; text-align: center; clear: both;">
								<input type="submit" id="submit" class="btn btn-info"
									value="确认添加" /> <i class="ace-icon fa fa-check bigger-110"></i>

								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset" id="cancelBtn">
									<i class="ace-icon fa fa-undo bigger-110"></i> 取消
								</button>
							</div>
						</form>

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

	<script src="${ctx}/resources/js/jquery.validate.min.js"></script>
	<script src="${ctx}/resources/js/messages_zh.js"></script>
	<script src="${ctx}/resources/js/jquery.form.min.js"></script>
	<script type="text/javascript">
		/*======表单校验 */
		var isFirstload = true;
		jQuery(function($) {

			loadStoreinfo();
			/*======表单校验 */
			$.validator.setDefaults({
				debug : false,
				submitHandler : function() {
					submitForm();
				}
			});
			// 手机号码验证
			jQuery.validator
					.addMethod(
							"isMobile",
							function(value, element) {
								var length = value.length;
								var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
								return this.optional(element)
										|| (length == 11 && mobile.test(value));
							}, "请正确填写您的手机号码");

			// 座机验证
			jQuery.validator.addMethod("telephone", function(value, element) {
				var tel = /^(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9}) $/g;
				return this.optional(element) || (tel.test(value));
			}, "座机号码格式错误:021-10101010!");

			$("#storeForm").validate({
				errorClass : "noInput",
				rules : {
					cellphone : {
						required : true,
						minlength : 11,
						isMobile : true
					},
					telephone : {
						required : true,
						minlength : 9,
						maxlength : 12,
						telephone : true
					}
				},
				messages : {
					cellphone : {
						required : "请输入手机号",
						minlength : "确认手机不能小于11个字符",
						isMobile : "请正确填写您的手机号码"
					},
					telephone : {
						required : "请输入座机号",
						minlength : "座机号最少9位",
						maxlength : "座机号最多12位",
						telephone : "请正确填写座机号"
					}

				}
			/* ,
					         errorPlacement : function(error, element) {  
					                error.appendTo(element.next().next());  
					            } */
			});

			function loadStoreinfo() {
				var storeid = queryValueByKey("storeid");
				if (storeid) {
					$("#submit").val("确认更新");
					$("#subTitle").text("更新门店");
					$("#id").val(storeid);
					$("#storeForm").attr("action", "${ctx}/store/update");
					$.ajax({
						url : "${ctx}/store/query?id=" + storeid,
						type : 'GET',
						headers : {
							Accept : "application/json",
						},
						success : function(data, textStatus) {
							var store = data[0];
							loadChainStoreList(function() {
								$("#chainstoreselect").val(store.chainstoreid);
							});

							$("#storename").val(store.name);
							$("#cellphone").val(store.cellphone);
							$("#telephone").val(store.telephone);
							$("#cityName").val(store.location);
							$("#description").val(store.description);
							if (store.pic) {
								$("#storeimg").attr("src",
										"${ctx}/upload" + store.pic);
							}
							loadMap(store.longitude, store.latitude);

							map.addEventListener("tilesloaded", function() {
								if (isFirstload) {
									$("#cityName").val(store.location);
									isFirstload = false;
								}

							});
							getStoreBusiness(storeid);

						},
						error : function(data, textStatus, errorThrown) {
						},
					});
				} else {
					//add store
					loadChainStoreList();
					loadBusiness();
					loadMap();
				}
			}
			function submitForm() {
				var url = $("#storeForm").attr('action');
				var ajax_option = {
					url : url,
					success : function(data) {
						if (data.result) {
							if (data.code == "OPER_UPDATE") {

							}
							location.href = "${ctx}/store/index";
						}
					},
					error : function() {
						alert('操作失败!');
						return false;
					}
				};
				$('#storeForm').ajaxSubmit(ajax_option);
			}
			function getStoreBusiness(storeid) {
				$.ajax({
					url : "${ctx}/store/queryStoreBusiness?id=" + storeid,
					type : 'GET',
					headers : {
						Accept : "application/json",
					},
					success : function(data, textStatus) {
						var storeBusiness = data;
						var bCheck = $("#businessBox [name='business']");
						loadBusiness(function() {
							for (id in storeBusiness) {
								$("#business" + storeBusiness[id]).attr(
										"checked", "checked");
							}
						});
					},
					error : function() {
					}
				});
			}
			function loadChainStoreList(competion) {
				$
						.ajax({
							url : "${ctx}/store/option",
							type : 'GET',
							headers : {
								/* Accept: "application/xml", */
								Accept : "application/json",
							},
							success : function(data, textStatus) {
								var selection1 = data[0];
								for (var i = 0; i < selection1.length; i++) {
									$('#chainstoreselect').append(
											"<option value='"+selection1[i].id+"'>"
													+ selection1[i].name
													+ "</option>");
								}
								if (null != competion) {
									competion();
								}
							},
							error : function(data, textStatus, errorThrown) {
							},
						});
			}

			/*========获取业务  */
			function loadBusiness(completion) {
				$
						.ajax({
							url : "${ctx}/store/businessList",
							type : 'GET',
							headers : {
								/* Accept: "application/xml", */
								Accept : "application/json",
							},
							success : function(data, textStatus) {
								$('#businessBox').html("");
								for (var i = 0; i < data.length; i++) {
									$('#businessBox')
											.append(
													"<label><input type='checkbox' id='business"+data[i].id+"' name='business' value='"+data[i].id+"'/>"
															+ data[i].name
															+ "</label>&nbsp;&nbsp;&nbsp;&nbsp;");
								}
								if (null != completion) {
									completion();
								}
							},
							error : function(data, textStatus, errorThrown) {
							},
						});
			}

			/*========获取业务  */
			function G(id) {
				return document.getElementById(id);
			}

			function loadMap(lng, lat) {
				map = new BMap.Map("container");
				var mapType1 = new BMap.MapTypeControl({
					mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
				});
				var mapType2 = new BMap.MapTypeControl({
					anchor : BMAP_ANCHOR_TOP_RIGHT
				});
				var point = new BMap.Point(116.404, 39.915); //创建点坐标

				if (lng && lat && lng != 0 && lat != 0) {
					point = new BMap.Point(lng, lat); //创建点坐标
					var marker = new BMap.Marker(point);
					map.clearOverlays();
					map.addOverlay(marker);
				}

				map.centerAndZoom(point, 14); //初始化地图，设置中心点坐标和地图级别

				map.enableScrollWheelZoom(); //激活滚轮调整大小功能

				map.addControl(new BMap.NavigationControl()); //添加控件：缩放地图的控件，默认在左上角；
				map.addEventListener("click", showInfo);
				map.addControl(mapType1); //2D图，卫星图
				map.addControl(mapType2);

				map.addControl(new BMap.ScaleControl()); //添加控件：地图显示比例的控件，默认在左下方；

				map.addControl(new BMap.OverviewMapControl()); //添加控件：地图的缩略图的控件，默认在右下方； TrafficContro
			}
			/*==================百度地图  */
			var myValue;
			var map = null; //在container容器中创建一个地图,参数container为div的id属性;

			var geoc = new BMap.Geocoder();

			var ac = new BMap.Autocomplete( //建立一个自动完成的对象
			{
				"input" : "cityName",
				"location" : map
			});

			ac.addEventListener("onhighlight", onhighlight); //鼠标放在下拉列表上的事件
			ac.addEventListener("onconfirm", onconfirm); //鼠标点击下拉列表后的事件

			function onhighlight(e) {
				var str = "";
				var _value = e.fromitem.value;
				var value = "";
				if (e.fromitem.index > -1) {
					value = _value.province + _value.city + _value.district
							+ _value.street + _value.business;
				}
				str = "FromItem<br />index = " + e.fromitem.index
						+ "<br />value = " + value;

				value = "";
				if (e.toitem.index > -1) {
					_value = e.toitem.value;
					value = _value.province + _value.city + _value.district
							+ _value.street + _value.business;
				}
				str += "<br />ToItem<br />index = " + e.toitem.index
						+ "<br />value = " + value;
				G("searchResultPanel").innerHTML = str;
			}

			function onconfirm(e) {
				var _value = e.item.value;
				myValue = _value.province + _value.city + _value.district
						+ _value.street + _value.business;
				G("searchResultPanel").innerHTML = "onconfirm<br />index = "
						+ e.item.index + "<br />myValue = " + myValue;
				setPlace();
			}

			function setPlace() {
				map.clearOverlays(); //清除地图上所有覆盖物
				function myFun() {
					var pp = local.getResults().getPoi(0).point; //获取第一个智能搜索的结果
					$('#lng').val(pp.lng);
					console.log($('#lng').val());
					$('#lat').val(pp.lat);
					map.centerAndZoom(pp, 18);
					map.addOverlay(new BMap.Marker(pp)); //添加标注
				}
				var local = new BMap.LocalSearch(map, { //智能搜索
					onSearchComplete : myFun
				});
				local.search(myValue);
			}
			/*==============  */
			function showInfo(e) {
				var lng = e.point.lng;
				var lat = e.point.lat;
				$('#lng').val(lng);
				$('#lat').val(lat);
				var marker = new BMap.Marker(new BMap.Point(lng, lat));
				map.clearOverlays();
				map.addOverlay(marker);
				var pt = e.point;
				geoc.getLocation(pt, function(rs) {
					var addComp = rs.addressComponents;
					var address = addComp.province + addComp.city
							+ addComp.district + addComp.street
							+ addComp.streetNumber;
					$('#cityName').val(address);
					//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
				});
			}

			var search = new BMap.LocalSearch("中国", {

				onSearchComplete : function(result) {

					if (search.getStatus() == BMAP_STATUS_SUCCESS) {

						var res = result.getPoi(0);

						var point = res.point;

						$("#lng").val(point.lng);

						$("#lat").val(point.lat);

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
				search.search(document.getElementById("cityName").value);
			});

			//search.search(document.getElementById("cityName").value);
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

		});

		$("#cancelBtn").click(function() {
			location.href = "${ctx}/store/listIndex";
		});

		//页面图上上传预览//
		//图片上传预览    IE是用了滤镜。
		function previewImage(file) {
			var MAXWIDTH = 382;
			var MAXHEIGHT = 350;
			var div = document.getElementById('preview');
			if (file.files && file.files[0]) {
				div.innerHTML = '<img id=storeimg>';
				var img = document.getElementById('storeimg');
				img.onload = function() {
					var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
							img.offsetWidth, img.offsetHeight);
					img.width = rect.width;
					img.height = rect.height;
					//                 img.style.marginLeft = rect.left+'px';
					//img.style.marginTop = rect.top+'px';
				}
				var reader = new FileReader();
				reader.onload = function(evt) {
					img.src = evt.target.result;
				}
				reader.readAsDataURL(file.files[0]);
			} else //兼容IE
			{
				var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
				file.select();
				var src = document.selection.createRange().text;
				div.innerHTML = '<img id=storeimg>';
				var img = document.getElementById('storeimg');
				img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
						img.offsetWidth, img.offsetHeight);
				status = ('rect:' + rect.top + ',' + rect.left + ','
						+ rect.width + ',' + rect.height);
				div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
			}
		}
		function clacImgZoomParam(maxWidth, maxHeight, width, height) {
			var param = {
				top : 0,
				left : 0,
				width : width,
				height : height
			};
			if (width > maxWidth || height > maxHeight) {
				rateWidth = width / maxWidth;
				rateHeight = height / maxHeight;

				if (rateWidth > rateHeight) {
					param.width = maxWidth;
					param.height = Math.round(height / rateWidth);
				} else {
					param.width = Math.round(width / rateHeight);
					param.height = maxHeight;
				}
			}

			param.left = Math.round((maxWidth - param.width) / 2);
			param.top = Math.round((maxHeight - param.height) / 2);
			return param;
		}
		//页面图上上传预览//

		function queryValueByKey(name) {
			var result = window.location.search.match(new RegExp("[\?\&]"
					+ name + "=([^\&]+)", "i"));
			if (result == null || result.length < 1) {
				return null;
			}
			var value = decodeURIComponent(result[1]);
			if ("null" == value) {
				value = null;
			}
			return value;
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
	</script>

</body>
</html>