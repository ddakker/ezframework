<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
	<head>

		<title></title>

	</head>
	<body>
		<ul>
			<h3>Service Layer(Controller -> Service -> Mapper)</h3>
			<li>
				등록 테스트
				<ul>
					<li>
						<form action="tranTestServiceCommit" method="POST">
							<input type="text" name="name" value="ddakker<spring:eval expression='new java.util.Date().seconds'/>" />
							<input type="text" name="email" value="ddakker@gmail.com" />
							<input type="submit" value="Add" />
						</form>
					</li>
				</ul>
			</li>
			<li>
				트랜잭션 테스트
				<ul>
					<li>등록/수정 되지 않고, Log에서 rollback메시지 확인!! </li>
					<li>
						<form action="tranTestServiceRollback" method="POST">
							<input type="text" name="name" value="ddakker<spring:eval expression='new java.util.Date().seconds'/>" />
							<input type="hidden" name="email" value="ddakker@gmail.com" />
							<input type="submit" value="2개 Add 및 강제 Exception발생" />
						</form>
					</li>
				</div>
				</ul>
			</li>
		</ul>
		
		

		<ul>
			<li>
				<h2>EZ_SAMPLE Table과 EZ_LOG Table의 데이터를 비교 하면서 테스트</h2>
				<ul>
					<li>
						<h3>EZ_LOG Table의 경우 트랜잭션이 별도로 처리한 예제(propagation="NOT_SUPPORTED", 필요시 적절히~)</h3>
					</li>
				</ul>
			</li>
		</ul>
		<table width="100%">
			<tr>
				<td valign="top">
					<table width="100%">
						JAVA MAPPER
						<thead>
						<tr>
							<th>SEQ</th>
							<th>NAME</th>
							<th>EMAIL</th>
						</tr>
						<tr><td height="2" colspan="3" bgcolor="#DDDDDD"></td></tr>
						</thead>
						<tbody>
						<c:forEach var="sample" items="${ samplesJava }">
						<tr>
							<td>${sample.seq}</td>
							<td>${sample.name}</td>
							<td>${sample.email}</td>
						</tr>
						<tr><td height="1" colspan="3" bgcolor="#DDDDDD"></td></tr>
						</c:forEach>
						</tbody>
					</table>
				</td>
				<td valign="top">
					XML MAPPER
					<table width="100%">
						<thead>
						<tr>
							<th>SEQ</th>
							<th>NAME</th>
							<th>EMAIL</th>
						</tr>
						<tr><td height="2" colspan="3" bgcolor="#DDDDDD"></td></tr>
						</thead>
						<tbody>
						<c:forEach var="sample" items="${ samplesXml }">
						<tr>
							<td>${sample.seq}</td>
							<td>${sample.name}</td>
							<td>${sample.email}</td>
						</tr>
						<tr><td height="1" colspan="3" bgcolor="#DDDDDD"></td></tr>
						</c:forEach>
						</tbody>
					</table>
				</td>
				<td valign="top">
					트랜잭션 타지 않는 로그 Insert
					<table width="100%">
						<thead>
						<tr>
							<th>SEQ</th>
							<th>MSG</th>
							<th>REG_DT</th>
						</tr>
						<tr><td height="2" colspan="3" bgcolor="#DDDDDD"></td></tr>
						</thead>
						<tbody>
						<c:forEach var="logVo" items="${ logs }">
						<tr>
							<td>${logVo.seq}</td>
							<td>${logVo.msg}</td>
							<td>${logVo.regDt}</td>
						</tr>
						<tr><td height="1" colspan="3" bgcolor="#DDDDDD"></td></tr>
						</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		
	</body>
</html>

