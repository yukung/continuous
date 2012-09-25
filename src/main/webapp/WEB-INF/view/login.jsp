<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8"/>
<title>Hello, world!</title>
</head>
<body>
	<header>
		<h1>テスト</h1>
	</header>
	<nav></nav>
	<article>
		<c:forEach var="msg" items="${msgs}" varStatus="status">
			<p><c:out value="${status.index}"/>:<c:out value="${msg}"/></p>
		</c:forEach>
	</article>
	<footer></footer>
</body>
</html>