<%@page pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>11</title>
</head>
<body>
<h2>Hello World!</h2>
<form action="/auth/login" method="post">
    <c:if test="${param.error != null}">
        <p>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>
    <%--    <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>--%>
    <button type="submit" class="btn">Log in</button>
</form>
</body>
</html>
