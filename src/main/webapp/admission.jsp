<%--
    Document   : registerVendor
    Created on : Aug 31, 2023, 12:58:57 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student Admission</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/styles.css" />
</head>
<body>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if(session.getAttribute("username")==null){
        response.sendRedirect("login.jsp");
    }
%>
<div class="form">
    <h2 style="color: chocolate" >Please Fill this Admission Form</h2>
    <form action="/admission" method="POST" enctype="multipart/form-data">
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
            <label for="phone_number">Phone number:</label>
            <input
                    style="font-size: large"
                    type="text"
                    placeholder="Enter your phone number"
                    name="phone_number"
                    id="phone_number"
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
                <label for="department">Department:</label>
                <select style="font-size: large" id="department" name="department">
                    <option>Software Engineering</option>
                    <option>Information Management</option>
                    <option>Networking</option>
                </select>
            </div>
            <br />

            <div>
                <label for="department">Passport:</label>
                <input type="file" accept="image/jpeg, image/png" style="font-size: large" id="passport" name="passport">
            </div>
            <h3 style="color: red;text-align: center">${invalidPassport}</h3><br>

            <div>
                <label for="diploma">Diploma:</label>
                <input type="file" accept=".pdf" style="font-size: large" id="diploma" name="diploma">
            </div>
            <h3 style="color: red;text-align: center">${invalidDiploma}</h3>
            <br />

        <div>
            <label for="male">Gender:</label>
            <label style="font-size: large; padding-left: 20px" for="male">Male</label>
            <input
                    style="font-size: large; size: 20px"
                    type="radio"
                    name="gender"
                    id="male"
                    value="M"
                    required
            />
            <label style="font-size: large" for="female">Female</label>
            <input
                    style="font-size: xx-large"
                    type="radio"
                    name="gender"
                    id="female"
                    value="F"
                    required
            />
        </div>
        <br />
            <div>
                <label for="bd">Birth Date:</label>
                <label style="font-size: large; padding-left: 20px" for="bd">Male</label>
                <input
                        style="font-size: large; size: 20px"
                        type="date"
                        name="bd"
                        id="bd"
                        required
                />
            </div>
            <br />
        <div>
            <label for="country">Country:</label>
            <select style="font-size: large" name="country" id="country">
                <optgroup label="Africa">
                    <option selected>Rwanda</option>
                    <option>Uganda</option>
                    <option>Kenya</option>
                    <option>Tanzania</option>
                    <option>Burundi</option>
                    <option>DR Congo</option>
                    <option>Congo</option>
                    <option >Sudan</option>
                </optgroup>
                <optgroup label="Others">
                    <option>United States</option>
                    <option>United Kingdom</option>
                    <option>Italy</option>
                    <option>Australia</option>
                    <option>New Zealand</option>
                    <option>Japan</option>
                    <option>China</option>
                    <option> India</option>
                </optgroup>
            </select>
        </div>
        <br />
        <div>
            <label for="city">City:</label>
            <input
                    style="font-size: large"
                    type="text"
                    placeholder="Enter your Residental City"
                    name="city"
                    id="city"
                    required
            />
        </div>
        <br />
        <div>
            <label for="address">Address:</label>
            <input
                    style="font-size: large"
                    type="text"
                    placeholder="Enter your Address"
                    name="address"
                    id="address"
                    required
            />
        </div>
        <br />
       <h3 style="color: red;text-align: center">${error}</h3><h3 style="color: green;text-align: center">${success}</h3><br />
            <h3 style="color: red;text-align: center">${email}</h3><br />
        <div class="buttons">
            <button style="font-size: x-large" name="admission" type="submit">Submit</button>
            <button style="font-size: x-large" type="reset">Reset</button>
        </div>
    </form>
    <h3 style="font-size: x-large;" class="home">
         <a href="/logout">Logout</a>
    </h3>
</div>
</body>
</html>