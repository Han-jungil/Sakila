<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%
	// 변수 선언
	int filmId = 0;
	int storeId = 0;
	int count = 0;
	
	// 요청값
	if((request.getParameter("filmId")!=null && request.getParameter("filmId")!="") && (request.getParameter("storeId")!=null && request.getParameter("storeId")!="")){
		filmId = Integer.parseInt(request.getParameter("filmId"));
		System.out.println(filmId+"<--filmId");
		storeId = Integer.parseInt(request.getParameter("storeId"));
		System.out.println(storeId+"<--storeId");
	}
	
	// 비지니스 로기직(모델계층)
	FilmDao filmDao = new FilmDao();
	Map<String,Object> map = filmDao.filmInStockCall(filmId, storeId);
	
	// 카운터 
	List<Integer> list = (List<Integer>)map.get("list");
	count = (Integer)map.get("count");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmInStockList</title>
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
			<h1>filmInStock List(procedure)</h1>
		</div>
	<form method="post" action="<%=request.getContextPath()%>/procedure/filmInStockList.jsp">
		<button type="submit" class="btn-dark">조회</button>
		<table border="1" class="table">
			<tr>
				<td>filmId</td>
				<td><input type="number" name="filmId" class="form-control"></td>
				<td>storeId</td>
				<td><input type="number" name="storeId" class="form-control"></td>
			</tr>
		</table>
	</form>
	<table class="table table-hover" border = "2">
		<thead>
			<h5><%=filmId%>번 영화는 <%=storeId%>번 가게에 <%=count%>개 재고</h5>
			<th>filmId</th>
			<th>storeId</th>
			<th>count</th>
		</thead>
		<tbody>
			<tr>
				 <td><%=filmId%></td>
				 <td><%=storeId%></td>
				 <td><%=count%></td>
				 <h5>inventoryId :
				 <%		
				 for(int i : list) {
				 %>
				 	<%=i%>,
				 <%
				}
				 %>
				 </h5>
			</tr>


		</tbody>
	</table>
	<!--  하단정보표시 -->
	<jsp:include page="/inc/bottomMenu.jsp"></jsp:include>
</div>
</body>
</html>