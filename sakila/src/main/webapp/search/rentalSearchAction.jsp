<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	int storeId =-1; //선택 없음 = -1
	if(request.getParameter("storeId")!=null&&!request.getParameter("storeId").equals("")){
   		storeId = Integer.parseInt(request.getParameter("storeId"));
  		System.out.println(storeId+"<--storeId");//디버깅
	}
	String customerName = request.getParameter("customerName");
	String beginDate = request.getParameter("beginDate");
	String endDate = request.getParameter("endDate");
	
	// 페이지 설정 변수선언
	int currentPage = 1;
	int rowPerPage = 10;
	int beginRow = (currentPage - 1) * rowPerPage;
	
	//
	RentalDao rentalDao = new RentalDao();
	List<Map<String, Object>> list = rentalDao.selectRentalSearchList(beginRow, rowPerPage, storeId, customerName, beginDate, endDate);
	
	//페이지 설정
	if(request.getParameter("currentPage") != null) { // 이전, 다음 링크를 통해서 들어왔다면
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+ " <-- currentPage"); // 디버깅
	
	// 페이지 바뀌면 끝이 아니고, 가지고 오는 데이터가 변경되어야 한다.
	rowPerPage = 5;
	beginRow = (currentPage-1)*rowPerPage; // 현재페이지가 변경되면 beginRow도 변경된다. -> 가져오는 데이터 변경된다.
	
	// 전체 행의수
	int totalCount = rentalDao.rentalSearchTotalRow(storeId, customerName, beginDate, endDate);
	
	// 마지막페이지 설정
	int lastPage = 0;
	lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmSearchAction</title>
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
			<h1>rentalSearch List 결과</h1>
		</div>
	<table class="table table-hover" border="2">
		<tr>
			<td>rentalId</td>
			<td>rentalDate</td>
			<td>inventoryId</td>
			<td>customerId</td>
			<td>returnDate</td>
			<td>staffId</td>
			<td>lastUpdate</td>
			<td>customerName</td>
			<td>storeId</td>
			<td>filmId</td>
			<td>title</td>
		</tr>
		<%
			for(Map m : list) {
		%>
				<tr>
					<td><%=m.get("rentalId")%></td>
					<td><%=m.get("rentalDate")%></td>
					<td><%=m.get("inventoryId")%></td>
					<td><%=m.get("customerId")%></td>
					<td><%=m.get("returnDate")%></td>
					<td><%=m.get("staffId")%></td>
					<td><%=m.get("lastUpdate")%></td>
					<td><%=m.get("customerName")%></td>
					<td><%=m.get("storeId")%></td>
					<td><%=m.get("filmId")%></td>
					<td><%=m.get("title")%></td>
				</tr>
		<%		
			}
		%>
	</table>
				<div class="btn-group">		
		<%
			if(currentPage > 1) { // 현재페이지가 1이면 이전페이지가 존재해서는 안된다.
		%>
				<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/Search/rentalSearchAction.jsp?currentPage=<%=currentPage-1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>" class="btn btn-primary">이전</a>
		<%	
			}
			if(currentPage < lastPage) { // 마지막페이지가 있다면 
		%>
			<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/Search/rentalSearchAction.jsp?currentPage=<%=currentPage+1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>" class="btn btn-primary">다음</a>
		<%		
			}
		%>
		</div>
		<!--  하단정보표시 -->
		<jsp:include page="/inc/bottomMenu.jsp"></jsp:include>
</div>
</body>
</html>