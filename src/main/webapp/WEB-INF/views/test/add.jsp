<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- basic styles -->
<link href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="http://cdn.bootcss.com/font-awesome/3.2.1/css/font-awesome.min.css">
<!--[if IE 7]>
		  <link rel="stylesheet" href="${ctx}/resources/css/font-awesome-ie7.min.css" />
		<![endif]-->
<!-- page specific plugin styles -->
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
	<!-- 引入顶部 -->
	<jsp:include page="../header.jsp"/>
	
	<div class="main-container" id="main-container">

		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>



		<div class="main-container-inner">

			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

		<!-- 引入左边菜单 -->
		<jsp:include page="../lefter.jsp"/>

		<!-- 内容开始 -->
		<div class="main-content">
			<div class="page-content">

					<div class="page-header">

						<h1>

							控制台 <small> <i class="icon-double-angle-right"></i> 查看

							</small>

						</h1>

					</div>
			</div>
			<div class="row">

							<div class="col-xs-12">

								<!-- PAGE CONTENT BEGINS -->



								<form class="form-horizontal" role="form">

									<div class="form-group">

										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Text Field </label>



										<div class="col-sm-9">

											<input type="text" id="form-field-1" placeholder="Username" class="col-xs-10 col-sm-5" />

										</div>

									</div>



									<div class="space-4"></div>



									<div class="form-group">

										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> Password Field </label>



										<div class="col-sm-9">

											<input type="password" id="form-field-2" placeholder="Password" class="col-xs-10 col-sm-5" />

											<span class="help-inline col-xs-12 col-sm-7">

												<span class="middle">Inline help text</span>

											</span>

										</div>

									</div>



									<div class="space-4"></div>



									<div class="form-group">

										<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly"> Readonly field </label>



										<div class="col-sm-9">

											<input readonly="" type="text" class="col-xs-10 col-sm-5" id="form-input-readonly" value="This text field is readonly!" />

											<span class="help-inline col-xs-12 col-sm-7">

												<label class="middle">

													<input class="ace" type="checkbox" id="id-disable-check" />

													<span class="lbl"> Disable it!</span>

												</label>

											</span>

										</div>

									</div>



									<div class="space-4"></div>



									<div class="form-group">

										<label class="col-sm-3 control-label no-padding-right" for="form-field-4">Relative Sizing</label>



										<div class="col-sm-9">

											<input class="input-sm" type="text" id="form-field-4" placeholder=".input-sm" />

											<div class="space-2"></div>



											<div class="help-block" id="input-size-slider"></div>

										</div>

									</div>



									<div class="form-group">

										<label class="col-sm-3 control-label no-padding-right" for="form-field-5">Grid Sizing</label>



										<div class="col-sm-9">

											<div class="clearfix">

												<input class="col-xs-1" type="text" id="form-field-5" placeholder=".col-xs-1" />

											</div>



											<div class="space-2"></div>



											<div class="help-block" id="input-span-slider"></div>

										</div>

									</div>



									<div class="form-group">

										<label class="col-sm-3 control-label no-padding-right">Input with Icon</label>



										<div class="col-sm-9">

											<span class="input-icon">

												<input type="text" id="form-field-icon-1" />

												<i class="icon-leaf blue"></i>

											</span>



											<span class="input-icon input-icon-right">

												<input type="text" id="form-field-icon-2" />

												<i class="icon-leaf green"></i>

											</span>

										</div>

									</div>



									<div class="space-4"></div>



									<div class="form-group">

										<label class="col-sm-3 control-label no-padding-right" for="form-field-6">Tooltip and help button</label>



										<div class="col-sm-9">

											<input data-rel="tooltip" type="text" id="form-field-6" placeholder="Tooltip on hover" title="Hello Tooltip!" data-placement="bottom" />

											<span class="help-button" data-rel="popover" data-trigger="hover" data-placement="left" data-content="More details." title="Popover on hover">?</span>

										</div>

									</div>



									<div class="space-4"></div>



									<div class="form-group">

										<label class="col-sm-3 control-label no-padding-right" for="form-field-tags">Tag input</label>



										<div class="col-sm-9">

											<input type="text" name="tags" id="form-field-tags" value="Tag Input Control" placeholder="Enter tags ..." />

										</div>

									</div>



									<div class="clearfix form-actions">

										<div class="col-md-offset-3 col-md-9">

											<button class="btn btn-info" type="button">

												<i class="icon-ok bigger-110"></i>

												Submit

											</button>



											&nbsp; &nbsp; &nbsp;

											<button class="btn" type="reset">

												<i class="icon-undo bigger-110"></i>

												Reset

											</button>

										</div>

									</div>



									
								</form>


							</div><!-- /.col -->

						</div>
		</div>
		<!-- 内容结束 -->
				
		
		</div>
		<!-- /.main-container-inner -->

	</div>
	<!-- /.main-container -->
	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script src="http://www.jq22.com/jquery/jquery-2.1.1.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->
	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${ctx}/resources/js/jquery-2.0.3.min.js'>"
								+ "<"+"script>");
	</script>
	<!-- <![endif]-->
	<!--[if IE]>
