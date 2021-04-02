<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        tr{
            width: 60px;
        }
    </style>
</head>
<body>
<h3 style="text-align: center;">${tianmao}</h3>
<div style="text-align: center;padding-left: 400px;">
    <table id="tbl" style="text-align: center;width: 600px;border:solid gray 1px;" cellspacing="1">
        <thead>
        <tr>
            <td>排行榜</td>
            <td>商品</td>
            <td>销量</td>
            <td>操作</td>
        </tr>
        </thead>

        <tbody id="tbd">
        <#if (list?? && list?size > 0) >
            <#list list as it >
                <tr style="height: 40px;">
                    <td>${it_index+1}</td>
                    <td>  ${it.value} </td>
                    <td>${it.score}</td>
                    <td><input type="button" onclick="addCount('${it.value}')" value="购买"></td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
<br><br>

<br>

<div style="text-align: center;">
    <input type="text" id="newItem"> &nbsp;&nbsp;&nbsp;
    <input type="button" value="新增商品" onclick="addItem()">
</div>


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">


    function addCount(value) {
        debugger
        $.ajax({
            type: "GET",
            url: "/addCount/" + value,
            success: function (result) {
                if (result) {
                    getContent(result)
                }
            }
        });
    };

    // addItem
    function addItem() {
        var val = $("#newItem").val();
        if (val) {
            $.ajax({
                type: "GET",
                url: "/addItem/" + val,
                success: function (result) {
                    if (result) {
                        getContent(result)
                        $("#newItem").val('')
                    }
                }
            });
        }
    };

    function getContent(result) {
        if (result && result.length > 0) {
            var content = ''
            for (let i = 0; i < result.length; i++) {
                var item = result[i]
                content = content + '  <tr  style="height: 40px;">\n' +
                    '                    <td> ' + (i + 1) + '</td>\n' +
                    '                    <td> ' + item.value + '</td>\n' +
                    '                    <td>' + item.score + '</td>\n' +
                    '                    <td><input type="button" onclick="addCount(\'' + item.value + '\')" value="购买"></td>\n' +
                    '                </tr>'
            }
            $("#tbd").html(content)
        }
    }
</script>
</body>
</html>