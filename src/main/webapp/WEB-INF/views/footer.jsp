<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="footer" id="footer">

	<script type="text/javascript">
		try {
			ace.settings.check('footer', 'fixed')
		} catch (e) {
		}
	</script>
	<div class="footer-content" 
	style="position: fixed;bottom: 0;text-align: center;margin: 0 auto;width:100%;padding:5px;background-color:#87b87f;color:white;">
		<span class="bigger-120" style=""> 地址：成都市金牛区大成市场&nbsp;
			&nbsp;客服电话：028-85561688 </span> <br /> <span class="bigger-120">
			版权所有 @2017 成都竹之源纸业有限公司 </span>
	</div>

	<script type="text/javascript">
		try {
			ace.settings.check('footer', 'collapsed')
		} catch (e) {
		}
	</script>

</div>
