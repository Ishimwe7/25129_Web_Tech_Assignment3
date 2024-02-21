<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2/2/2024
  Time: 9:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>login Page</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/styles.css" />
</head>
<body class="body">
<div class="form">
  <h1 style="color: chocolate"> Login Here</h1>
  <br />
  <form action="<%= request.getContextPath() %>/Login" method="POST">
    <label for="email">Email:</label>
    <input
            style="font-size: larger"
            type="email"
            autofocus
            placeholder="Enter your email here"
            name="email"
            id="email"
    /><br />
    <br />
    <label for="password">Password:</label>
    <input
            style="font-size: larger"
            type="password"
            maxlength="10"
            placeholder="Enter your password"
            name="password"
            id="password"
    /> <br>
    <h3 style="color: red;text-align: center">${error}</h3>
      <h3 style="color: green;text-align: center">${success}</h3>
    <h3 style="color: green;text-align: center">${con}</h3>
    <p class="buttons" style="margin-left: 120px;margin-top: 30px">
      <button style="font-size: x-large" type="submit" name="login">Login</button>
      <button style="font-size: x-large;" type="reset">Reset</button>
    </p>
  </form>
<%--  <p style="font-size: x-large">--%>
<%--    You don't have an account?--%>
<%--    <a href="<%= request.getContextPath() %>/Links/signUp.jsp"> Register Here</a>--%>
<%--  </p>--%>
  <br>
  <h3 style="font-size: x-large" class="home">
    Don't have an account?  Sign up <a href="<%= request.getContextPath() %>/signUp.jsp">here</a>
  </h3>
</div>
</body>
</html>