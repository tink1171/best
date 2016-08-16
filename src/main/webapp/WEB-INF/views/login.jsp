<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/spring-social/social/tags" prefix="social" %>

<fmt:setBundle basename="messages" />
<fmt:message key="message.password" var="noPass" />
<fmt:message key="message.username" var="noUser" />
<!DOCTYPE html>
<html>
<head>

 <script type="text/javascript">
  function validate() {
      if (document.f.email.value == "" && document.f.password.value == "") {
          alert("${noUser} and ${noPass}");
      document.f.username.focus();
      return false;
      }
      if (document.f.email.value == "") {
      alert("${noUser}");
      document.f.username.focus();
      return false;
       }
       if (document.f.password.value == "") {
      alert("${noPass}");
      document.f.password.focus();
      return false;
       }
  }
  </script>

  <c:if test="${param.regSucc == true}">
      <div id="status">
      <spring:message code="message.regSucc">
          </spring:message>
      </div>
  </c:if>
  <c:if test="${param.regError == true}">
      <div id="error">
          <spring:message code="message.regError">
          </spring:message>
      </div>
  </c:if>

</head>
<body>

   <h1>Login</h1>
   <form name='f' action="login" method='POST' onsubmit="return validate()">
      <table>
         <tr>
            <td>Email:</td>
            <td><input type='text' name='username' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
         </tr>
      </table>
  </form>

  <p>Login with social accounts</p>
 	<form action="/connect/facebook" method="POST">
    			<input type="hidden" name="scope" value="user_posts" />
    			<p><button type="submit">Connect to Facebook</button></p>
    		</form>
</body>
</html>





