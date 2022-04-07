<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	String category = request.getParameter("category");
	String rating = request.getParameter("rating");
	double price = -1; // price 데이터가 입력되지 않았을때
	if(!request.getParameter("price").equals("")) {
		price = Double.parseDouble(request.getParameter("price"));
	}
	int length = -1; // length 데이터가 입력되지 않았을때
	if(!request.getParameter("length").equals("")) {
		length = Integer.parseInt(request.getParameter("length"));
	}
	String title = request.getParameter("title");
	String actor = request.getParameter("actor");
	
	// 페이지 설정 변수선언
	int currentPage = 1;
	int rowPerPage = 5;
	int beginRow = (currentPage - 1) * rowPerPage;
	
	FilmDao filmDao = new FilmDao();
	List<FilmList> list = filmDao.selectFilmListSearch(beginRow ,rowPerPage ,category, rating, price, length, title, actor);
	System.out.println(list.size()); // 0
	
	
	//페이지 설정
	
	if(request.getParameter("currentPage") != null) { // 이전, 다음 링크를 통해서 들어왔다면
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+ " <-- currentPage"); // 디버깅
	
	// 페이지 바뀌면 끝이 아니고, 가지고 오는 데이터가 변경되어야 한다.
	rowPerPage = 5;
	beginRow = (currentPage-1)*rowPerPage; // 현재페이지가 변경되면 beginRow도 변경된다. -> 가져오는 데이터 변경된다.
	
	// 전체 행의수
	int totalCount = filmDao.FilmListSearchTotalRow( category, rating, price, length, title, actor);
	
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
			<h1>filmSearch List 결과</h1>
		</div>
	<table class="table table-hover" border="2">
		<%
			for(FilmList f : list) {
		%>
				<tr>
					<td><%=f.getFid()%></td>
					<td><%=f.getTitle()%></td>
					<td><%=f.getDescription()%></td>
					<td><%=f.getCategory()%></td>
					<td><%=f.getPrice()%></td>
					<td><%=f.getLength()%></td>
					<td><%=f.getRating()%></td>
					<td><%=f.getActors()%></td>
				</tr>
		<%		
			}
		%>
	</table>
			<div class="btn-group">		
		<%
			if(currentPage > 1) { // 현재페이지가 1이면 이전페이지가 존재해서는 안된다.
		%>
				<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/Search/filmSearchAction.jsp?currentPage=<%=currentPage-1%>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">이전</a>&nbsp;&nbsp;&nbsp;
		<%	
			}
			if(currentPage < lastPage) { // 마지막페이지가 있다면 
		%>
			<a class="btn bg-dark text-white" href="<%=request.getContextPath()%>/Search/filmSearchAction.jsp?currentPage=<%=currentPage+1%>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>">다음</a>&nbsp;&nbsp;&nbsp;
		<%		
			}
		%>
		</div>
		<!--  하단정보표시 -->
		<jsp:include page="/inc/bottomMenu.jsp"></jsp:include>
</div>
</body>
</html>