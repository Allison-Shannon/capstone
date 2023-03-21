<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>

<form action="changepassword" method="post">
    <table>
        <h2>Change Password</h2>
        <tr>
            <td>
                <input type="password" name="currentpassword" placeholder="Old Password"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="password" name="newpassword" placeholder="New Password"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="password" name="confirmpassword" placeholder="Confirm Password"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" id="signin" value="Change Password" />	
	            <a href="index.jsp">Back to Home</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>