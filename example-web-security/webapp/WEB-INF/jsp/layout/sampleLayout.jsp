<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- sample -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
	<head>
		<title><decorator:title default="EZDEVGROUP"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />

		<link href="${url:resource('/resources/css/sample.css')}" rel="stylesheet" type="text/css" />
		<%@ include file="/WEB-INF/jsp/layout/inc/staticBefore.jspf"%>
		<script src="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shCore.js"></script>
		<script src="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shLegacy.js"></script>
		<script src="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shBrushBash.js"></script>
		<script src="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shBrushCss.js"></script>
		<script src="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shBrushGroovy.js"></script>
		<script src="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shBrushJava.js"></script>
		<script src="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shBrushJScript.js"></script>
		<script src="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shBrushSql.js"></script>
		<script src="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shBrushXml.js"></script>
		<link href="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shCore.css" type=text/css rel=stylesheet />
		<link href="http://s1.daumcdn.net/cfs.tistory/custom/blog/22/222668/skin/images/shThemeEclipse.css" type=text/css rel=stylesheet />
		<script type=text/javascript>
			SyntaxHighlighter.all();
		</script>

		<decorator:head />
	</head>
	<body>

		<table width="100%">
			<tr>
				<td width="250px" valign="top">
					<%@ include file="/WEB-INF/jsp/layout/inc/leftSampleMenu.jspf"%>
				</td>
				<td valign="top"><decorator:body /></td>
			</tr>
		</table>



		<%@ include file="/WEB-INF/jsp/layout/inc/staticAfter.jspf"%>
	</body>
</html>