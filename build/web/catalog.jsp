<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mariner Miata</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="./resources/favicon-car.ico" type="image/x-icon">
        <link href="./styles/stylesheet.css" rel="stylesheet" type="text/css">
        <link href="./styles/catalog.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <% String breadcrumb = "Home > Catalog";%>
        <%@include file="./header.jsp" %>
        <%@include file="./user-navigation.jsp" %> 
        <div class="content">
            <%@include file="./site-navigation.jsp" %>
            <main class="main">
                <div class="listWrapper">
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                    <c:if test="${products != null}">
                        <c:if test="${category == null || 
                                      category == 'externals'
                                      || category != null && category != 'internals'}">
                            <div class="contentWrapper">
                                <img class="catalogImg" src="./resources/miata.jpeg" alt="Exterriro">
                                <h3>Miata Body, Externals</h3>
                                <ul class="catalogList">
                                    <c:forEach items="${products}" var="product">
                                        <c:if test="${product.category == 'externals'}">
                                            <li><a href="./catalog?productCode=${product.productCode}">${product.productName}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${category == null || 
                                      category == 'internals'
                                      || category != null && category != 'externals'}">
                            <div class="contentWrapper">
                                <img class="catalogImg" src="./resources/interiror.jpeg" alt="Interrior">
                                <h3>Internals, Dash, AC</h3>
                                <ul class="catalogList">
                                    <c:forEach items="${products}" var="product">
                                        <c:if test="${product.category == 'internals'}">
                                            <li><a href="./catalog?productCode=${product.productCode}">${product.productName}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </c:if>
                </div>
            </main>
        </div>
        <%@include file="./footer.jsp" %>
    </body>
</html>

