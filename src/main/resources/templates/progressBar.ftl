<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style type="text/css">
    </style>
</head>
<body>
<h3>进度条ID ${id}</h3>
<div style="margin-top: 10px;">
    <input type="text" id="address"> <span>&nbsp; &nbsp; &nbsp; &nbsp;</span>
    <input type="button" value="添加地点"  id="add">
</div>
<div>
    <span id="pp"></span>
</div>

<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript">

    $(document).ready(function(){

        getProcessList()
    });

    $("#add").click(function(){
            addAddress($("#address").val())
            $("#address").val('')
        }
    );

    function addAddress(value) {
        $.ajax({
            type:'GET',
            url:"${basePath}/processAdd/1002/"+value,
            success:function(result){
                getProcessList()
            }
        });
    };

    function getProcessList() {

        $.ajax({
            type : "GET", //提交方式
            url : "${basePath}/process/1002",//路径
            success : function(result) {//返回数据根据结果进行相应的处理
                if(result){
                    content = ""
                    for (var i = 0; i < result.length; i++) {
                        var item =  result[i]
                        content = content +  item.value + " &nbsp;  &nbsp;    &nbsp;       " + dateFormat("YYYY-mm-dd HH:MM", new Date(item.score))
                        content = content + "<br><br>"

                    }
                    $("#pp").html(content)
                }
            }
        });
    };

    // 格式化时间
    function dateFormat(fmt, date) {
        let ret;
        const opt = {
            "Y+": date.getFullYear().toString(),        // 年
            "m+": (date.getMonth() + 1).toString(),     // 月
            "d+": date.getDate().toString(),            // 日
            "H+": date.getHours().toString(),           // 时
            "M+": date.getMinutes().toString(),         // 分
            "S+": date.getSeconds().toString()          // 秒
            // 有其他格式化字符需求可以继续添加，必须转化成字符串
        };
        for (let k in opt) {
            ret = new RegExp("(" + k + ")").exec(fmt);
            if (ret) {
                fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
            };
        };
        return fmt;
    }

</script>
</body>
</html>
