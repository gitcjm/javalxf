<%--
  Created by IntelliJ IDEA.
  User: cjm
  Date: 6/19/20
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.str.mvc.bean.*" %>
<%
    User user = (User) request.getAttribute("user");
%>
<html>
<head>
    <title>Hello - JSP</title>
</head>
<body>
    <h1>Hello <%= user.name %></h1>
    <p>School Name:
        <span style="color:red">
            <%= user.school.name %>
        </span>
    </p>
    <p>School Address:
        <span style="color:red">
            <%= user.school.address %>
        </span>
    </p>
</body>
</html>
