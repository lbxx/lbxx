<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="col-xs-12" style="display:inline-block;margin-bottom:10px;">
	<c:forEach items="${menucode}" var="operator">
		<shiro:hasPermission  name="${operator.menucode}:${operator.permission}">
		<button class="btn btn-sm btn-primary" id="${operator.permission}Button" onclick="${operator.permission}Button('${ctx}/${operator.menucode}','${operator.permission}')">${operator.name}</button>
		</shiro:hasPermission>
	</c:forEach>
</div>