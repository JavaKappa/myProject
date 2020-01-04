<%@ page import="ru.webapp.storage.XmlStorage" %>
<%@ page import="ru.webapp.web.HtmlUtil" %><%--
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
        request.setAttribute("resumeList", new XmlStorage("xml_storage"));
    %>
</table>
$END$
</body>
</html>