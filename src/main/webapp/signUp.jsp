<%--
    Document   : registerVendor
    Created on : Feb 15, 2024, 12:58:57 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/styles.css" />
</head>
<body>

<%--<%--%>
<%--    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");--%>
<%--    if(session.getAttribute("username")==null){--%>
<%--        response.sendRedirect("login.jsp");--%>
<%--    }--%>
<%--%>--%>
<div class="form">
    <h2 style="color: chocolate" >Please Sign Up here</h2>
    <form action="/signUp" method="POST">
        <%--<form action="/signUp.jsp" method="POST">--%>
        <div>
            <label for="shop_name">First name:</label>
            <input
                    style="font-size: large"
                    type="text"
                    autofocus
                    placeholder="Enter first name"
                    name="f_name"
                    id="shop_name"
                    required
            />
        </div>
        <br />
        <div>
            <label for="s_name">Second name:</label>
            <input
                    style="font-size: large"
                    type="text"
                    placeholder="Enter second name"
                    name="s_name"
                    id="s_name"
                    required
            />
        </div>
        <br />
        <div>
            <label for="email">Email:</label>
            <input
                    style="font-size: large"
                    type="text"
                    placeholder="Enter your email"
                    name="email"
                    id="email"
                    required
            />
        </div>
        <h3 style="color: red;text-align: center">${invalidEmail}</h3><br>

        <div>
            <label for="password">Password:</label>
            <input
                    style="font-size: large"
                    type="password"
                    placeholder="Enter your Password"
                    name="password"
                    id="password"
                    required
            />
        </div>
        <br />
        <div>
            <label for="confirm-password">Confirm-Password:</label>
            <input
                    style="font-size: large"
                    type="password"
                    placeholder="Enter your password again"
                    name="confirm-password"
                    id="confirm-password"
                    required
            />
        </div>
        <h3 style="color: red;text-align: center">${passwordMismatches}</h3><br>
        <h3 style="color: red;text-align: center">${error}</h3><h3 style="color: green;text-align: center">${success}</h3><br />
            <h3 style="color: red;text-align: center">${exist}</h3>
        <div class="buttons" style="margin-left: 100px">
            <button style="font-size: x-large" name="admission" type="submit">Submit</button>
            <button style="font-size: x-large" type="reset">Reset</button>
        </div>
    </form>
    <br>
    <h3 style="font-size: x-large;" class="home">
        Already have an account?  <a href="<%= request.getContextPath() %>/login.jsp">Login</a>
    </h3>
</div>
</body>
</html>