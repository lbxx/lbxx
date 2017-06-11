<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/pager/css/GridManager.css">
    <script type="text/javascript" src="${ctx}/resources/pager/js/GridManager.js"></script>
    <title>GridManager:使用静态数据渲染</title>
    <style>
        html, body{
            width: 100%;
            height: 100%;
            overflow-x:hidden;
            margin: 0px;
            padding: 0px;
        }
        h2{
            font-size:22px;
            padding:10px 30px;
            color:#333;
        }
        p{
            font-size:14px;
            padding:10px 30px;
            color:#333;
            text-indent:2em;
            margin: 0px;
        }
        hr{
            margin-top: 18px;
            margin-bottom: 18px;
            border: 0;
            border-top: 1px solid #eee;
        }
    </style>
</head>

<body>
<table grid-manager="demo-ajaxPageCode"></table>
<script type="text/javascript">
    var table = document.querySelector('table[grid-manager="demo-ajaxPageCode"]')
    table.GM({
        ajax_url: '${ctx}/test/testPager'
        ,ajax_type: 'GET'
        ,query: {pluginId: 1}
        ,supportAjaxPage: true
        ,columnData: [
            {
                key: 'name',
                text: 'name'
            },{
                key: 'url',
                text: 'url'
            }
        ]
    })
</script>

</body>
</html>
