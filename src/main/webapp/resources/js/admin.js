// 编辑
function editButton(url,permiss) {
    url = url + "/" +permiss;
    var $idsCheckedCheck = $("#grid-table input[role='checkbox']:checked");
    if ($idsCheckedCheck.length != 1) {
        mydialog("",'对不起，请选择一个选项!');
        return;
    } else {
        // 获取id
        var id = $idsCheckedCheck.parent().parent().attr("id");
        window.location.href = url + "?id=" + id;
    }
}
// 查看
function viewButton(url,permiss) {
    url = url+ "/" +permiss;
    var $idsCheckedCheck = $("#grid-table input[role='checkbox']:checked");
    if ($idsCheckedCheck.length != 1) {
        mydialog("",'对不起，请选择一个选项!');
        return;
    } else {
        // 获取id
        var id = $idsCheckedCheck.parent().parent().attr("id");
        window.location.href = url + "?id=" + id;
    }
}
// 删除
function removeButton(url,permiss) {
    var purl = url;
    var $idsCheckedCheck = $("#grid-table input[role='checkbox']:checked");
    if ($idsCheckedCheck == undefined||$idsCheckedCheck.length==0) {
        mydialog("",'对不起，请选择一个选项!');
    } else {
        if ($idsCheckedCheck.size() <= 0){
            return;
        }else{
            var id = $idsCheckedCheck.parent().parent().attr("id");
            url = url+"/"+permiss + "?id=" + id;
            removeDialog(url,purl,'您确定要删除吗?');
        }
    }
}
// 添加方法，直接跳转
function addButton(url,permiss) {
    url = url + "/" + permiss;
    window.location.href = url;
}

// 下面是dialog弹窗的
function mydialog(url,msg){
    // 动态生成弹窗内容
    $("#dialog-message p").html(msg);
    // 下面这个方法是dialog样式文件，勿删
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function(title) {
            var $title = this.options.title || '&nbsp;'
            if( ("title_html" in this.options) && this.options.title_html == true )
                title.html($title);
            else title.text($title);
        }
    }));
    var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
        modal: true,
        title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>提示信息</h4></div>",
        title_html: true,
        buttons: [
            {
                text: "Cancel",
                "class" : "btn btn-xs",
                click: function() {
                    // 取消关闭弹窗
                    $( this ).dialog( "close" );
                }
            },
            {
                text: "OK",
                "class" : "btn btn-primary btn-xs",
                click: function() {
                    // 确定关闭弹窗，然后执行操作
                    $( this ).dialog( "close" );
                    // 判断url是否为空
                    if(url != "" && url != null){
                        location.href = url;
                    }
                }
            }
        ]
    });
}
// 下面是dialog弹窗的
function removeDialog(url,purl,msg){
    // 动态生成弹窗内容
    $("#dialog-message p").html(msg);
    // 下面这个方法是dialog样式文件，勿删
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function(title) {
            var $title = this.options.title || '&nbsp;'
            if( ("title_html" in this.options) && this.options.title_html == true )
                title.html($title);
            else title.text($title);
        }
    }));
    var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
        modal: true,
        title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>提示信息</h4></div>",
        title_html: true,
        buttons: [
            {
                text: "Cancel",
                "class" : "btn btn-xs",
                click: function() {
                    // 取消关闭弹窗
                    $( this ).dialog( "close" );
                }
            },
            {
                text: "OK",
                "class" : "btn btn-primary btn-xs",
                click: function() {
                    // 确定关闭弹窗，然后执行操作
                    $( this ).dialog( "close" );
                    // 判断url是否为空
                    if(url != "" && url != null){
                        //location.href = url;
                        // 换成ajax提交
                        $.ajax({
                            url:url,
                            type:"get",
                            dataType:"json",
                            data:{},
                            success:function(data){
                                if(data.ok){
                                    $.scojs_message(data.msg, $.scojs_message.TYPE_OK);
                                    window.location.assign(purl+"/list");
                                }else{
                                    $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
                                }
                            }
                        })
                    }
                }
            }
        ]
    });
}
// 下面是dialog提示信息弹窗的
function msgdialog(msg){
    // 动态生成弹窗内容
    $("#dialog-message p").html(msg);
    // 下面这个方法是dialog样式文件，勿删
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function(title) {
            var $title = this.options.title || '&nbsp;'
            if( ("title_html" in this.options) && this.options.title_html == true )
                title.html($title);
            else title.text($title);
        }
    }));
    var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
        modal: true,
        title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='icon-ok'></i>提示信息</h4></div>",
        title_html: true,
        buttons: [
            {
                text: "OK",
                "class" : "btn btn-primary btn-xs",
                click: function() {
                    // 确定关闭弹窗，然后执行操作
                    $( this ).dialog( "close" );
                }
            }
        ]
    });
}



