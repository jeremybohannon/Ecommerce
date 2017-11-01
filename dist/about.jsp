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
        <% String breadcrumb = "Home > About";%>
        <%@include file="./header.jsp" %>
        <%@include file="./user-navigation.jsp" %>      
        <div class="content">
            <%@include file="./site-navigation.jsp" %>
            <main class="main">
                <h1 class="mainTitle">About Mariner Miata!</h1>
                <p class="mainContent">We have the largest collection of new and used Mazda Miata parts! We've been around since 1992 when we realized the Mazda Miata is the best vehicle made ever!</p>
                <p class="mainContent">We're a family owned business, keeping our nation strong by keeping our economy running.</p>
                <p class="mainContent">We want you to have the best buying experience possible so feel free to contact us!</p>
            </main>
        </div>
        <%@include file="./footer.jsp" %>
    </body>
</html>
