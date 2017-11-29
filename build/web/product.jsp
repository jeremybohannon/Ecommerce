<%-- 
    Document   : profile
    Created on : Nov 27, 2017, 3:32:30 PM
    Author     : jeremybohannon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mariner Miata</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="../resources/favicon-car.ico" type="image/x-icon">
        <link href="./styles/stylesheet.css" rel="stylesheet" type="text/css">
        <link href="./styles/cart.css" rel="stylesheet" type="text/css">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    </head>
    <body>
        <% String breadcrumb = "Home ";%>
        <%@include file="../header.jsp" %>
        <%@include file="../user-navigation.jsp" %>      
        <div class="content">
            <%@include file="../site-navigation.jsp" %>
            <main class="main">
                <div class="cartHeader">
                    <h1 class="mainTitle">Profile</h1>
                </div>
                <div class="cartHeader">
                    <form class="paymentForm" action="./admin" method="post">
                    <div class="paymentInfo">
                        <ul>
                            <li>
                                <span>Product Code</span>
                                <input name="productCode" readonly="readonly" value="${product.productCode}">
                            </li>
                            <li>
                                <span>Name</span>
                                <input type="text" name="productName" value="${product.productName}">
                            </li>
                            <li>
                                <span>Catalog Category</span>
                                <input type="text" name="catagory" value="${product.category}">
                            </li>
                            <li>
                                <span>Description</span>
                                <input type="text" name="description" value="${product.description}">
                            </li>
                            <li>
                                <span>Price</span>
                                <input type="number" name="price" value="${product.price}">
                            </li>
                            <li>
                                <span>Image URL</span>
                                <input type="text" name="imageURL" value="${product.imageURL}">
                            </li>
                            
                        </ul>
                    </div>
                    <div class="cartAndCheckout">
                        <c:if test="${!blank}">
                            <button class="button" type="submit" name="action" value="updateProduct">Update</button>
                            <button class="button" type="submit" name="action" value="deleteProduct">Delete</button>
                        </c:if>
                        <c:if test="${blank}">
                            <button class="button" type="submit" name="action" value="addProduct">Add</button>
                        </c:if>
                    </div>
                </form>
                </div>
            </main>
        </div>
        <%@include file="../footer.jsp" %>
    </body>
</html>
