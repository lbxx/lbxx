<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="sidebar" id="sidebar">

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>



				<div class="sidebar-shortcuts" id="sidebar-shortcuts">

					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">

						<button class="btn btn-success">

							<i class="icon-signal"></i>

						</button>



						<button class="btn btn-info">

							<i class="icon-pencil"></i>

						</button>



						<button class="btn btn-warning">

							<i class="icon-group"></i>

						</button>



						<button class="btn btn-danger">

							<i class="icon-cogs"></i>

						</button>

					</div>



					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">

						<span class="btn btn-success"></span> <span class="btn btn-info"></span>



						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>

					</div>

				</div>
				<!-- #sidebar-shortcuts -->



				<ul class="nav nav-list">

					<li class="active"><a href="${ctx}/index/index"> <i
							class="icon-dashboard"></i> <span class="menu-text"> 控制台 </span>

					</a>
					</li>
					<c:forEach items="${sessionScope.menuList}" var="onemenu">
						<li><a href="${ctx}${onemenu.url}" class="dropdown-toggle"> <i
							class="icon-desktop"></i> <span class="menu-text"> ${onemenu.name} </span>
							<c:if test="${not empty onemenu.menuList}">
								<b class="arrow icon-angle-down"></b>
							</c:if>

					</a>
					<ul class="submenu">
						<c:forEach items="${onemenu.menuList}" var="twomenu">
								<li><a href="${ctx}${twomenu.url}" class="dropdown-toggle"> <i
										class="icon-double-angle-right"></i> ${twomenu.name}
										<c:if test="${not empty twomenu.menuList}">
											<b class="arrow icon-angle-down"></b>
										</c:if>

								</a>
								<ul class="submenu">
									<c:forEach items="${twomenu.menuList}" var="threemenu">
												<li><a href="${ctx}${threemenu.url}"> <i class="icon-leaf"></i> ${threemenu.name}
												</a></li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					  </ul>
					 </li>
					</c:forEach>
				</ul>
				<!-- /.nav-list -->



				<div class="sidebar-collapse" id="sidebar-collapse">

					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>

				</div>



				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>

			</div>