<script type="text/javascript">

 window.jQuery || document.write("<script src='${ctx}/resources/js/jquery-1.10.2.min.js'>"+"<"+"script>");

</script>

<![endif]-->
	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='${ctx}/resources/js/jquery.mobile.custom.min.js'>"
							+ "<"+"script>");
	</script>

	<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>

	<script src="${ctx}/resources/js/typeahead-bs2.min.js"></script>
	<!-- page specific plugin scripts -->
	<!--[if lte IE 8]>
		  <script src="${ctx}/resources/js/excanvas.min.js"></script>
		<![endif]-->
	<script src="${ctx}/resources/js/jquery-ui-1.10.3.custom.min.js"></script>

	<script src="${ctx}/resources/js/jquery.ui.touch-punch.min.js"></script>

	<script src="${ctx}/resources/js/jquery.slimscroll.min.js"></script>

	<script src="${ctx}/resources/js/jquery.easy-pie-chart.min.js"></script>

	<script src="${ctx}/resources/js/jquery.sparkline.min.js"></script>

	<script src="${ctx}/resources/js/flot/jquery.flot.min.js"></script>

	<script src="${ctx}/resources/js/flot/jquery.flot.pie.min.js"></script>

	<script src="${ctx}/resources/js/flot/jquery.flot.resize.min.js"></script>
	<!-- ace scripts -->
	<script src="${ctx}/resources/js/ace-elements.min.js"></script>

	<script src="${ctx}/resources/js/ace.min.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {

			$('.easy-pie-chart.percentage')
					.each(
							function() {

								var $box = $(this).closest('.infobox');

								var barColor = $(this).data('color')
										|| (!$box.hasClass('infobox-dark') ? $box
												.css('color')
												: 'rgba(255,255,255,0.95)');

								var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)'
										: '#E2E2E2';

								var size = parseInt($(this).data('size')) || 50;

								$(this)
										.easyPieChart(
												{

													barColor : barColor,

													trackColor : trackColor,

													scaleColor : false,

													lineCap : 'butt',

													lineWidth : parseInt(size / 10),

													animate : /msie\s*(8|7|6)/
															.test(navigator.userAgent
																	.toLowerCase()) ? false
															: 1000,

													size : size

												});

							})

			$('.sparkline').each(
					function() {

						var $box = $(this).closest('.infobox');

						var barColor = !$box.hasClass('infobox-dark') ? $box
								.css('color') : '#FFF';

						$(this).sparkline('html', {
							tagValuesAttribute : 'data-values',
							type : 'bar',
							barColor : barColor,
							chartRangeMin : $(this).data('min') || 0
						});

					});

			var placeholder = $('#piechart-placeholder').css({
				'width' : '90%',
				'min-height' : '150px'
			});

			var data = [

			{
				label : "social networks",
				data : 38.7,
				color : "#68BC31"
			},

			{
				label : "search engines",
				data : 24.5,
				color : "#2091CF"
			},

			{
				label : "ad campaigns",
				data : 8.2,
				color : "#AF4E96"
			},

			{
				label : "direct traffic",
				data : 18.6,
				color : "#DA5430"
			},

			{
				label : "other",
				data : 10,
				color : "#FEE074"
			}

			]

			function drawPieChart(placeholder, data, position) {

				$.plot(placeholder, data, {

					series : {

						pie : {

							show : true,

							tilt : 0.8,

							highlight : {

								opacity : 0.25

							},

							stroke : {

								color : '#fff',

								width : 2

							},

							startAngle : 2

						}

					},

					legend : {

						show : true,

						position : position || "ne",

						labelBoxBorderColor : null,

						margin : [ -30, 15 ]

					}

					,

					grid : {

						hoverable : true,

						clickable : true

					}

				})

			}

			drawPieChart(placeholder, data);

			/**

			we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically

			so that's not needed actually.

			 */

			placeholder.data('chart', data);

			placeholder.data('draw', drawPieChart);

			var $tooltip = $(
					"<div class='tooltip top in'><div class='tooltip-inner'></div></div>")
					.hide().appendTo('body');

			var previousPoint = null;

			placeholder.on('plothover', function(event, pos, item) {

				if (item) {

					if (previousPoint != item.seriesIndex) {

						previousPoint = item.seriesIndex;

						var tip = item.series['label'] + " : "
								+ item.series['percent'] + '%';

						$tooltip.show().children(0).text(tip);

					}

					$tooltip.css({
						top : pos.pageY + 10,
						left : pos.pageX + 10
					});

				} else {

					$tooltip.hide();

					previousPoint = null;

				}

			});

			var d1 = [];

			for (var i = 0; i < Math.PI * 2; i += 0.5) {

				d1.push([ i, Math.sin(i) ]);

			}

			var d2 = [];

			for (var i = 0; i < Math.PI * 2; i += 0.5) {

				d2.push([ i, Math.cos(i) ]);

			}

			var d3 = [];

			for (var i = 0; i < Math.PI * 2; i += 0.2) {

				d3.push([ i, Math.tan(i) ]);

			}

			var sales_charts = $('#sales-charts').css({
				'width' : '100%',
				'height' : '220px'
			});

			$.plot("#sales-charts", [

			{
				label : "Domains",
				data : d1
			},

			{
				label : "Hosting",
				data : d2
			},

			{
				label : "Services",
				data : d3
			}

			], {

				hoverable : true,

				shadowSize : 0,

				series : {

					lines : {
						show : true
					},

					points : {
						show : true
					}

				},

				xaxis : {

					tickLength : 0

				},

				yaxis : {

					ticks : 10,

					min : -2,

					max : 2,

					tickDecimals : 3

				},

				grid : {

					backgroundColor : {
						colors : [ "#fff", "#fff" ]
					},

					borderWidth : 1,

					borderColor : '#555'

				}

			});

			$('#recent-box [data-rel="tooltip"]').tooltip({
				placement : tooltip_placement
			});

			function tooltip_placement(context, source) {

				var $source = $(source);

				var $parent = $source.closest('.tab-content')

				var off1 = $parent.offset();

				var w1 = $parent.width();

				var off2 = $source.offset();

				var w2 = $source.width();

				if (parseInt(off2.left) < parseInt(off1.left)
						+ parseInt(w1 / 2))
					return 'right';

				return 'left';

			}

			$('.dialogs,.comments').slimScroll({

				height : '300px'

			});

			//Android's default browser somehow is confused when tapping on label which will lead to dragging the task

			//so disable dragging when clicking on label

			var agent = navigator.userAgent.toLowerCase();

			if ("ontouchstart" in document && /applewebkit/.test(agent)
					&& /android/.test(agent))

				$('#tasks').on('touchstart', function(e) {

					var li = $(e.target).closest('#tasks li');

					if (li.length == 0)
						return;

					var label = li.find('label.inline').get(0);

					if (label == e.target || $.contains(label, e.target))
						e.stopImmediatePropagation();

				});

			$('#tasks').sortable({

				opacity : 0.8,

				revert : true,

				forceHelperSize : true,

				placeholder : 'draggable-placeholder',

				forcePlaceholderSize : true,

				tolerance : 'pointer',

				stop : function(event, ui) {//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved

					$(ui.item).css('z-index', 'auto');

				}

			}

			);

			$('#tasks').disableSelection();

			$('#tasks input:checkbox').removeAttr('checked').on('click',
					function() {

						if (this.checked)
							$(this).closest('li').addClass('selected');

						else
							$(this).closest('li').removeClass('selected');

					});

		})
	</script>
</body>
</html>