<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
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
					<jsp:include page="../location.jsp" />
					<ul class="breadcrumb"
						style="padding-left: 10px;; line-height: 40px;">
						<li id="subTitle">技师请假</li>
					</ul>
					<!-- .breadcrumb -->
				</div>
				<!-- 当前页定位结束 -->
				<!-- 右边内容开始 -->
				<div class="page-content">
					<div class="row"
						style="width: 1100px; height: 768px; overflow: hidden;">
						<!-- ================= -->
						<form id="technicianLeaveForm" name="technicianLeaveForm"
							class="form-horizontal" role="form" action="${ctx}/technicianLeave/save" method="POST">
							<input type="hidden" name="id" id="id" value="" />
							<!-- -->
							<div style="border: 1px solid #797979; padding: 10px;">

								<div style="width: 40%; margin: 3px;">
									<label style="line-height: 2em; width: 20%; font-weight: bold;">技师名称:</label>
									<select id="technicianselect" style="width: 200px;"
										name="technicianid">
									</select>
								</div>
								<div style="display: inline-block; width: 35%; margin: 3px;">
									<label style="line-height: 2em; width: 25%; font-weight: bold;">请假开始时间:</label>
									<div class="input-group" style="display: inline-block;">
										<input id="starttime" name="starttime" type="text" readonly="readonly"
                                            style="width: 200px;" 
                                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
									</div>
								</div>
								<div style="display: inline-block; width: 35%; margin: 3px;">
									<label style="line-height: 2em; width: 25%; font-weight: bold;">请假结束时间:</label>
									<div class="input-group "
										style="display: inline-block;">
										<input id="endtime" name="endtime" type="text" readonly="readonly"
											style="width: 200px;" 
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
									</div>
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
	<script src="${ctx}/resources/js/date-time/moment.min.js"></script>
	<%-- <script src="${ctx}/resources/js/date-time/bootstrap-datetimepicker.min.js"></script> --%>
	<script src="${ctx}/resources/DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/resources/js/messages_zh.js"></script>
	<script src="${ctx}/resources/js/jquery.form.min.js"></script>
	<script type="text/javascript">
		/*======表单校验 */
		var isFirstload = true;
		jQuery(function($) {

			loadTechnicianSelect();
			loadTechnicianLeaveInfo();

		});
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

		$("#technicianLeaveForm").validate({
			errorClass : "noInput",
			rules : {
				starttime : {
					required : true
				},
				endtime : {
                    required : true
                }
			},
			messages : {
				starttime : {
					required : "这是一个必填字段"
				},
				endtime : {
                    required : "这是一个必填字段"
                }
			}
		/* ,
		                 errorPlacement : function(error, element) {  
		                        error.appendTo(element.next().next());  
		                    } */
		});

		function loadTechnicianSelect() {
			$.ajax({
				url : "${ctx}/technician/technicians",
				type : 'GET',
				headers : {
					/* Accept: "application/xml", */
					Accept : "application/json",
				},
				success : function(data, textStatus) {
					$('#technicianselect').html("");
					for (var i = 0; i < data.length; i++) {
						$('#technicianselect').append(
								"<option value='"+data[i].id+"'>"
										+ data[i].name + "</option>");
					}

				},
				error : function(data, textStatus, errorThrown) {
				},
			});
		}
		
		function loadTechnicianLeaveInfo() {
            var technicianleaveid = queryValueByKey("technicianleaveid");
            if (technicianleaveid) {
                $("#id").val(technicianleaveid);
                $.ajax({
                    url : "${ctx}/technicianLeave/technicianLeaveInfo?technicianleaveid=" + technicianleaveid,
                    type : 'GET',
                    headers : {
                        Accept : "application/json",
                    },
                    success : function(data, textStatus) {
                        $("#technicianselect").val(data.technicianid);
                        $("#starttime").val(formateTimeStamp(data.starttime));
                        $("#endtime").val(formateTimeStamp(data.endtime));
                    },
                    error : function(data, textStatus, errorThrown) {
                    },
                });
            } else {
            }
        
    }
		function submitForm() {
			var url = $("#technicianLeaveForm").attr('action');
			var ajax_option = {
				url : url,
				dataType : 'json',
				success : function(data) {
					if (data.result) {
						location.href = "${ctx}/technicianLeave/listIndex";
					}else{
                        alert(data.msg);
                        return false;
                    }
				},
				error : function() {
					alert('操作失败!');
					return false;
				}
			};
			$('#technicianLeaveForm').ajaxSubmit(ajax_option);
		}

		/*========获取业务  */
		$("#cancelBtn").click(function() {
			location.href = "${ctx}/technicianLeave/listIndex";
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
	    //时间戳转格式化时间
		function formateTimeStamp(inputTime) {    
	        var date = new Date(inputTime);  
	        var y = date.getFullYear();    
	        var m = date.getMonth() + 1;    
	        m = m < 10 ? ('0' + m) : m;    
	        var d = date.getDate();    
	        d = d < 10 ? ('0' + d) : d;    
	        var h = date.getHours();  
	        h = h < 10 ? ('0' + h) : h;  
	        var minute = date.getMinutes();  
	        var second = date.getSeconds();  
	        minute = minute < 10 ? ('0' + minute) : minute;    
	        second = second < 10 ? ('0' + second) : second;   
	        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
	    }    
		//上班时间选择

		//下班时间选择
	</script>

</body>
</html>