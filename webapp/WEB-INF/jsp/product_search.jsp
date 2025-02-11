<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html lang="ja">
	<%@ page import="java.util.List" %>
		<%@ page import="object.Product" %>
			<% List<Product> product_details = (List<Product>)request.getAttribute("product_details"); %>
				
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>商品検索結果</title>
	<style>
		body {
			font-family: Verdana, sans-serif;
		}

		table {
			width: 100%;
			border-collapse: collapse;
			margin: 20px auto;
			font-size: 16px;
		}

		th {
			background-color: white;
			color: black;
			padding: 10px;
			font-weight: bold;
			border-bottom: 2px solid black;
			text-align: center;
		}

		td {
			padding: 10px;
			border-bottom: 1px solid #ddd;
			text-align: center;
		}

		tr:nth-child(even) {
			background-color: #f9f9f9;
		}

		table,
		th,
		td {
			border-bottom: 1px solid #ddd;
		}

		thead,
		tr,
		th {
			border-bottom: 1px solid black;
		}

		.container {
			width: 80%;
			margin: 30px auto;
			text-align: center;
		}
	</style>
</head>

<body>
	<div class="container">
		<h2>商品検索結果</h2>
		<table>
			<thead>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>分類</th>
					<th>説明</th>
					<th>在庫数</th>
					<th>登録日</th>
					<th>更新日</th>
				</tr>
			</thead>
			<tbody>
				<% if (product_details !=null && !product_details.isEmpty()) { %>
					<% for(Product pro : product_details) { %>
						<tr>
							<td>
								<%= pro.getProductid() %>
							</td>
							<td>
								<%= pro.getProductName() %>
							</td>
							<td>
								<%= pro.getCategory() %>
							</td>
							<td>
								<%= pro.getExplanation() %>
							</td>
							<td>
								<%= pro.getQuantity() %>
							</td>
							<td>
								<%= pro.getRegistered_time() %>
							</td>
							<td>
								<%= pro.getUpdated_time() %>
							</td>
						</tr>
						<% } %>
							<% } else { %>
								<tr>
									<td colspan="7">データがありません</td>
								</tr>
								<% } %>
			</tbody>
		</table>

		<a href="#" onclick="window.history.back(); return false;">商品一覧画面へ</a>
	</div>
</body>

</html>