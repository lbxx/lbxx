<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html lang="en">

<head>

	<meta charset="utf-8" />

	<title>分页</title>



	<meta name="viewport" content="width=device-width, initial-scale=1.0" />



	<!-- basic styles -->



	<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet" />

	<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/3.2.1/css/font-awesome.min.css">



	<!--[if IE 7]>

	<link rel="stylesheet" href="${ctx}/resources/css/font-awesome-ie7.min.css" />

	<![endif]-->



	<!-- page specific plugin styles -->



	<!-- fonts -->







	<!-- ace styles -->



	<link rel="stylesheet" href="${ctx}/resources/css/ace.min.css" />

	<link rel="stylesheet" href="${ctx}/resources/css/ace-rtl.min.css" />

	<link rel="stylesheet" href="${ctx}/resources/css/ace-skins.min.css" />



	<!--[if lte IE 8]>

	<link rel="stylesheet" href="${ctx}/resources/css/ace-ie.min.css" />

	<![endif]-->



	<!-- inline styles related to this page -->



	<!-- ace settings handler -->



	<script src="${ctx}/resources/js/ace-extra.min.js"></script>



	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->



	<!--[if lt IE 9]>

	<script src="${ctx}/resources/js/html5shiv.js"></script>

	<script src="${ctx}/resources/js/respond.min.js"></script>

	<![endif]-->

</head>



<body>

<jsp:include page="../header.jsp" />


<div class="main-container" id="main-container">

	<script type="text/javascript">

        try{ace.settings.check('main-container' , 'fixed')}catch(e){}

	</script>



	<div class="main-container-inner">

		<a class="menu-toggler" id="menu-toggler" href="#">

			<span class="menu-text"></span>

		</a>


