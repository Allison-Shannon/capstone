<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Account</title>
</head>
<body>
    <h1>Delete Account</h1>
    
    <p>Warning: This action is permanent and cannot be undone. Please enter your password to confirm:</p>
    
    <form action="DeleteAccountServlet" method="post">
        <input type="password" name="password" placeholder="Password">
        <input type="submit" value="Confirm Delete">
    </form>
    
</body>
</html>