<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" />
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
   <%-- <link href="<c:url value="/resources/bootstrap.css" />" rel="stylesheet">--%>
    <title>Expired</title>
</head>
<body>
    <h1>${message}</h1>
    <br>
    <a href="<c:url value="/registration" />">
        <spring:message code="label.form.loginSignUp"></spring:message>
    </a>
</body>
</html>