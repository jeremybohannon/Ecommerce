
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
        <% String breadcrumb = "Home > Payment ";%>
        <%@include file="./header.jsp" %>
        <%@include file="./user-navigation.jsp" %>      
        <div class="content">
            <%@include file="./site-navigation.jsp" %>
            <main class="main">
                <div class="cartHeader">
                    <h1 class="mainTitle">Enter your payment information</h1>
                </div>
                <form class="paymentForm" action="./order" method="post">
                    <div class="paymentInfo">
                        <ul>
                            <li>
                                <span>Credit Card Type</span>
                                <select name="cards" required>
                                   <option value="discover">Discover</option>
                                   <option value="mastercard">Mastercard</option>
                                   <option value="visa">Visa</option>
                                </select>
                            </li>
                            <li>
                                <span>Credit Card Number</span>
                                <input placeholder="XXXX-XXXX-XXXX-XXXX" name="cardNum" type="number" required>
                            </li>
                            <li>
                                <span>Expiration Date (MM/YYYY)</span>
                                <input placeholder="MM" name="month" type="number" min="1" max="12" required>
                                <input placeholder="YYYY" name="year" type="number" min="2018" max="2028" required>
                            </li>
                            <li>
                                <span>CVV (3-Digit)</span>
                                <input placeholder="XXX" name="cvv" type="number" min="100" max="999" required>
                            </li>
                        </ul>
                    </div>
                    <div>
                        <span>Your card will be charged a total of: </span>
                        <span>
                            <fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${subTotal}" type = "currency"/>
                        </span>
                    </div>
                    <div class="cartAndCheckout">
                        <button class="button" type="submit" name="action" value="confirmOrder">Confirm Payment</button>
                    </div>
                </form>
            </main>
        </div>
        <%@include file="./footer.jsp" %>
    </body>
</html>
