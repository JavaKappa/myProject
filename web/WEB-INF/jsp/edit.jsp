<%@ page import="ru.webapp.model.Resume" %><%--
  Created by IntelliJ IDEA.
  User: Капу пк
  Date: 11.01.2020
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=((Resume)request.getAttribute("resume")).getFullName()%></title>

</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<p>Editing this resume</p>
<jsp:include page="fragments/footer.jsp"/>


</body>
</html>
