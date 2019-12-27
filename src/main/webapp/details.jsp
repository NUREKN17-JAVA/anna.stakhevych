<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" class = "ua.nure.kn.stakhevych.domain.User" scope="session"/>
<html>
<head><title>User management/Details</title></head>
<body>
	<form action="<%=request.getContextPath()%>/details" method ="post">
	
	<p>First name : $(user.firstName)</p><br>
	<p>Last Name : $(user.lastName)</p><br>
	<p>Date of birth : $(user.dateOfBirth)</p><br>
	<input type = "submit" name="cancelButton" value="Cancel">
	</form>
<c:if test="$(requestScope.error!=null)">
	<script>
		alert('&(requestScope.error)');
	</script>
</c:if>
</body>
</html>