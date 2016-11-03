<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
<head>
	<title></title>
</head>
<body>
	<h1>List Paging 및 Paging TagLib</h1>

	페이지 갯수
	<select onchange="location.href='?pageSize=' + this.value">
		<c:if test="${not empty paging }">
		<option value="5" <c:if test="${ paging.pageSize == 5 }">selected="selected"</c:if>>5개</option>
		<option value="10" <c:if test="${ paging.pageSize == 10 }">selected="selected"</c:if>>10개</option>
		<option value="20" <c:if test="${ paging.pageSize == 20 }">selected="selected"</c:if>>20개</option>
		<option value="30" <c:if test="${ paging.pageSize == 30 }">selected="selected"</c:if>>30개</option>
		</c:if>
	</select>

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
		<c:if test="${not empty paging }">
		<c:forEach var="sampleVo" items="${ paging.list }">
		<tr>
			<td align="center">${sampleVo.seq}</td>
			<td><a href="/sample/list/${sampleVo.seq}">${sampleVo.name}</a></td>
			<td align="center">${sampleVo.email}</td>
		</tr>
		<tr><td height="1" colspan="4" bgcolor="#DDDDDD"></td></tr>
		</c:forEach>
		</c:if>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="4" align="center">
				<c:if test="${not empty paging }">
					<br/>
					<ui:paging value="${paging}"  /><!-- default mode="text", btnFirstNm="처음" btnPrevNm="이전" btnNextNm="다음" btnLastNm="마지막" -->
					
					<br/>
					<ui:paging value="${paging}" 
							   mode="image"
							   btnFirst="${url:img('/welfare_pms/images/common/btn_first.jpg')}"
							   btnPrev="${url:img('/welfare_pms/images/common/btn_prev.jpg')}"
							   btnNext="${url:resource('/resources/img/btn_next.jpg')}"
							   btnLast="${url:img('/welfare_pms/images/common/btn_last.jpg')}" />
					<br/>		   
					<ui:paging value="${paging}" 
							   btnFirstNm="◁" 
							   btnPrevNm="&lt;"
							   btnNextNm="&gt;"
							   btnLastNm="▷"
							   />
				</c:if>
			</td>
		</tr>
		</tfoot>
	</table>

	등록 테스트
	<ul>
		<li>
			<form action="add" method="POST">
				<input type="text" name="name" value="ddakker<spring:eval expression='new java.util.Date().seconds'/>" />
				<input type="text" name="email" value="ddakker@gmail.com" />
				<input type="submit" value="Add" />
			</form>
		</li>
	</ul>


</body>
</html>

