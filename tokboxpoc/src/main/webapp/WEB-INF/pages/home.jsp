<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
	<h2>Welcome to ChatRoom</h2>

	<h3>Available Sessions</h3>

	<table border="1px" cellpadding="8px">
		<c:forEach items="${sessions}" var="current">
			<tr>
				<td><a href="joinChat.html?sessionId=${current}"><c:out
							value="${current}" /></a></td>
			</tr>
		</c:forEach>
	</table>

	<a href="createChat.html">Create new chat room</a>

</body>
</html>