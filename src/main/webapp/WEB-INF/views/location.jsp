<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="breadcrumbs" id="breadcrumbs">
	<script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
	</script>
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="javascript:;">Home</a>
		</li>
		<li>
			<a href="javascript:;">${menu.name}</a>
		</li>
		<li class="active">
			<c:forEach items="${menucode}" var="operator">
				<c:if test="${menu.id == operator.id}">
					${operator.name}
				</c:if>
			</c:forEach>
		</li>
	</ul><!-- .breadcrumb -->
</div>