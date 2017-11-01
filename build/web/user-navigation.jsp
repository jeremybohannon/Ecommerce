<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="userNav">
    <ul id="userList">
        <c:if test="${theUser != null}">
            <li><a href="./404.jsp">${theUser.firstName} ${theUser.lastName}</a></li>
        </c:if>
        <c:if test="${theUser == null}">
            <li><a href="./404.jsp">Sign In</a></li>
        </c:if>
        <li><a href="./cart.jsp">My Cart</a></li>
        <li><a href="./catalog">Catalog</a></li>
    </ul>
</nav>