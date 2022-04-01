<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	StaffDao staffDao = new StaffDao();
	List<Map<String, Object>> list = staffDao.selectStaffList();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>staffList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<!-- 상단메뉴 -->
	<div class="col-sm-10 bg-secondary text-white">
		<tr>[스마트혼합]공공데이터 융합자바/스프링 개발자 양성과정(19회차) &nbsp&nbsp Teacher : P.SungHwan &nbsp&nbsp Manager : L.SunMin</tr>
	</div>
	<div class="col-sm-10">
		<div>
			<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/index.jsp">index</a>
		</div>
		<div class="mt-4 p-5 bg-dark text-white rounded">
			<h1>Store List</h1>
		</div>
	<table class="table table-hover" border = "2">
		<thead>
			<th>staffId</th>
			<th>storeId</th>
			<th>staffName</th>
			<th>staffAddress</th>
			<th>email</th>
			<th>notes</th>
			<th>username</th>
			<th>lastUpdate</th>
		</thead>
		<tbody>
			<% 
			for(Map m : list) {	
			%>
				<tr>
					 <td><%=m.get("staffId")%></td>
					 <td><%=m.get("storeId")%></td>
					 <td><%=m.get("staffName")%></td>
					 <td><%=m.get("staffAddress")%></td>
					 <td><%=m.get("email")%></td>
					 <td><%=m.get("notes")%></td>
					 <td><%=m.get("username")%></td>
					 <td><%=m.get("lastUpdate")%></td>
				</tr>
			<% 
			}
			%>
		</tbody>
	</table>
	<!--  하단정보표시 -->
			<div class="bg-secondary">
			<div>상호명 : GooDee Academy</div>
			<div>전화 : 02-2108-5900</div>
			<div>팩스 : 02-2108-5909</div>
			<div>사업자등록번호 : 457-85-00300</div>
			<div>홈페이지 : <A href="https://www.gdu.co.kr">https://www.gdu.co.kr</A></div>
			<div>주소 : 서울 금천구 가산디지털2로 115 대륭테크노타운 3차 1109호 71가산디지털단지역 5번 출구에서444m</div>
			</div>
</div>
</body>
</html>