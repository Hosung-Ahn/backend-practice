<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
성공
<p>this is mvc member save-result page</p>
<ul>
    <li>id : ${member.id}</li>
    <li>username : ${member.username}</li>
    <li>age : ${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
