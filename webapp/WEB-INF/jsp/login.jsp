<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログイン画面</title>
<style>
	body {
		margin: 0;
		font-family: Arial, sans-serif;
		display: flex;
		justify-content: center;
	}

	.container {
		background-color: #ffffff;
		padding: 60px 70px;
		margin-top: 30px;
		width: 360px;
		border-radius: 10px;
		box-shadow: 0 4px 10px rgba(0 ,0, 0, 0.2);
	}

	.title {
		font-size: 26px;
		margin-top: 0;
		text-align: center;
		color: black;
	}

	.form {
		margin-bottom: 15px;
		text-align: left;
	}

	.form-label {
		font-size: 20px;
		color: black;
		margin-bottom: 5px;
		display: block;
	}

	.form-input {
		width: 100%;
		padding: 10px;
		font-size: 16px;
		border: 1px solid black;
		border-radius: 5px;
		box-sizing: border-box;
	}

	.btn {
		padding: 10px 20px;
		font-size: 16px;
		background-color: blue; 
		color: white;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		transition: background-color 0.3s ease;
		display: block;
		margin: 40px auto 0;
		
	}

	.btn:hover {
		background-color: rgb(7, 116, 241);
	}
	
	.text-danger {
		color: red;
		font-size: 20px;
		padding: 0px;
		margin: 0px;
		position: absolute;
		top: 410px;
	}

</style>
</head>
<body>
	<div class="container">
		<h1 class="title">管理者ログイン画面</h1>
		<form action="/ProductJavaMysql/AdminServlet" method="post">
			<div class="form">
				<label for="adminID" class="form-label">管理者ID</label>
				<input type="text" id="adminID" name="admin_id" class="form-input">
			</div>
			<div class="form">
				<label for="pass" class="form-label">パスワード</label>
				<input type="password" id="pass" name="password" class="form-input">
			</div>
			<button type="submit" class="btn">ログイン</button>
		</form>
	
	
	<% 
	   String errorMsg = (String)request.getAttribute("errorMsg");
		 if (errorMsg != null) {
	%>		
	    <p class="text-danger"><%= errorMsg %></p>
	<%
		 }
	%>
	</div>
</body>
</html>