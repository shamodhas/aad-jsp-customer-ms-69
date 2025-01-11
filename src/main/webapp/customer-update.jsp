<%--
  Created by IntelliJ IDEA.
  User: shamo
  Date: 1/11/2025
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Customer</title>
</head>
<body>
<h1>Update Customer</h1>

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

<form action="customer-update" method="post">
    <label for="id">ID</label><br>
    <input type="text" id="id" name="customer_id"/><br><br>

    <label for="name">Name</label><br>
    <input type="text" id="name" name="customer_name"/><br><br>

    <label for="address">Address</label><br>
    <input type="text" id="address" name="customer_address"/><br><br>

    <label for="email">EMail</label><br>
    <input type="text" id="email" name="customer_email"/><br><br>

    <button type="submit">Update Customer</button>
    <%--    <input type="submit" value="Save Customer">--%>
</form>
</body>
</html>
