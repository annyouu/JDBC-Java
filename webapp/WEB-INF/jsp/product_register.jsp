<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録画面</title>
<style>
	body {
		margin: 0;
		font-family: Arial, sans-serif;
		display: flex;
		justify-content: center;
	}

	.container {
		background-color: #ffffff;
		padding: 50px 60px;
		margin-top: 30px;
		width: 360px;
		border-radius: 10px;
		box-shadow: 0 4px 10px rgba(0 ,0, 0, 0.2);
		text-align: center;
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
		margin-bottom: 20px;
		
	}

	.btn:hover {
		background-color: rgb(7, 116, 241);
	}
	

</style>
</head>
<body>
	<div class="container">
		<h1 class="title">商品登録画面</h1>
		<form action="/ProductJavaMysql/ProductRegisterServlet" method="post">
			<div class="form">
				<label for="productName" class="form-label">商品名</label>
				<input type="text" id="productName" name="product_name" class="form-input" required>
			</div>
			<div class="form">
				<label for="category" class="form-label">分類</label>
				<input type="text" id="category" name="category" class="form-input" required>
			</div>
			<div class="form">
				<label for="explanation" class="form-label">説明</label>
				<input type="text" id="explanation" name="explanation" class="form-input" required>
			</div>
			<div class="form">
				<label for="quantity" class="form-label">在庫数</label>
				<input type="number" id="quantity" name="quantity" class="form-input" required>
			</div>
			<button type="submit" class="btn">登録する</button>
		</form>
		<a href="#"  onclick="window.history.back(); return false;">商品一覧画面へ戻る</a>
	</div>
</body>
</html>