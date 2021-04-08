<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>

<h2 style="text-align: center;">${title}</h2>

<div style="text-align: center;">
    <#if product??>
        <h5>商品ID:${product.id} </h5>
        <h5>商品名称:${product.name} </h5>
        <h5>商品价格:${product.price} </h5>
        <h5>商品描述:${product.description} </h5>
    </#if>

</div>

<script>
    function btn() {
        alert(1)
    }
</script>
</body>
</html>