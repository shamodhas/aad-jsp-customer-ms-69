<%--
  Created by IntelliJ IDEA.
  User: shamo
  Date: 1/11/2025
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Delete</title>
</head>
<body>
<h1>Delete Customer</h1>

<%
    String message = request.getParameter("message");
    String error = request.getParameter("error");
%>
<%
    if (message != null) {
%>
<div style="color: green"><%=message%>
</div>
<%
    }
%>
<%
    if (error != null) {
%>
<div style="color: red"><%=error%>
</div>
<%
    }
%>

<form action="customer-delete" method="post">
    <label for="id">ID</label><br/>
    <input type="number" id="id" name="customer_id"/>
    <br/><br/>
    <button type="submit">Delete Customer</button>
</form>

</body>
</html>
