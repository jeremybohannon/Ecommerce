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
        <% String breadcrumb = "Home > Catalog > Cart";%>
        <%@include file="./header.jsp" %>
        <%@include file="./user-navigation.jsp" %>      
        <div class="content">
            <%@include file="./site-navigation.jsp" %>
            <main class="main">
                <div class="cartHeader">
                    <h2>Your Cart</h2>
                    <p>To remove an item, set the quantity to zero.</p>
                </div>
                <form class="cartTableWrapper" action="./order" method="post">
                    <table class="cartTable">
                      <tr>
                        <th>Item</th>
                        <th class="tablePrice">Price</th> 
                        <th class="tableQuantity">Quantity</th>
                        <th class="tableTotal">Total</th>
                      </tr>
                      <c:forEach items="${theShoppingCart.items}" var="item">
                          <tr>
                              <td>${item.product.productName}</td>
                              <td class="tablePrice">$${item.product.price}</td>
                              <input type="hidden" min="0" name="productList[]" value="${item.product.productCode}" />
                              <td class="tableQuantity"><input type="number" min="0" name="${item.product.productCode}" value="${item.quantity}" /></td>
                              <td class="tableTotal">
                                <fmt:setLocale value = "en_US"/>
                                <fmt:formatNumber value = "${item.product.price * item.quantity}" type = "currency"/>
                              </td>
                          </tr>
                      </c:forEach>
                          
                    </table>
                    <div class="subtotalWrapper">
                        <span><b>Subtotal:</b></span>
                        <span>
                            <fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${subTotal}" type = "currency"/>
                        </span>
                    </div>  
                    <div class="cartAndCheckout">
                        <button class="button" type="submit" name="action" value="updateCart">Update Cart</button>
                        <button class="button" type="submit" name="action" value="checkout">Check Out</button>
                    </div>
                </form>             
            </main>
        </div>
        <%@include file="./footer.jsp" %>
    </body>
</html>
