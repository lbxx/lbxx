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
                   window.location.assign(contextPath+'/user/showInfo/')
               }else{
                   if(data.code=='CAPTCHA_ERROR'){
                       console.log($(form).find('imput[name="kaptcha"]').parent());
                 alert(data.msg);
                   }
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
