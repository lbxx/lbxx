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
					<!-- <div class="breadcrumbs ace-save-state">
						<li
							style="list-style: none; padding-left: 20px; font-size: 16px; float: left;"><span
							class="dropdown-toggle"> <i class=""> </i>店铺管理
						</span></li>
						<ul class="breadcrumb" style="float: right; line-height: 40px;">
							您的当前位置:
							<li><a href="#">预约管理</a></li>
							<li class="active">技师设置</li>
						</ul>

					</div> -->
					<jsp:include page="../location.jsp" />
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
							action="${ctx}/technician/save" method="POST">
							<input type="hidden" name="id" id="id" value="" />
							<!-- -->
							<div style="border: 1px solid #797979; padding: 10px;">
								<div style="display: inline-block; width: 40%; margin: 3px;">
									<label style="line-height: 2em; width: 20%; font-weight: bold;">技师姓名:</label>
									<input type="text" id="name" name="name" placeholder="请输入技师姓名 "
										class="required" style="height: 32px; width: 70%;">
								</div>

								<div style="display: inline-block; width: 40%; margin: 3px;">
									<label style="line-height: 2em; width: 20%; font-weight: bold;">所属门店:</label>
									<select id="chainstoreselect" style="width: 130px;"
										name="chainstoreid">
										<!-- <option value="">1</option> -->
									</select> <select id="storeselect" style="width: 200px;" name="storeid">
									</select>
								</div>

								<div style="display: inline-block; width: 40%; margin: 3px;">
									<label style="line-height: 2em; width: 20%; font-weight: bold;">技师手机:</label>
									<input type="text" id="cellphone" name="cellphone"
										maxlength="11" placeholder="请输入技师手机" class="required"
										style="height: 32px; width: 70%;">
								</div>
								<div style="display: inline-block; width: 40%; margin: 3px;">
									<label style="line-height: 2em; width: 20%; font-weight: bold;">技师性别:</label>
									<select style="display: inline-block; width: 130px;"
										id="gender" name="gender">
										<option value="1" selected="selected">男</option>
										<option value="2">女</option>
									</select>
								</div>
								<div style="display: inline-block; width: 50%; margin: 3px;">
									<label style="line-height: 2em; width: 15%; font-weight: bold;">上班时间:</label>
									<div
										style="width: 45%; display: inline-block; vertical-align: top; padding-left: 5px;"
										id="dayCheckbox">
										<label><input 
											id="day1" name="workdays" type="checkbox" value="1" />星期一 </label> <label><input
											id="day2" name="workdays" type="checkbox" value="2" />星期二 </label> <label><input
											id="day3" name="workdays" type="checkbox" value="3" />星期三 </label> <label><input
											id="day4" name="workdays" type="checkbox" value="4" />星期四 </label> <label><input
											id="day5" name="workdays" type="checkbox" value="5" />星期五 </label> <label><input
											id="day6" name="workdays" type="checkbox" value="6" />星期六 </label> <label><input
											id="day7" name="workdays" type="checkbox" value="7" />星期日 </label>
									</div>
									<input type="hidden" name="" />
								</div>
								<div style="display: inline-block; width: 100%; margin: 3px;">
									<label style="line-height: 2em; width: 8%; font-weight: bold;">时间设置:</label>
									<!-- <div style="display: inline-block;width: 30%;">
                                                                                                            婴儿洗澡时间:<input type="text" id="name" name="name" 
                                        class="required" style="height: 32px;">
                                    </div>
                                    <div style="display: inline-block;width: 30%;">
                                                                                                            婴儿游泳时间:<input type="text" id="name" name="name" 
                                        class="required" style="height: 32px;">
                                    </div> -->
									<div id="storeBusiness"
										style="border: 1px #D5D5D5 solid; width: 80%; height: 100px; overflow-y: scroll;"></div>
								</div>
								<div style="display: inline-block; width: 40%; margin: 3px;">
									<label style="line-height: 2em; width: 20%; font-weight: bold;">技师说明:</label>
									<textarea rows="10" name="description" cols="150"
										placeholder="请输入技师说明..." style="overflow: hidden;"></textarea>
								</div>
							</div>
							<div
								style="margin: 50px auto 0 auto; padding-top: 10px; text-align: center; clear: both;">
								<input type="submit" id="submit" class="btn btn-info" value="提交" />
								<i class="ace-icon fa fa-check bigger-110"></i> &nbsp; &nbsp;
								&nbsp;
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

			loadTechnicianinfo();
			/*======表单校验 */
			$.validator.setDefaults({
				debug : false,
				submitHandler : function() {
					submitForm();
				}
			});
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

		$("#technicianForm").validate({
			errorClass : "noInput",
			rules : {
				cellphone : {
					required : true,
					minlength : 11,
					isMobile : true
				},
				name : {
					required : true
				}
			},
			messages : {
				cellphone : {
					required : "请输入手机号",
					minlength : "确认手机不能小于11个字符",
					isMobile : "请正确填写您的手机号码"
				},
				name : {
					required : "这是一个必填字段"
				}
			}
		/* ,
		                 errorPlacement : function(error, element) {  
		                        error.appendTo(element.next().next());  
		                    } */
		});

		function loadTechnicianinfo() {
			loadChainStoreList(function () {
				var technicianid = queryValueByKey("technicianid");
	            if (technicianid) {
	                /* $("#subTitle").text("更新门店"); */
	                $("#id").val(technicianid);
	                $.ajax({
	                    url : "${ctx}/technician/technicianInfo?id=" + technicianid,
	                    type : 'GET',
	                    headers : {
	                        Accept : "application/json",
	                    },
	                    success : function(data, textStatus) {
	                        //data中返回三个数组,第一个为 technician 相关数据
	                        var technician = data[0];
	                        var technicianBusiness = data[1];
	                        var store = data[2];
	                        var workdays =technician.workday.split(',');
	                         var dayCheck = $("#dayCheckbox [name='workdays']");
	                        for(i in workdays){
	                            $("#day"+workdays[i]).attr("checked","checked");
	                        }
	                        if(store){
	                            $("#chainstoreselect").val(store.chainstoreid);
	                            loadStoreList();
	                            $("#storeselect").val(store.id);
	                            }
	                        if(technicianBusiness){
	                        	for(i in technicianBusiness){
	                        		$("#businessTime_"+technicianBusiness[i].technicianid).val(technicianBusiness[i].spend);
	                        	}
	                        }
	                        return;

	                        $("#name").val(technician.name);
	                        $("#cellphone").val(technician.cellphone);
	                        $("#cityName").val(store.location);
	                        $("#description").val(technician.description);
	                        
	                        getStoreBusiness(storeid);

	                    },
	                    error : function(data, textStatus, errorThrown) {
	                    },
	                });
	            } else {
	                //add store
	                loadChainStoreList();
	                loadBusiness();
	            }
			});
			
		}
		function submitForm() {
			var url = $("#technicianForm").attr('action');
			var ajax_option = {
				url : url,
				success : function(data) {
					if (data.result) {
						location.href = "${ctx}/technician/listIndex";
					}
				},
				error : function() {
					alert('操作失败!');
					return false;
				}
			};
			$('#technicianForm').ajaxSubmit(ajax_option);
		}
		//获取门店开启的业务
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
							$("#business" + storeBusiness[id]).attr("checked",
									"checked");
						}
					});
				},
				error : function() {
				}
			});
		}
		var selection2;
		function loadChainStoreList(competion) {
			$.ajax({
				url : "${ctx}/store/option",
				type : 'GET',
				headers : {
					/* Accept: "application/xml", */
					Accept : "application/json",
				},
				success : function(data, textStatus) {
					var selection1 = data[0];
					$('#chainstoreselect').html("");
					for (var i = 0; i < selection1.length; i++) {
						$('#chainstoreselect').append(
								"<option value='"+selection1[i].id+"'>"
										+ selection1[i].name + "</option>");
					}
					if (null != competion) {
						competion();
					}
					selection2 = objOfPropertyToArr(data[1]);
					loadStoreList();
					$('#chainstoreselect').change(loadStoreList);

				},
				error : function(data, textStatus, errorThrown) {
				},
			});
		}

		function loadStoreList() {
			$('#storeselect').html("");
			var id = $('#chainstoreselect').val();
			for (var i = 0; i < selection2.length; i++) {
				for (var j = 0; j < selection2[i].length; j++) {
					if (selection2[i][j].chainstoreid == id) {
						$('#storeselect').append(
								"<option value='"+selection2[i][j].id+"'>"
										+ selection2[i][j].name + "</option>")
					}
				}
			}
			var storeId = $("#storeselect").val();
            if (storeId == null || storeId == '') {
                return;
            }
			loadStoreBusiness(storeId);
		};
	    
		//店铺下拉列表改变触发
		$("#storeselect").change(function(){
			$("#storeBusiness").html("");
			var storeId = $("#storeselect").val();
            if (storeId == null || storeId == '') {
                return;
            }
            loadStoreBusiness(storeId);
		});
		function loadStoreBusiness(storeId) {
			$.ajax({
				url : "${ctx}/technician/businessInfo?storeId="+ storeId,
				type : 'GET',
				headers : {
					Accept : "application/json",
				},
				success : function(data) {
					console.log(data);
					if(data == null || data.length == 0){
						$("#storeBusiness").html("该店铺暂未开启任何业务!");
					}else{
						for(var i=0;i<data.length;i++){
							console.log(data[i].id);
							$("#storeBusiness").append(
									'<div style="display: inline-block;width: 30%;padding-top:10px;padding-left:5px;">'+data[i].name
									+'时间:<input type="text" id="" name="businessTime_'+data[i].id+'"' 
								    +'style="height: 32px;"></div>'		
							);
						}
					}
				},
				error : function() {
				}
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
		$("#cancelBtn").click(function() {
			location.href = "${ctx}/store/listIndex";
		});

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