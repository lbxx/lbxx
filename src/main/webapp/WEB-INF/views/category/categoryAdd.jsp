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
						<li id="subTitle">分类设置</li>
					</ul>
					<!-- .breadcrumb -->
				</div>
				<!-- 当前页定位结束 -->
				<!-- 右边内容开始 -->
				<div class="page-content">
					<div class="row"
						style="width: 1100px; height: 768px; overflow: hidden;">
						<!-- ================= -->
						<form id="categoryForm" name="categoryForm"
							class="form-horizontal" role="form" 
							action="${ctx}/category/save" method="POST">
							<input type="hidden" name="id" id="id" value="" />
							<!-- -->
							<div style="border: 1px solid #797979; padding: 10px;">
								<div style="display: inline-block; width: 40%; margin: 3px;">
									<label style="line-height: 2em; width: 20%; font-weight: bold;">分类名称:</label>
									<input type="text" id="name" name="name" placeholder="请输入分类名称"
										class="required" style="height: 32px; width: 70%;">
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

			loadCategorynfo();
			
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

		$("#categoryForm").validate({
			errorClass : "noInput",
			rules : {
				name : {
					required : true
				}
			},
			messages : {
				name : {
					required : "这是一个必填字段"
				}
			}
		/* ,
		                 errorPlacement : function(error, element) {  
		                        error.appendTo(element.next().next());  
		                    } */
		});

		function loadCategorynfo() {
				var categoryid = queryValueByKey("categoryid");
	            if (categoryid) {
	                /* $("#subTitle").text("更新门店"); */
	                $("#id").val(categoryid);
	                $.ajax({
	                    url : "${ctx}/category/categoryInfo?categoryid=" + categoryid,
	                    type : 'GET',
	                    headers : {
	                        Accept : "application/json",
	                    },
	                    success : function(data, textStatus) {
	                        $("#id").val(data.id);
	                        $("#name").val(data.name);
	                        

	                    },
	                    error : function(data, textStatus, errorThrown) {
	                    },
	                });
	            } else {
	            }
			
		}
		function submitForm() {
			var url = $("#categoryForm").attr('action');
			var ajax_option = {
				url : url,
				dataType:'json',
				success : function(data) {
					if (data.result) {
						location.href = "${ctx}/category/listIndex";
					}
				},
				error : function() {
					alert('操作失败!');
					return false;
				}
			};
			$('#categoryForm').ajaxSubmit(ajax_option);
		}

		/*========获取业务  */
		$("#cancelBtn").click(function() {
			location.href = "${ctx}/category/listIndex";
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