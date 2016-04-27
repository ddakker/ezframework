<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
	<head>
		<title></title>
		<script>
			location.href = "<spring:eval expression="@global['page.main.url']" />";
		</script>
	</head>
	<body>
	</body>
</html>