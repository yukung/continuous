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
		<p><c:out value="${user}"></c:out></p>
	</article>
	<footer></footer>
</body>
</html>