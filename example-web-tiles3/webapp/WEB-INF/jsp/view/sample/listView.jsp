<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
<head>
	<title></title>
</head>
<body>
	<h1>List View</h1>


	<table width="100%">
		<colgroup>
			<col width="100">
			<col width="*">
			<col width="200">
		</colgroup>
		<thead>
		<tr>
			<th>SEQ</th>
			<th>NAME</th>
			<th>EMAIL</th>
		</tr>
		<tr><td height="2" colspan="4" bgcolor="#DDDDDD"></td></tr>
		</thead>
		<tbody>
		<tr>
			<td align="center">${sampleVo.seq}</td>
			<td>${sampleVo.name}</td>
			<td align="center">${sampleVo.email}</td>
		</tr>
		<tr><td height="1" colspan="4" bgcolor="#DDDDDD"></td></tr>
		</tbody>
	</table>

</body>
</html>

