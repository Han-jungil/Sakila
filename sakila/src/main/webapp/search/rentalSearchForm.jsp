<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%
	StoreDao storeDao = new StoreDao();
	List<Integer> storeIdList = storeDao.selectStoreIdList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rentalSearchForm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">

	<!-- 상단메뉴 -->
	<div class="col-sm-15 bg-secondary text-white">
		<tr>[스마트혼합]공공데이터 융합자바/스프링 개발자 양성과정(19회차) &nbsp&nbsp Teacher : P.SungHwan &nbsp&nbsp Manager : L.SunMin</tr>
	</div>
	<div class="col-sm-15">
		<div>
			<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/index.jsp">index</a>
		</div>
		<div class="mt-4 p-5 bg-dark text-white rounded">
			<h1>rental 검색</h1>
		</div>
	<form action="<%=request.getContextPath()%>/search/rentalSearchAction.jsp" method="post">
		<table class="table table-hover" border = "2">
			<!--  가게아이디 검색 -->
			<tr>
				<td>스토어 ID</td>
				<td>
					<%
						for(int i : storeIdList) {
					%>
							<div><input type="radio" name="storeId" value="<%=i %>"><%=i %>번 가게</div>
					<%
						}
					%>
				</td>
			</tr>
			<!-- 고객이름 검색 -->
			<tr>
				<td>고객이름</td>
				<td><input type="text" name="customerName"></td>
			</tr>
			<!-- 대여일자 검색 -->
			<tr>
				<td>
				대여일자(대여 날짜 검색기간: 2005-05-24 ~ 2006-02-14)에서만 검색
				</td>
				<td><input type="date" name="beginDate"> ~ <input type="date" name="endDate"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">검색</button>
				</td>
			</tr>
		</table>
	</form>
	<!--  하단정보표시 -->
	<jsp:include page="/inc/bottomMenu.jsp"></jsp:include>
</div>
</body>
</html>