<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>海迅科技</title>
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
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${ctx}/resources/css/ace-ie.min.css" />
    <![endif]-->
    <!-- inline styles related to this page -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>

    <script src="${ctx}/resources/js/html5shiv.js"></script>

    <script src="${ctx}/resources/js/respond.min.js"></script>

    <![endif]-->

</head>



<body class="login-layout">
<input name="contextPath" hidden value="${ctx}">

<div class="main-container">

    <div class="main-content">

        <div class="row">

            <div class="col-sm-10 col-sm-offset-1">

                <div class="login-container">

                    <div class="center">

                        <h1>

                            <i class="icon-leaf green"></i>

                            <span class="red">Ace</span>

                            <span class="white">Application</span>

                        </h1>

                        <h4 class="blue">&copy; Company Name</h4>

                    </div>



                    <div class="space-6"></div>



                    <div class="position-relative">

                        <div id="login-box" class="login-box visible widget-box no-border">

                            <div class="widget-body">

                                <div class="widget-main">

                                    <h4 class="header blue lighter bigger">

                                        <i class="icon-coffee green"></i>

                                        Please Enter Your Information

                                    </h4>



                                    <div class="space-6"></div>



                                    <form id="loginForm" method="post" action="${ctx}/login/login">

                                        <fieldset>

                                            <label class="block clearfix">

														<span class="block input-icon input-icon-right">

															<input type="text" name="account" id="account" required minlength="3" maxlength="30" class="form-control" placeholder="请输入用户名" />

															<i class="icon-user"></i>

														</span>

                                            </label>



                                            <label class="block clearfix">

														<span class="block input-icon input-icon-right">

															<input type="password" name="password" id="password" required minlength="3" maxlength="30" class="form-control" placeholder="请输入密码" />

															<i class="icon-lock"></i>

														</span>

                                            </label>
                                            <label class="block clearfix">

													<span>

															<input type="text" name="kaptcha" id="kaptcha" required maxlength="4" minlength="4" style="width:60%;display: inline;" class="form-control" placeholder="请输入验证码" />
														<%--	<i class="icon-user"></i>--%>
                                                             <img src="${ctx}/randomCode.jpg" id="randomCode" onclick="this.src='${ctx}/randomCode.jpg';"/>
													</span>



                                            </label>


                                            <div class="space"></div>



                                            <div class="clearfix">

                                             <%--   <label class="inline">

                                                    <input type="checkbox" class="ace" />

                                                    <span class="lbl"> Remember Me</span>

                                                </label>--%>



                                                <button  type="submit" id="login" class="width-35 pull-right btn btn-sm btn-primary">

                                                    <i class="icon-key"></i>

                                                    Login

                                                </button>

                                            </div>



                                            <div class="space-4"></div>

                                        </fieldset>

                                    </form>



                                    <div class="social-or-login center">

                                        <span class="bigger-110">Or Login Using</span>

                                    </div>



                                   <%-- <div class="social-login center">

                                        <a class="btn btn-primary">

                                            <i class="icon-facebook"></i>

                                        </a>



                                        <a class="btn btn-info">

                                            <i class="icon-twitter"></i>

                                        </a>



                                        <a class="btn btn-danger">

                                            <i class="icon-google-plus"></i>

                                        </a>

                                    </div>--%>

                                </div><!-- /widget-main -->



                                <div class="toolbar clearfix">

                                    <div>

                                        <a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">

                                            <i class="icon-arrow-left"></i>

                                            I forgot my password

                                        </a>

                                    </div>



                                    <div>

                                        <a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">

                                            I want to register

                                            <i class="icon-arrow-right"></i>

                                        </a>

                                    </div>

                                </div>

                            </div><!-- /widget-body -->

                        </div><!-- /login-box -->



                        <div id="forgot-box" class="forgot-box widget-box no-border">

                            <div class="widget-body">

                                <div class="widget-main">

                                    <h4 class="header red lighter bigger">

                                        <i class="icon-key"></i>

                                        Retrieve Password

                                    </h4>



                                    <div class="space-6"></div>

                                    <p>

                                        Enter your email and to receive instructions

                                    </p>



                                    <form>

                                        <fieldset>

                                            <label class="block clearfix">

														<span class="block input-icon input-icon-right">

															<input type="email" class="form-control" placeholder="Email" />

															<i class="icon-envelope"></i>

														</span>

                                            </label>



                                            <div class="clearfix">

                                                <button type="button" class="width-35 pull-right btn btn-sm btn-danger">

                                                    <i class="icon-lightbulb"></i>

                                                    Send Me!

                                                </button>

                                            </div>

                                        </fieldset>

                                    </form>

                                </div><!-- /widget-main -->



                                <div class="toolbar center">

                                    <a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">

                                        Back to login

                                        <i class="icon-arrow-right"></i>

                                    </a>

                                </div>

                            </div><!-- /widget-body -->

                        </div><!-- /forgot-box -->



                        <div id="signup-box" class="signup-box widget-box no-border">

                            <div class="widget-body">

                                <div class="widget-main">

                                    <h4 class="header green lighter bigger">

                                        <i class="icon-group blue"></i>

                                        New User Registration

                                    </h4>



                                    <div class="space-6"></div>

                                    <p> Enter your details to begin: </p>



                                    <form>

                                        <fieldset>

                                            <label class="block clearfix">

														<span class="block input-icon input-icon-right">

															<input type="email" class="form-control" placeholder="Email" />

															<i class="icon-envelope"></i>

														</span>

                                            </label>



                                            <label class="block clearfix">

														<span class="block input-icon input-icon-right">

															<input type="text" class="form-control" placeholder="Username" />

															<i class="icon-user"></i>

														</span>

                                            </label>



                                            <label class="block clearfix">

														<span class="block input-icon input-icon-right">

															<input type="password" class="form-control" placeholder="Password" />

															<i class="icon-lock"></i>

														</span>

                                            </label>



                                            <label class="block clearfix">

														<span class="block input-icon input-icon-right">

															<input type="password" class="form-control" placeholder="Repeat password" />

															<i class="icon-retweet"></i>

														</span>

                                            </label>



                                            <label class="block">

                                                <input type="checkbox" class="ace" />

                                                <span class="lbl">

															I accept the

															<a href="#">User Agreement</a>

														</span>

                                            </label>



                                            <div class="space-24"></div>



                                            <div class="clearfix">

                                                <button type="reset" class="width-30 pull-left btn btn-sm">

                                                    <i class="icon-refresh"></i>

                                                    Reset

                                                </button>



                                                <button type="button" class="width-65 pull-right btn btn-sm btn-success">

                                                    Register

                                                    <i class="icon-arrow-right icon-on-right"></i>

                                                </button>

                                            </div>

                                        </fieldset>

                                    </form>

                                </div>



                                <div class="toolbar center">

                                    <a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">

                                        <i class="icon-arrow-left"></i>

                                        Back to login

                                    </a>

                                </div>

                            </div><!-- /widget-body -->

                        </div><!-- /signup-box -->

                    </div><!-- /position-relative -->

                </div>

            </div><!-- /.col -->

        </div><!-- /.row -->

    </div>

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



