<%@ page import="ru.webapp.storage.XmlStorage" %>
<%@ page import="ru.webapp.web.HtmlUtil" %>
<%@ page import="ru.webapp.model.ContactType" %>
<%@ page import="ru.webapp.storage.FileStorage" %>
<%@ page import="ru.webapp.storage.SerializeFileStorage" %>
<%@ page import="ru.webapp.web.ResumeServlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%--
  Created by IntelliJ IDEA.
  User: Капу пк
  Date: 28.12.2019
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resume title</title>
</head>
<body>
<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <td colspan = "5" style="text-align: right"><a href="resume?action=create"><img src="https://pngicon.ru/file/uploads/dobavit.png" alt="Добавить резюме" width="30">
            <font size="10px"><b>Добавить Резюме</b></font></a>
        </td>
    </tr>
    <tr>
        <th>Имя</th>
        <th>Проживание</th>
        <th>Email</th>
        <th></th>
        <th></th>
    </tr>
    <%
        request.setAttribute("resumeList", ResumeServlet.storage.getAllSorted());
    %>
    <c:forEach items="${resumeList}" var="resume">
        <jsp:useBean id="resume" type="ru.webapp.model.Resume"/>
        <tr>
            <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
            <td>${resume.location}</td>
            <td><%=HtmlUtil.getContact(resume, ContactType.MAIL)%>
            </td>
            <td><a href="resume?uuid=${resume.uuid}&action=delete"><img width="50px" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTk2bQtTdK_NQ9Jp0NHL1av8CGEkbNeX1fRgrDLuS_MmiIY_u1THA&s"></a></td>
            <td><a href="resume?uuid=${resume.uuid}&action=edit"><img width="50px" src="data:image/png;base64,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"></a> </td>
        </tr>
    </c:forEach>
</table>
$END$
</body>
</html>