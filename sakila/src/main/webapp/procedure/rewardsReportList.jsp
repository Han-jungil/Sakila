<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%
	// 변수 선언
	int minMonthlyPurchases = 0;
	int minDollarAmuntPurchased =0; 
	int count = 0;
	
	// 요청값
	if(request.getParameter("minMonthlyPurchases")!=null&&!request.getParameter("minMonthlyPurchases").equals("")){
		minMonthlyPurchases = Integer.parseInt(request.getParameter("minMonthlyPurchases"));
		System.out.println(minMonthlyPurchases+"<--minMonthlyPurchases");
	}

	if(request.getParameter("minDollarAmuntPurchased")!=null&&!request.getParameter("minDollarAmuntPurchased").equals("")){
		minDollarAmuntPurchased = Integer.parseInt(request.getParameter("minDollarAmuntPurchased"));
		System.out.println(minDollarAmuntPurchased+"<--minDollarAmuntPurchased");
	}

	// 비지니스 로기직(모델계층)
	RewardsReportDao rewardsReportDao = new RewardsReportDao();
	Map<String,Object> rewardsReport = rewardsReportDao.rewardsReportCall(minMonthlyPurchases, minDollarAmuntPurchased);
	
	// 카운터
	count = (Integer)rewardsReport.get("count");
	List<Customer> customerList = (List<Customer>)rewardsReport.get("customer");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RewardsReportList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<!-- 상단메뉴 -->
	<div class="col-sm-12 bg-secondary text-white">
		<tr>[스마트혼합]공공데이터 융합자바/스프링 개발자 양성과정(19회차) &nbsp&nbsp Teacher : P.SungHwan &nbsp&nbsp Manager : L.SunMin</tr>
	</div>
	<div class="col-sm-12">
		<div>
			<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/index.jsp">index</a>
		</div>
		<div class="mt-4 p-5 bg-dark text-white rounded">
			<h1>rewardsReport List(procedure)</h1>
		</div>
	<form method="post" action="<%=request.getContextPath()%>/procedure/rewardsReportList.jsp">
	<button type="submit" class="btn-dark">조회</button>
	<table border="1" class="table">
		<tr>
			<td>minMonthlyPurchases</td>
			<td><input type="number" name="minMonthlyPurchases" class="form-control"></td>
			<td>minDollarAmountPurchased</td>
			<td><input type="number" name="minDollarAmountPurchased" class="form-control"></td>
		</tr>
		</table>
	</form>
	<%
	if(minMonthlyPurchases!=0 && minDollarAmuntPurchased != 0.0 ){
	%>
	<table class="table table-hover col-sm-11">
		<thead>
			<th>customerId</th>
			<th>storeId</th>
			<th>Name</th>
			<th>email</th>
			<th>addressId</th>
			<th>active</th>
			<th>createDate</th>
			<th>lastupDate</th>
		</thead>
		<tbody>
		<h5><%=minMonthlyPurchases%>번 사신 손님들의 <%=minDollarAmuntPurchased%>금액보다 산게 <%=count%>번 정도 있음</h5>
		<%
			for(Customer c : customerList) {
		%>
			<tr>
				<td><%=c.getCustomerId()%></td>
				<td><%=c.getStoreId()%></td>
				<td><%=c.getFirstName()%></td>
				<td><%=c.getLastName()%></td>
				<td><%=c.getEmail()%></td>
				<td><%=c.getAddressId()%></td>
				<td><%=c.getActive()%></td>
				<td><%=c.getCreateDate()%></td>
				<td><%=c.getLastUpdate()%></td>
			</tr>
		<%
			}
	}
		%>
		</tbody>
	</table>
	<!--  하단정보표시 -->
	<jsp:include page="/inc/bottomMenu.jsp"></jsp:include>
</div>
</body>
</html>