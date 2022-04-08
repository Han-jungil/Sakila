<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	StatsDataDao statsDataDao = new StatsDataDao();
	
	// 1. customer별 총 amount
	List<Map<String, Object>> amountByCustomer = statsDataDao.amountByCoustomer();
	// 2. rental_rate별 영화갯수
	List<Map<String, Object>> rentalRateBycount = statsDataDao.rentalRateBycount();
	// 3. rating별 영화갯수
	List<Map<String, Object>> ratingBycount = statsDataDao.ratingByCount();
	// 4. language별 영화 개수
	List<Map<String, Object>> languageByCount = statsDataDao.languageByCount();
	// 5. Language별 영화 개수(구간, 기준을 정해서)
	List<Map<String, Object>> languageByLengthCount = statsDataDao.languageByLengthCount();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StatsData</title>
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
			<h1>StatsDataList</h1>
		</div>
	<h3>1. amountByCostomer(180이상)</h3>
	<table class="table table-hover" border="1">
		<tr>
			<td>고객아이디</td>
			<td>고객이름</td>
			<td>총지불액</td>
		</tr>
	<%
		for(Map<String, Object> m : amountByCustomer) {
	%>
		<tr>
			<td><%=m.get("customerId")%></td>
			<td><%=m.get("name") %></td>
			<td><%=m.get("total") %></td>
		</tr>
	<%
		}
	%>
	</table>
	<h3>2. rentalRateBycount</h3>
	<table class="table table-hover" border="1">
		<tr>
			<td>rentalRate</td>
			<td>count</td>
		</tr>
			<%
		for(Map<String, Object> m : rentalRateBycount) {
		%>
		<tr>
			<td><%=m.get("rentalRate")%></td>
			<td><%=m.get("cnt") %></td>
		</tr>
		<%
		}
		%>
	</table>
	<h3>3. ratingBycount</h3>
	<table class="table table-hover" border="1">
		<tr>
			<td>rating</td>
			<td>count</td>
		</tr>
		<%
			for(Map<String, Object> m : ratingBycount) {
		%>
		<tr>
			<td><%=m.get("rating")%></td>
			<td><%=m.get("cnt") %></td>
		</tr>
		<%
		}
		%>
	</table>
	<h3>4. languageByCount</h3>
	<table class="table table-hover" border="1">
		<tr>
			<td>name</td>
			<td>count</td>
		</tr>
		<%
			for(Map<String, Object> m : languageByCount) {
		%>
		<tr>
			<td><%=m.get("name")%></td>
			<td><%=m.get("cnt") %></td>
		</tr>
		<%
		}
		%>
	</table>
		<h3>5. languageByLengthCount</h3>
	<table class="table table-hover" border="1">
		<tr>
			<td>length</td>
			<td>count</td>
		</tr>
		<%
			for(Map<String, Object> m : languageByLengthCount) {
		%>
		<tr>
			<td><%=m.get("length")%></td>
			<td><%=m.get("cnt") %></td>
		</tr>
		<%
		}
		%>
	</table>
	</div>
	<!--  하단정보표시 -->
	<jsp:include page="/inc/bottomMenu.jsp"></jsp:include>
</div>
</body>
</html>