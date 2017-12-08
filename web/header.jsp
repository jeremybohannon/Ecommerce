<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">
    <c:if test="${theUser != null}">
        <a id="userName" href="./signin?action=signOut">Sign out</a>
    </c:if>
    <c:if test="${theUser == null}">
        <a id="userName" href="./signin.jsp">Sign In</a>
    </c:if>
    <div class="headerContent">
        <h1 id='title'>Mariner Miata</h1>
        <img id="logo" src="./resources/logo.png" alt="logo"/>
    </div>
    <div id="breadCrumb">
        <a href="./index.jsp"><%=breadcrumb%>
        </a>
    </div>
</header>