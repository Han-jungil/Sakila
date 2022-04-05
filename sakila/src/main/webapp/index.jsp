<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<!-- 상단메뉴 -->
	<div class= "row">
		<div class="col-sm-10 bg-secondary text-white">
			<tr>[스마트혼합]공공데이터 융합자바/스프링 개발자 양성과정(19회차) &nbsp;&nbsp; Teacher : P.SungHwan &nbsp;&nbsp; Manager : L.SunMin</tr>
		</div>
	</div>
	<!-- 좌측+메인 -->
	<div class= "row">
		<div class= "col-sm-3">
			<div class=" mt-5 p-5 bg-dark text-white rounded">
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<h1>&nbsp;&nbsp;Index</h1>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>
				<div>&nbsp;</div>	
		</div>
		</div>
		<div class="col-sm-7">
			<table class="table table-hover">
				<thead>
					<tr>	
						<th>목록</th>
					</tr>
				</thead>
				<tbody>
					<td>
						<jsp:include page="./inc/indexMenu.jsp"></jsp:include>
					</td>
				</tbody>
			</table>
		</div>
	</div>
	<!--  하단정보표시 -->
			<div class="col-sm-10 bg-secondary">
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