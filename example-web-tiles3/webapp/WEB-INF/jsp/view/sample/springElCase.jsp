<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<h1>Spring El사용법[[properties, MessageSource, Class접근]</h1>

		<li><a href="http://verify.ezdevgroup.org/wiki/doku.php?id=다국어_처리_messagesource" target="_blank">wiki 메뉴얼1</a></li>
		<li><a href="http://verify.ezdevgroup.org/wiki/doku.php?id=glboal_properties_사용" target="_blank">wiki 메뉴얼2</a></li>
		<li><a href="http://verify.ezdevgroup.org/wiki/doku.php?id=spring_el_의_유용한_활용방법" target="_blank">wiki 메뉴얼3</a></li>

		<ul>
			<h3>MessageSource 접근방법</h3>
			<pre class="brush: html">
				msg = &lt;spring:message code="msg.test1"/>
				msg param = &lt;spring:message code="msg.test2" arguments="첫번째, 두번째" />
			</pre>
			<li>
				msg = <spring:message code="msg.test1"/>
			</li>
			<li>
				msg param = <spring:message code="msg.test2" arguments="첫번째, 두번째" />
			</li>
		</ul>

		<ul>
			<h3>Properties 접근방법</h3>
			<pre class="brush: html">
				server.type = &lt;spring:eval expression="@global['server.type']" />

				&lt;spring:eval var="serverType" expression="@global['server.type']" />
				&lt;c:if test="\${ serverType == 'local' }">
					로컬임
				&lt;/c:if>
			</pre>
			<li>
				server.type = <spring:eval expression="@global['server.type']" />
			</li>
			<li>
				<spring:eval var="serverType" expression="@global['server.type']" />
				<c:if test="${ serverType == 'local' }">
					로컬임
				</c:if>
			</li>
		</ul>

		<ul>
			<c:set value="1000" var="buyPrice" />
			<h3>Java Api 및 Class 접근방법</h3>
			<pre class="brush: html">
				&lt;li>java Date = &lt;spring:eval expression='new java.util.Date()'/>" /></li>
				&lt;li>Java DecimalFormat = &lt;spring:eval expression='new java.text.DecimalFormat("###,##0").format(${buyPrice})'/>
			</pre>
			<li>java Date = <spring:eval expression='new java.util.Date()'/>" /></li>
			<li>Java DecimalFormat = <spring:eval expression='new java.text.DecimalFormat("###,##0").format(${buyPrice})'/>
			<%-- <li>
				<spring:message code="button.list"    />
			</li> --%>
		</ul>


	</body>
</html>

