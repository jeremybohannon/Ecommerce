<%-- 
    Document   : signin
    Created on : Nov 27, 2017, 10:53:13 AM
    Author     : jeremybohannon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
<% String breadcrumb = "Home > Signin";%>
<%@include file="../header.jsp" %>
<%@include file="../user-navigation.jsp" %>
<div class="content">
    <%@include file="../site-navigation.jsp" %>
    <main class="main">
        <div class="cartHeader">
            <h1 class="mainTitle">Sign In</h1>
        </div>
        <div class="cartHeader">
            <form class="paymentForm" action="./signin" method="post">
                <div class="paymentInfo">
                    <ul>
                        <li>
                            <span>Email</span>
                            <input placeholder="Email@email.com" name="email" required>
                        </li>
                        <li>
                            <span>Password</span>
                            <input type="password" name="password" required>
                        </li>
                        <c:if test="${error}">
                            <li>
                                <span>Sorry, Wrong email or password</span>
                            </li>
                        </c:if>
                        <li>
                            <a href="./forgotPass.jsp">Forgot password? Click Here!</a>
                        </li>
                        <li>
                            <a href="./signup.jsp">Don't have an account? Click to sign up here!!</a>
                        </li>
                    </ul>
                </div>
                <div class="cartAndCheckout">
                    <button class="button signIn" type="submit" name="action" value="signIn">Sign In</button>
                </div>
            </form>
        </div>
    </main>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
