<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>

		<title></title>

	</head>
	<body>
		<h1>MediaType에 따른 결과</h1>
		<ul>
			<a href="http://verify.ezdevgroup.org/wiki/doku.php?id=contenttype에_따른_뷰처리" target="_blank">wiki 메뉴얼</a>
			<li>동일한 요청 URL에 HTML, JSON, JSONP, XML 응답 받을 수 있다.</li>
			<li>각 요청은 결과코드가 리턴되고(result), 0000 이라면 성공이다.</li>
			<li>결과코드가 0000이 아니라면, message 데이터가 전달되며, Exception().getMessage() 값이 전달된다.</li>
			<li>result, message 값은 최상위(xml기준) 노드 바로 하위에 존재하며, 해당 key/value값은 /properties/applicationProperties.xml 에서 정의 할수 있다.</li>
		</ul>
		<script>
			jQuery(document).ready(function(){
				jQuery("#btHtml").click(function(){
					jQuery.ajax({
						url: '/sample/resultCase',
						dataType: 'html',
						error: function(jqXHR, textStatus, errorThrown){
							alert("error: " + jqXHR + "\n" + textStatus + "\n" + errorThrown);
						},
						success: function(data, textStatus){
							jQuery("#divResult").html("textStatus=" + textStatus);
							jQuery("#txtaData").val(data);
						}
					});
				});

				jQuery("#btJson").click(function(){
					jQuery.ajax({
						url: '/sample/resultCase',
						dataType: 'json',
						error: function(jqXHR, textStatus, errorThrown){
							alert("error: " + jqXHR + "\n" + textStatus + "\n" + errorThrown);
						},
						success: function(data, textStatus){
							jQuery("#divResult").html("data.result=" + data.result);
							jQuery("#divSelector").html("a=" + data.a + ", sampleList[0].name=" + data.sampleList[0].name)
							jQuery("#txtaData").val(JSON.stringify(data));

							var iter = data.sampleList;
							for (var i = 0; i < iter.length; i++) {
								alert(iter[i].name);
							}

						}
					});
				});

				jQuery("#btJsonEx").click(function(){
					jQuery.ajax({
						url: '/sample/resultCase?isEx=true',
						dataType: 'json',
						error: function(jqXHR, textStatus, errorThrown){
							alert("error: " + jqXHR + "\n" + textStatus + "\n" + errorThrown);
						},
						success: function(data, textStatus){
							jQuery("#divResult").html("data.result=" + data.result + ", data.message=" + data.message);
							jQuery("#txtaData").val(JSON.stringify(data));
						}
					});
				});

				jQuery("#btJsonp").click(function(){
					jQuery.ajax({
						url: '/sample/resultCase',
						dataType: 'jsonp',
						error: function(jqXHR, textStatus, errorThrown){
							alert("error: " + jqXHR + "\n" + textStatus + "\n" + errorThrown);
						},
						success: function(data, textStatus){
							jQuery("#divResult").html("data.result=" + data.result);
							jQuery("#divSelector").html("a=" + data.a + ", sampleList[0].name=" + data.sampleList[0].name)
							jQuery("#txtaData").val("callbackFun(" + JSON.stringify(data) + ")");

							var iter = data.sampleList;
							for (var i = 0; i < iter.length; i++) {
								alert(iter[i].name);
							}
						}
					});
				});

				jQuery("#btXml").click(function(){
					jQuery.ajax({
						url: '/sample/resultCase',
						dataType: 'xml'
					}).done(function(data){
						var iter = data.getElementsByTagName("sampleList")[0].childNodes;
						for (var i = 0; i < iter.length; i++) {
							alert(iter[i].getElementsByTagName("name")[0].childNodes[0].nodeValue);
						}

					});
				});

				jQuery("#btAuth").click(function(){
					jQuery.ajax({
						url: '/sample/onlyAdmin',
						dataType: 'json',
						error: function(jqXHR, textStatus, errorThrown){
							alert("error: " + jqXHR + "\n" + textStatus + "\n" + errorThrown + "\n" + jqXHR.status);
						},
						success: function(data, textStatus){
							jQuery("#divResult").html("data.result=" + data.result + ", data.message=" + data.message);
							jQuery("#txtaData").val(JSON.stringify(data));
						}
					});
				});

			});



			function test1(a){
				setTime(function(){
					alert()
				}, 1000);
			}



		</script>
		<div>
			<button id="btHtml">HTML Call</button>
			<button id="btJson">JSON Call</button>
			<button id="btJsonEx">JSON Exception Call</button>
			<button id="btJsonp">JSONP Call</button>
			<button id="btXml">XML Call</button>
			<button id="btAuth">권한체크(user일때 권한 없음) JSON Call</button>
		</div>
		<div>
			<div id="divResult"></div>
			<div id="divSelector"></div>
			<textarea id="txtaData" rows="40" cols="100"></textarea>
		</div>
	</body>
</html>

