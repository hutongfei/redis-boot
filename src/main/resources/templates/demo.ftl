<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>aaaaaaaaa</h1>
    <#if msg?? >
        ${msg}
    </#if>

    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            console.log('${msg}')
            console.log(111111111111111)
            addViewCount()
        });

        function addViewCount() {
            $.ajax({
                type : "GET",
                url : "/upAdd/"+ '${msg}',
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