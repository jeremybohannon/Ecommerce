
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    </head>
    <body>
        <% String breadcrumb = "Home ";%>
        <%@include file="./header.jsp" %>
        <%@include file="./user-navigation.jsp" %>      
        <div class="content">
            <%@include file="./site-navigation.jsp" %>
            <main class="main">
                <div class="cartHeader">
                    <h1 class="mainTitle">Orders List</h1>
                </div>
                <table class="cartTable orderListTable">
                    <tr>
                       <th>Order Number</th>
                       <th>Customer</th> 
                       <th>Order Date</th>
                       <th>Total</th>
                    </tr>
                    <c:forEach items="${theOrders}" var="order">
                          <tr>
                              <c:if test="${admin}">
                                  <td><a href="./admin?action=orderNum&orderNum=${order.orderNumber}&userID=${order.user.userID}">${order.orderNumber}</a></td>
                              </c:if>
                              <c:if test="${!admin}">
                                  <td>${order.orderNumber}</a</td>
                              </c:if>
                              <td>${order.user.firstName} ${order.user.lastName}</td>
                              <td>${order.date}</td>
                              <td>
                              <fmt:setLocale value = "en_US"/>
                              <fmt:formatNumber value = "${order.totalCost}" type = "currency"/>
                              </td>
                          </tr>
                      </c:forEach>
                </table>
            </main>
        </div>
        <%@include file="./footer.jsp" %>
    </body>
</html>
