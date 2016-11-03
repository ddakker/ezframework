<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<ul>
			<h3>참고사항</h3>
			<li>
				JAVA Source에 무의미한 try~cache문의 필요 없다.
				<br/>
				필요 하다면 throw new RuntimeException("메시지"); 날려라
			</li>
			<li>JAVA Method에 throws 전가 처리 하지 않는다.</li>
			<li>
				RestFul방식을 사용하기 싶다면??
				Spring Tag form을 사용 하던가, 아니면 일반 form에서 _method="PUT", "DELETE" 넘김
			</li>
			<li>
				정적 자원들은 무조건 custom tag를 사용하라
			</li>
		</ul>
	</body>
</html>

