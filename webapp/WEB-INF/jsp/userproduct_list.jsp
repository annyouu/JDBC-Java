<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="object.Product" %>
<% List<Product> product_list = (List<Product>) request.getAttribute("product"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧画面</title>
<style>
body {
  font-family: Verdana, sans-serif;
}
h1 {
 font-size: 28px;
 font-weight: bold;
 color: black;
 text-align: center;
}
table {
 width: 100%;
 border-collapse: collapse;
 font-size: 16px;
 margin: 20px auto;
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
table, th, td {
 border-bottom: 1px solid #ddd;
}
thead, tr, th {
border-bottom: 1px solid black;
}

.table-container {
  width: 80%;
  margin: 30px auto;
  text-align: center;
  }
  
  .btn {
  display: block;
  font-size: 16px;
  padding: 10px 20px;
  font-weight: bold;
  color: white;
  background-color: red;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin: 20px auto;
  text-align: center;
  }
  .btn:hover {
    background-color: rgba(255, 0, 0, 0.7);
}

.searchbtn {
  display: block;
  font-size: 16px;
  padding: 10px 16px;  
  color: blue;
  background-color: white;
  border: solid 1px blue;
  border-radius: 5px;
  cursor: pointer;
  margin: 0px;
  position: relative;
  bottom: 10px;
  left: 1330px;
}

.searchbtn:hover {
   background-color: blue;
   color: white;

}

.search {
  display: block;
  font-size: 16px;
  padding: 6px 10px;
  width: 1200px;
  height: 40px;
  border-radius: 5px;
  border: solid 1px black;
  position: absolute;
  top: 75px;
  left: 240px;
}


</style>
</head>
<body>
	<div class="table-container">
		<h1 class="text-center">商品一覧</h1>
	<form id="searchForm"  action="<%= request.getContextPath() %>/SearchProductServlet" method="get" >
    <input type="text"id="searchInput" name="productName" class="search"  placeholder="商品名で検索" />
    <button type="submit" class="searchbtn">検索</button>
    </form>
		
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
			    <% if (product_list != null) { %>
				<% for(Product pro : product_list ){ %>
				<tr>
					<td><%= pro.getProductid() %></td>
					<td><%= pro.getProductName() %></td>
					<td><%= pro.getCategory() %></td>
					<td><%= pro.getExplanation() %></td>
					<td><%= pro.getQuantity() %></td>
					<td><%= pro.getRegistered_time() %></td>
					<td><%= pro.getUpdated_time() %></td>
				</tr>
				<% } %>
				 <% } else { %>
			        <tr>
			            <td colspan="9" style="text-align: center; color: red;">データがひとつもありません。</td>
			        </tr>
                  <% } %>
			</tbody>
		</table>
	</div>
</body>
</html>