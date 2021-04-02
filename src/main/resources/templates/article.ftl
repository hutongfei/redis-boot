<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div style="text-align: center;">
<p>
    Java工程师必看：项目能力提升大作战（一）<br>
    150篇文章掌握NoSQL【包含Redis、MongoDB 、Elasticsearch、HBase】<br>
    小编在这里根据知识图谱整理了CSDN站内的优质文章150篇，<br>
    帮助Java工程提升项目能力、实现系统化学习！<br>
    <img src="https://csdnimg.cn/release/blogv2/dist/pc/img/articleReadEyes.png" alt=""><br>
    <#if count??>
        ${count}
    <#else >
        0
    </#if>

</p>
</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        if (!window.name) {
            window.name = new Date() + '';
            addViewCount()
        }
    });

    function addViewCount() {
        $.ajax({
            type : "GET",
            url : "/incrPoint/2021",
            success : function(result) {
                if(result){
                    console.log(result)
                }
            }
        });
    };


</script>

</body>
</html>