<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <title>Mariner Miata</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="./resources/favicon-car.ico" type="image/x-icon">
    <link href="./styles/stylesheet.css" rel="stylesheet" type="text/css">
    <link href="./styles/item.css" rel="stylesheet" type="text/css">
</head>
<body>
<% String breadcrumb = "Home > Catalog > Item";%>
<%@include file="./header.jsp" %>
<%@include file="./user-navigation.jsp" %>
<div class="content">
    <%@include file="./site-navigation.jsp" %>
    <main class="main">
        <form class="item" action="./order?action=updateCart" method="post">
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <img class="itemPhoto" src="${product.imageURL}" alt="Mazda Miata"/>
            <div class="itemInfo">
                <h4>${product.productName}</h4>
                <p class="itemType">Category: ${product.category}</p>
                <p class="itemCost">Price: $${product.price}</p>
                <h4 class="itemDescription">Description:</h4>
                <p class="itemDescription">${product.description}</p>
                <div class="purchase">
                    <button class="button" type="submit" name='productList[]' value="${product.productCode}">Add To
                        Cart
                    </button>
                </div>
            </div>
            <div class="backBtn">
                <a href="./catalog"><h3>Back to Catalog</h3></a>
            </div>
        </form>
    </main>
</div>
<%@include file="./footer.jsp" %>
</body>
</html>
