<%--
  Created by IntelliJ IDEA.
  User: Капу пк
  Date: 07.01.2020
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Резюме этого персонажа</title>
</head>
<body>
<p>
    <%=request.getAttribute("resume")%>
</p>
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAbVwxsN6PK1L-5zHlW4IflmuEAjWDozxPt9dbkkAM9I_Gt95b&s" alt = "Фото" >
</body>
</html>
