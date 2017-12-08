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
<% String breadcrumb = "Home > Catalog > Cart > Order";%>
<%@include file="./header.jsp" %>
<%@include file="./user-navigation.jsp" %>
<div class="content">
    <%@include file="./site-navigation.jsp" %>
    <main class="main">
        <div class="invoiceDetail">
            <h2>Invoice</h2>
            <p>Date: ${currentOrder.date}</p>
            <p>Ship To / Bill To:</p>
            <p><c:out value="${theUser.lastName}"/>, <c:out value="${theUser.firstName}"/></p>
            <p><c:out value="${theUser.address2}"/></p>
            <p><c:out value="${theUser.address1}"/></p>
            <p><c:out value="${theUser.city}"/>, <c:out value="${theUser.state}"/> <c:out value="${theUser.postalCode}"/></p>
        </div>
        <c:if test="${!admin}">
        <form class="cartTableWrapper" action="./catalog" method="post">
            </c:if>
            <c:if test="${admin}">
            <form class="cartTableWrapper" action="./admin" method="post">
                <input type="hidden" min="0" name="orderNum" value="${currentOrder.orderNumber}"/>
                </c:if>
                <table class="cartTable">
                    <tr>
                        <th>Item</th>
                        <th class="tablePrice">Price</th>
                        <th class="tableQuantity">Quantity</th>
                        <th class="tableTotal">Total</th>
                    </tr>
                    <c:forEach items="${currentOrder.items}" var="item">
                        <tr>
                            <td>${item.product.productName}</td>
                            <td class="tablePrice">$${item.product.price}</td>
                            <input type="hidden" min="0" name="productList[]" value="${item.product.productCode}"/>
                            <td class="tableQuantity"><input type="number" min="0" name="${item.product.productCode}"
                                                             value="${item.quantity}"/></td>
                            <td class="tableTotal">
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatNumber value="${item.product.price * item.quantity}" type="currency"/>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
                <c:if test="${!admin}">
                    <div class="subtotalWrapper">
                        <span><b>Subtotal:</b></span>
                        <span>
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatNumber value="${subTotal}" type="currency"/>
                            </span><br/>
                        <span><b>Tax:</b></span>
                        <span>
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatNumber value="${currentOrder.taxRate * subTotal}" type="currency"/>
                            </span><br/>
                        <span><b>Total:</b>
                            </span>
                        <span>
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatNumber value="${currentOrder.totalCost}" type="currency"/>
                            </span>
                    </div>
                    <c:if test="${!currentOrder.paid}">
                        <div class="cartAndCheckout">
                            <a href="./cart.jsp" class="button">Back to cart</a>
                            <a href="./payment.jsp" class="button">Purchase</a>
                        </div>
                    </c:if>
                    <c:if test="${currentOrder.paid}">
                        <div class="cartAndCheckout">
                            <span><b>Paid In Full!</b></span>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${admin}">
                    <div class="cartAndCheckout">
                        <button class="button" type="submit" name="action" value="updateOrder">Update Order</button>
                    </div>
                </c:if>
            </form>
    </main>
</div>
<%@include file="./footer.jsp" %>
</body>
</html>
