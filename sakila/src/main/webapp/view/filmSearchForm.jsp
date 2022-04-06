<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	CategoryDao categoryDao = new CategoryDao();
	List<Category> categoryList = categoryDao.selectCategoryList();	
	FilmDao filmDao = new FilmDao();
	List<Double> priceList = filmDao.selectfilmPriceDistinctList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmSearchForm</title>
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
			<h1>film List View 검색</h1>
		</div>
	<form action="<%=request.getContextPath()%>/view/filmSearchAction.jsp" method="post">
		<table class="table table-hover" border = "2">
			<tr>
				<td>카테고리</td>
				<td>
					<select name="category">
						<option value="">카테고리선택</option>
						<%
							for(Category c : categoryList) {
						%>
								<option value="<%=c.getName()%>"><%=c.getName()%></option>
						<%		
							}
						%>
					</select>				
				</td>
			</tr>
			<tr>
				<td>등급</td>
				<td>
					<select name="rating">
						<option value="">등급선택</option>
						<option value="G">G</option>
						<option value="PG">PG</option>
						<option value="PG-13">PG-13</option>
						<option value="R">R</option>
						<option value="NC-17">NC-17</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>대여료</td>
				<td>
					<div><input type="radio" name="price" value="" checked="checked">선택안함</div>
					<%
						for(Double p : priceList) {
					%>
							<div><input type="radio" name="price" value="<%=p%>"><%=p%></div>
					<%		
						}
					%>
				</td>
			</tr>
			<tr>
				<td>영화시간</td>
				<td>
					<div><input type="radio" name="length" value="" checked="checked">선택안함</div>
					<div><input type="radio" name="length" value="0">1시간 미만</div><!-- lenght < 60 -->
					<div><input type="radio" name="length" value="1">1시간 이상</div><!-- lenght >= 60 -->
				</td>
			</tr>
			<tr>
				<td>제목 검색</td>
				<td>
					<input type="text" name="title">
				</td>
			</tr>
			<tr>
				<td>배우 검색</td>
				<td>
					<input type="text" name="actor">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">검색</button>
				</td>
			</tr>
		</table>

	</form>
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