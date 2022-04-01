<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	StoreDao storeDao = new StoreDao();
	List<Map<String, Object>> list = storeDao.selectStoreList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>storeList</title>
</head>
<body>
	<a href="">index</a>
	<h1>Store List</h1>
	<table border = "1">
		<thead>
			<th>storeId</th>
			<th>staffId</th>
			<th>staffname</th>
			<th>addressId</th>
			<th>staffAddress</th>
			<th>lastUpdate</th>
		</thead>
		<tbody>
			<% 
			for(Map m : list) {	
			%>
				<tr>
					 <td><%=m.get("storeId")%></td>
					 <td><%=m.get("staffId")%></td>
					 <td><%=m.get("staffname")%></td>
					 <td><%=m.get("addressId")%></td>
					 <td><%=m.get("staffAddress")%></td>
					 <td><%=m.get("lastUpdate")%></td>
				</tr>
			<% 
			}
			%>
		</tbody>
	</table>
</body>
</html>