<jsp:include page="../lefter.jsp" />



		<div class="main-content">

			<div class="breadcrumbs" id="breadcrumbs">

				<script type="text/javascript">

                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}

				</script>



				<ul class="breadcrumb">

					<li>

						<i class="icon-home home-icon"></i>

						<a href="#">Home</a>

					</li>



					<li>

						<a href="#">Tables</a>

					</li>

					<li class="active">Simple &amp; Dynamic</li>

				</ul><!-- .breadcrumb -->
			</div>



			<div class="page-content">
				<div class="row">

					<div class="col-xs-12">

						<div class="hr hr-18 dotted hr-double"></div>

						<div class="hr hr-18 dotted hr-double"></div>

						<div class="row">

							<div class="col-xs-12">
								<div class="table-responsive">

									<table id="sample-table-2" class="table table-striped table-bordered table-hover">

										<thead>

										<tr>

											<th class="center">

												<label>

													<input type="checkbox" class="ace" />

													<span class="lbl"></span>

												</label>

											</th>

											<th>Domain</th>

											<th>Price</th>

											<th class="hidden-480">Clicks</th>



											<th>

												<i class="icon-time bigger-110 hidden-480"></i>

												Update

											</th>

											<th class="hidden-480">Status</th>



											<th></th>

										</tr>

										</thead>



										<tbody>

										<tr>

											<td class="center">

												<label>

													<input type="checkbox" class="ace" />

													<span class="lbl"></span>

												</label>

											</td>



											<td>

												<a href="#">best.com</a>

											</td>

											<td>$75</td>

											<td class="hidden-480">6,500</td>

											<td>Apr 03</td>



											<td class="hidden-480">

												<span class="label label-sm label-inverse arrowed-in">Flagged</span>

											</td>



											<td>

												<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">

													<a class="blue" href="#">

														<i class="icon-zoom-in bigger-130"></i>

													</a>



													<a class="green" href="#">

														<i class="icon-pencil bigger-130"></i>

													</a>



													<a class="red" href="#">

														<i class="icon-trash bigger-130"></i>

													</a>

												</div>



												<div class="visible-xs visible-sm hidden-md hidden-lg">

													<div class="inline position-relative">

														<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">

															<i class="icon-caret-down icon-only bigger-120"></i>

														</button>



														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">

															<li>

																<a href="#" class="tooltip-info" data-rel="tooltip" title="View">

																				<span class="blue">

																					<i class="icon-zoom-in bigger-120"></i>

																				</span>

																</a>

															</li>



															<li>

																<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">

																				<span class="green">

																					<i class="icon-edit bigger-120"></i>

																				</span>

																</a>

															</li>



															<li>

																<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">

																				<span class="red">

																					<i class="icon-trash bigger-120"></i>

																				</span>

																</a>

															</li>

														</ul>

													</div>

												</div>

											</td>

										</tr>



										<tr>

											<td class="center">

												<label>

													<input type="checkbox" class="ace" />

													<span class="lbl"></span>

												</label>

											</td>



											<td>

												<a href="#">pro.com</a>

											</td>

											<td>$55</td>

											<td class="hidden-480">4,250</td>

											<td>Jan 21</td>



											<td class="hidden-480">

												<span class="label label-sm label-success">Registered</span>

											</td>



											<td>

												<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">

													<a class="blue" href="#">

														<i class="icon-zoom-in bigger-130"></i>

													</a>



													<a class="green" href="#">

														<i class="icon-pencil bigger-130"></i>

													</a>



													<a class="red" href="#">

														<i class="icon-trash bigger-130"></i>

													</a>

												</div>



												<div class="visible-xs visible-sm hidden-md hidden-lg">

													<div class="inline position-relative">

														<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">

															<i class="icon-caret-down icon-only bigger-120"></i>

														</button>



														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">

															<li>

																<a href="#" class="tooltip-info" data-rel="tooltip" title="View">

																				<span class="blue">

																					<i class="icon-zoom-in bigger-120"></i>

																				</span>

																</a>

															</li>



															<li>

																<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">

																				<span class="green">

																					<i class="icon-edit bigger-120"></i>

																				</span>

																</a>

															</li>



															<li>

																<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">

																				<span class="red">

																					<i class="icon-trash bigger-120"></i>

																				</span>

																</a>

															</li>

														</ul>

													</div>

												</div>

											</td>

										</tr>



										<tr>

											<td class="center">

												<label>

													<input type="checkbox" class="ace" />

													<span class="lbl"></span>

												</label>

											</td>



											<td>

												<a href="#">right.com</a>

											</td>

											<td>$50</td>

											<td class="hidden-480">4,400</td>

											<td>Apr 01</td>



											<td class="hidden-480">

												<span class="label label-sm label-warning">Expiring</span>

											</td>



											<td>

												<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">

													<a class="blue" href="#">

														<i class="icon-zoom-in bigger-130"></i>

													</a>



													<a class="green" href="#">

														<i class="icon-pencil bigger-130"></i>

													</a>



													<a class="red" href="#">

														<i class="icon-trash bigger-130"></i>

													</a>

												</div>



												<div class="visible-xs visible-sm hidden-md hidden-lg">

													<div class="inline position-relative">

														<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">

															<i class="icon-caret-down icon-only bigger-120"></i>

														</button>



														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">

															<li>

																<a href="#" class="tooltip-info" data-rel="tooltip" title="View">

																				<span class="blue">

																					<i class="icon-zoom-in bigger-120"></i>

																				</span>

																</a>

															</li>



															<li>

																<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">

																				<span class="green">

																					<i class="icon-edit bigger-120"></i>

																				</span>

																</a>

															</li>



															<li>

																<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">

																				<span class="red">

																					<i class="icon-trash bigger-120"></i>

																				</span>

																</a>

															</li>

														</ul>

													</div>

												</div>

											</td>

										</tr>



										<tr>

											<td class="center">

												<label>

													<input type="checkbox" class="ace" />

													<span class="lbl"></span>

												</label>

											</td>



											<td>

												<a href="#">once.com</a>

											</td>

											<td>$20</td>

											<td class="hidden-480">1,400</td>

											<td>Apr 04</td>



											<td class="hidden-480">

												<span class="label label-sm label-info arrowed arrowed-righ">Sold</span>

											</td>



											<td>

												<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">

													<a class="blue" href="#">

														<i class="icon-zoom-in bigger-130"></i>

													</a>



													<a class="green" href="#">

														<i class="icon-pencil bigger-130"></i>

													</a>



													<a class="red" href="#">

														<i class="icon-trash bigger-130"></i>

													</a>

												</div>



												<div class="visible-xs visible-sm hidden-md hidden-lg">

													<div class="inline position-relative">

														<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">

															<i class="icon-caret-down icon-only bigger-120"></i>

														</button>



														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">

															<li>

																<a href="#" class="tooltip-info" data-rel="tooltip" title="View">

																				<span class="blue">

																					<i class="icon-zoom-in bigger-120"></i>

																				</span>

																</a>

															</li>



															<li>

																<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">

																				<span class="green">

																					<i class="icon-edit bigger-120"></i>

																				</span>

																</a>

															</li>



															<li>

																<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">

																				<span class="red">

																					<i class="icon-trash bigger-120"></i>

																				</span>

																</a>

															</li>

														</ul>

													</div>

												</div>

											</td>

										</tr>

										</tbody>

									</table>

								</div>

							</div>

						</div>
					</div><!-- /.col -->

				</div><!-- /.row -->

			</div><!-- /.page-content -->

		</div><!-- /.main-content -->

	</div><!-- /.main-container-inner -->


</div><!-- /.main-container -->



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



<script src="${ctx}/resources/js/jquery.dataTables.min.js"></script>

<script src="${ctx}/resources/js/jquery.dataTables.bootstrap.js"></script>



<!-- ace scripts -->



<script src="${ctx}/resources/js/ace-elements.min.js"></script>

<script src="${ctx}/resources/js/ace.min.js"></script>



<!-- inline scripts related to this page -->



<script type="text/javascript">

    jQuery(function($) {

        var oTable1 = $('#sample-table-2').dataTable( {

            "aoColumns": [

                { "bSortable": false },

                null, null,null, null, null,

                { "bSortable": false }

            ] } );





        $('table th input:checkbox').on('click' , function(){

            var that = this;

            $(this).closest('table').find('tr > td:first-child input:checkbox')

                .each(function(){

                    this.checked = that.checked;

                    $(this).closest('tr').toggleClass('selected');

                });



        });





        $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});

        function tooltip_placement(context, source) {

            var $source = $(source);

            var $parent = $source.closest('table')

            var off1 = $parent.offset();

            var w1 = $parent.width();



            var off2 = $source.offset();

            var w2 = $source.width();



            if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';

            return 'left';

        }

    })

</script>



</body>

</html>

