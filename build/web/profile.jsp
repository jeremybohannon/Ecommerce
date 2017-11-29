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
                    <form class="paymentForm" action="./signin" method="post">
                    <div class="paymentInfo">
                        <ul>
                            <li>
                                <span>Email</span>
                                <input name="email" value="${theUser.email}">
                            </li>
                            <li>
                                <span>Password</span>
                                <input type="text" name="password" value="${theUser.password}">
                            </li>
                            <li>
                                <span>First Name</span>
                                <input type="text" name="firstName" value="${theUser.firstName}">
                                <span>Last Name</span>
                                <input type="text" name="lastName" value="${theUser.lastName}">
                            
                            </li>
                            <li>
                                <span>Address #1</span>
                                <input type="text" name="address1" value="${theUser.address1}">
                                <span>Address #2</span>
                                <input type="text" name="address2" value="${theUser.address2}">
                            </li>
                            <li>
                                <span>City</span>
                                <input type="text" name="city" value="${theUser.city}">
                                <span>State</span>
                                <input type="text" name="state" value="${theUser.state}">
                            </li>
                            <li>
                                <span>Zip code</span>
                                <input type="text" name="zip" value="${theUser.postalCode}">
                                <span>Country</span>
                                <input type="text" name="country" value="${theUser.country}">
                            </li>
                        </ul>
                    </div>
                    <div class="cartAndCheckout">
                        <button class="button" type="submit" name="action" value="update">Update</button>
                    </div>
                </form>
                </div>
            </main>
        </div>
        <%@include file="../footer.jsp" %>
    </body>
</html>
