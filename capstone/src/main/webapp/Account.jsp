<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
        <link rel="stylesheet" href="styles.css">
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<div class="topnav">
        <a href="index.jsp">Home</a>
        <a href="Buy.jsp">Buy</a>
        <a href="Sell.jsp">Sell</a>
        <a href="Account.jsp">Account</a>
        <a href="/capstone/logout">Logout</a>
<input type="text" placeholder="Search...">
<a >&#128269</a>
<a href="Cart.jsp">&#128722</a>
 </div>

    <table id="user">
    <tr>
        <th>My Store</th>
    </tr>
    <tr>
        <td><button id="userbton">Edit Store</button></td>
</tr>
<tr>
        <td><button id="userbton">Products</button></td>
</tr>
<tr>
        <td><button id="userbton">Sales</button></td>
</tr>
<tr>
        <td><button id="userbton">Analytics</button></td>
</tr>
<tr>
        <td><button id="userbton">Account Information</button></td>
</tr>
</table>

<table id="accMGMT">
<tr><td>
<a href="ChangePassword.jsp">Change Password</a>
</td></tr>
<tr><td>
<a href="ChangeAddress.jsp">Change Address</a>
</td></tr>
<tr><td>
<a href="DeleteAccount.jsp">Delete Account</a>
</td></tr>
</table>
</body>
</html>