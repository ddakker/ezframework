<%@page import="org.ezdevgroup.sample.domain.EzMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<%

List<EzMap> sampleOneToOneList = (List<EzMap>) request.getAttribute("sampleOneToOne");

%>


<html>
	<head>
		<title></title>
	</head>
	<body>
		<h1>DB 관계 예제</h1>
		
		<p />
		
		<%
		if (sampleOneToOneList != null) {
			for (int i=0,size=sampleOneToOneList.size(); i<2; i++) {
				EzMap sample = sampleOneToOneList.get(0);
				out.print("<div>" + sample.getInteger("seq") + ", " + 
									sample.getString("name") + ", " + 
									sample.getInteger("abCd") + ", " + 
									sample.getNumberDot("abCd") + ", " + 
									sample.getString("phone") + ", " + 
									sample.getPhone("phone") + 
							"</div>");
			}
		}
		%>
		
		
		<table>
			<tr>
				<td valign="top">
					<table width="100%" border="1">
						<h2>OneToOne(1:1)</h2>
						<thead>
						<tr>
							<th>EZ_SAMPLE - SEQ</th>
							<th>EZ_SAMPLE - NAME</th>
							<th>EZ_SAMPLE - EMAIL</th>
							<th>EZ_SAMPLE_DESC - MSG</th>
							<th>EZ_SAMPLE_DESC - SEQ</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="sample" items="${ sampleOneToOne }">
						<tr>
							<td>${sample.seq}</td>
							<td>${sample.name}</td>
							<td>${sample.email}</td>
							<td>${sample.msg}</td>
							<td>${sample.seq}</td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
				</td>
				<td valign="top">
					<table width="100%" border="1">
						<h2>OneToMany(1:N)</h2>
						<thead>
						<tr>
							<th>EZ_SAMPLE - SEQ</th>
							<th>EZ_SAMPLE - NAME</th>
							<th>EZ_SAMPLE - EMAIL</th>
							<th>EZ_SAMPLE_DESC</th>
						</tr>
						<tr><td height="2" colspan="5" bgcolor="#DDDDDD"></td></tr>
						</thead>
						<tbody>
						<tr>
							<td>${sampleOneToMany.seq}</td>
							<td>${sampleOneToMany.name}</td>
							<td>${sampleOneToMany.email}</td>
							<td>
								<table width="100%" border="1">
									<thead>
									<tr>
										<th>EZ_SAMPLE_DESC - MSG</th>
										<th>EZ_SAMPLE_DESC - SEQ</th>
									</tr>
									<tbody>
									<c:forEach var="sampleDesc" items="${ sampleOneToMany.sampleDescList }">
									<tr>
										<td>${sampleDesc.msg}</td>
										<td>${sampleDesc.seq}</td>
									</tr>
									</c:forEach>
									</tbody>
								</table>
							</td>
						</tr>
						<tr><td height="1" colspan="5" bgcolor="#DDDDDD"></td></tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		
		
		
	</body>
</html>

