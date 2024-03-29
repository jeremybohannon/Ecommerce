<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mariner Miata</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="./resources/favicon-car.ico" type="image/x-icon">
    <link href="./styles/stylesheet.css" rel="stylesheet" type="text/css">
    <link href="./styles/cart.css" rel="stylesheet" type="text/css">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
<% String breadcrumb = "Home > Userlist ";%>
<%@include file="./header.jsp" %>
<%@include file="./user-navigation.jsp" %>
<div class="content">
    <%@include file="./site-navigation.jsp" %>
    <main class="main">
        <div class="cartHeader">
            <h1 class="mainTitle">Users List</h1>
        </div>
        <table class="cartTable orderListTable">
            <tr>
                <th>User ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><a href="./admin?action=viewProfile&email=${user.email}">${user.userID}</a></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.email}"/></td>
                </tr>
            </c:forEach>
        </table>
    </main>
</div>
<%@include file="./footer.jsp" %>
</body>
</html>