<!-- inline scripts related to this page -->



<script type="text/javascript">

    function show_box(id) {

        jQuery('.widget-box.visible').removeClass('visible');

        jQuery('#'+id).addClass('visible');

    }

</script>
<script src="${ctx}/resources/js/jquery.form.min.js"></script>
<script src="${ctx}/resources/js/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

<script src="${ctx}/resources/js/haixun/login.js"></script>
<script type="text/javascript">
	/* $(function(){
		$("#login").on("click",function(){
			//$("#loginForm").validate();
			var account = $("#account").val();
			var password = $("#password").val();
			var kaptcha = $("#kaptcha").val();
			if(account == null || account == ""){
				alert("请输入用户名");
				return false;
			}
			if(password == null || password == ""){
				alert("请输入密码");
				return false;
			}
			if(kaptcha == null || kaptcha == ""){
				alert("请输入验证码");
				return false;
			}
			$.ajax({
				url:"${ctx}/login/login",
				type:"POST",
				dataType:"json",
				data:{"account":account,"password":password,"kaptcha":kaptcha},
				success:function(v){
					if(v.result){
						window.location.href="${ctx}/index/index";
					}else{
						alert(v.msg)
					}
				},
				error:function(e){
					console.log(e)
				}
			})
		})
	}) */
</script>
</body>

</html>

