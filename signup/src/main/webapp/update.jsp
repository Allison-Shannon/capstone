<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Update User</title>
</head>
<body>
	<h1>Update User</h1>
	<form action="update" method="post">
		<label>Username:</label>
		<input type="text" name="uname" required>
		<br>
		<label>Password</label>
		<input type="password" name="upwd" required>
		<br>
		<label>Email</label>
		<input type="email" name="uemail" required>
		<br>
		<label>Phone#</label>
		<input type="text" name="umobile" required>
		<br>
		<input type="submit" value="Update">
	</form>
</body>
</html>
