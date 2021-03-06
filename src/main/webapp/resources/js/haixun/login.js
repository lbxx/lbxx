/**
 * Created by tangxinmao on 2017/6/4.
 */

$(function () {
    var contextPath = $('input[name="contextPath"]').val();

    $("#loginForm").validate({
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                success: function (data) {
               if(data.result){
                   $.scojs_message(data.msg, $.scojs_message.TYPE_OK);
                   window.location.assign(contextPath+'/index/index/')
               }else{
                   $('#randomCode').prop('src',contextPath+'/randomCode.jpg');
                   $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
               }
                },error:function (data) {


            }
            });
        },
        rules: {
            kaptcha: {
                remote: {
                    url: contextPath + "/login/checkKaptcha",     //后台处理程序
                    type: "post",               //数据发送方式
                    dataType: "json",           //接受数据格式
                    data: {                     //要传递的数据
                        kaptcha: function () {
                            return $('input[name="kaptcha"]').val();
                        }
                    }
                }
            }
        },
        messages: {
            kaptcha: {
                remote: "验证码错误!"
            }
        }
        , errorPlacement: function (error, element) {

            if ($(element).prop('name') == 'kaptcha') {
                error.appendTo(element.parent())

            } else
                error.appendTo(element.parent())
        }
    });
});
