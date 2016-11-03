<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
	<head>
		<title></title>
		
		<style>
			button, html input[type=button], input[type=reset], input[type=submit] {
				-webkit-appearance: button;
				cursor: pointer;
			}
			
			.btn {
				display: inline-block;
				padding: 6px 12px;
				margin-bottom: 0;
				font-size: 14px;
				font-weight: normal;
				line-height: 1.42857143;
				text-align: center;
				white-space: nowrap;
				vertical-align: middle;
				-ms-touch-action: manipulation;
				touch-action: manipulation;
				cursor: pointer;
				-webkit-user-select: none;
				-moz-user-select: none;
				-ms-user-select: none;
				user-select: none;
				background-image: none;
				border: 1px solid transparent;
				border-radius: 4px;
			}
			
			.btn-default {
				color: #333;
				background-color: #fff;
				border-color: #ccc;
			}
			
			.btn-xs {
				padding: 1px 5px;
				font-size: 12px;
				line-height: 1.5;
				border-radius: 3px;
			}
			</style>
		<!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"> -->
	</head>
	<body>
		<ul>
			<h3>Edit Upload Form</h3>
		</ul>
		<form:form modelAttribute="sampleDto" action="/sample/edit" method="POST">
			name: <form:input path="name" controlType="edit" />
			<br/>
			email: <form:input path="email" controlType="edit" />

			<ui:fileForm fileItemList="${boardFileList}" pkKey="seq" fileNmKey="fileName" />
						
			<input type="submit" value="글수정" />
		</form:form>
	</body>
</html>

