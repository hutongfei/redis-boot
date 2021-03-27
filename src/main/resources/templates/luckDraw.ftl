<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style type="text/css">
        #draw{
            cursor: pointer;
            width:200px;
            height:200px;
            line-height:200px;
            background-color: blue;
            /* 				background-repeat-x: none; */
            /* float: left; */
            font-size: 20px;
            text-align: center;
            color:white;
            border-radius: 100px;
        }
        .drawClass{
            transition: All 0s ease-in-out;
            -webkit-transition: All 3s ease-in-out;
            -moz-transition: All 3s ease-in-out;
            -o-transition: All 3s ease-in-out;
        }

        .drawClass2 {
            transform: rotate(1440deg);
            -webkit-transform: rotate(1440deg);
            -moz-transform: rotate(1440deg);
            -o-transform: rotate(1440deg);
            -ms-transform: rotate(1440deg);
        }
        h3{
            line-height: 200px;
            text-align: center;
        }
    </style>
</head>
<body>
<div>
    <h4 id="drawerList" style="color: red;"></h4>
</div>

<div class="drawClass" id="draw">
    开奖
</div>
<br>
<div>
    <h4 id="userList"></h4>
</div>
<div style="margin-top: 100px;">
    <input type="text" id="addDrawer"> <span>&nbsp; &nbsp; &nbsp; &nbsp;</span>
    <input type="button" value="添加参与者"  id="add">
</div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

    $(document).ready(function(){
        getUser()
    });


    $("#draw").click(function() {
        $(".drawClass").addClass('drawClass2')
        var t = window.setTimeout(function() {
            getDrawer()
            $(".drawClass").removeClass('drawClass2')
            window.clearTimeout(t)
        }, 3000);

    });


    $("#add").click(function(){
            addUser($("#addDrawer").val())
            $("#addDrawer").val('')
        }
    );

    function addUser(value) {
        $.ajax({
            type:'GET',
            url:"/addDrawer/"+value,
            success:function(result){
                getUser()
            }

        });
    };

    function getUser() {
        $.ajax({
            type : "GET", //提交方式
            url : "/getDrawer",//路径
            success : function(result) {//返回数据根据结果进行相应的处理
                if(result){
                    var content = "奖池名单："
                    for (var i = 0; i < result.length; i++) {
                        content = content +  result[i] + "   "
                    }
                    $("#userList").html(content)
                }
            }
        });
    };

    function getDrawer() {
        $.ajax({
            type : "GET", //提交方式
            url : "/drawer/1",//路径
            success : function(result) {//返回数据根据结果进行相应的处理
                if(result){

                    $("#drawerList").html("中奖者：" +result)
                }
            }
        });
    };


</script>
</body>
</html>
