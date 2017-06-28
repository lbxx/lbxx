<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- basic scripts -->
<!--[if !IE]> -->
<script src="http://www.jq22.com/jquery/jquery-2.1.1.js"></script>
<!-- <![endif]-->
<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctx}/resources/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='${ctx}/resources/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='${ctx}/resources/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
<script src="${ctx}/resources/js/typeahead-bs2.min.js"></script>
<!-- page specific plugin scripts -->
<script src="${ctx}/resources/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/resources/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="${ctx}/resources/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/resources/js/jqGrid/i18n/grid.locale-en.js"></script>
<!-- ace scripts -->
<script src="${ctx}/resources/js/ace-elements.min.js"></script>
<script src="${ctx}/resources/js/ace.min.js"></script>
<script src="${ctx}/resources/js/jquery-ui.custom.min.js"></script>
<script src="${ctx}/resources/js/chosen.jquery.min.js"></script>
<script src="${ctx}/resources/js/jquery.form.min.js"></script>
<script src="${ctx}/resources/js/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<!-- sco -->
<script src="${ctx}/resources/js/sco/sco.message.js"></script>
<!-- 下面这个是引入dialog弹窗的js -->
<script src="${ctx}/resources/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${ctx}/resources/js/jquery.ui.touch-punch.min.js"></script>
<!-- 公共跳转的js,依赖上面js -->
<script src="${ctx}/resources/js/admin.js"></script>

<!-- 弹窗dialog公共DIV-->
<div id="dialog-message" class="hide">
    <p></p>
</div>
