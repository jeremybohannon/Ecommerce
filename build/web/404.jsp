<!DOCTYPE html>
<html>
    <head>
        <title>Mariner Miata</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="./resources/favicon-car.ico" type="image/x-icon">
        <link href="./styles/stylesheet.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <% String breadcrumb = "Home > 404";%>
        <%@include file="./header.jsp" %>
        <%@include file="./user-navigation.jsp" %>
        <div class="content">
            <%@include file="./site-navigation.jsp" %>
            <main class="main">
                <h1 class="mainTitle">Oops! This page has not been made yet or does not exist! Check back later!</h1>
            </main>
        </div>
        <%@include file="./footer.jsp" %>
    </body>
</html>
