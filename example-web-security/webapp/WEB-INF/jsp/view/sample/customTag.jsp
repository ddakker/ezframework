<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<h1>CustomTag/EL Lib</h1>

		<li><a href="http://verify.ezdevgroup.org/wiki/doku.php?id=커스텀_태크" target="_blank">wiki 메뉴얼</a></li>
		<ul>
			<li>
				<div>리소스 위치가 물리적으로 변동될수 있는 상황을 고려하여 리소스 참조는 항상 \${ url:resource } El을 사용하도록한다.</div>
				<div>- 프로젝트 소스내의 리소스가 이미지 서버로 변경되거나, 이미지 서버 주소가 변동되는 상황 고려</div>
				<ul>
					<li>
						프로젝트에 포함된 정적 리소스 사용
						<pre class="brush: html">
							&lt;script src="\${url:resource('/resources/js/jquery-1.11.1.min.js')}">&lt;/script>
						</pre>
					</li>
					<li>
						<div>이미지 서버 경로 사용</div>
						<div>- HTTP, HTTPS 상황에 따라 자동으로 처리된다.</div>
						<div><img src="${url:img('/welfare/eznew/service/images/logo/2013/logo_ez1.gif')}" /></div>
						<div><img src="${url:img2('/welfare/eznew/service/images/logo/2013/logo_ez1.gif', pageContext.request)}" /></div>

						<pre class="brush: html">
							&lt;img src="\${url:img('/welfare/eznew/service/images/logo/2013/logo_ez1.gif')}" />

						</pre>
					</li>
				</ul>
			</li>
		</ul>
	</body>
</html>

