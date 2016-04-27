<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
	<head>
		<title></title>
		<script>
			//alert(j$.cookie("idKey"))
		</script>
	</head>
	<body>
        <form action="<spring:eval expression="@global['page.login.url']" />" method="post" onsubmit="return onSubmitHandler();">
        <fieldset><legend>조건정보 영역 custom - username 이외 파라미터 넘겨 처리 하기</legend>

            <div class="user_login_ultop">
                <ul>
                    <li>
                        <label for="id">아이디</label>
                        <input type="text" title="아이디를 입력하세요." id="id" name="userId" maxlength="10"/>
                    </li>
                    <li>
                        <label for="password">비밀번호</label>
                        <input type="text" title="비밀번호를 입력하세요." id="pwd" name="userPwd"/>
                    </li>
                </ul>
                <input type="submit" value="로그인"  />
                <button onclick="j$('form :radio[name=userType][value=1001]').attr('checked', true);j$('#id').val('user');j$('#pwd').val('user');">사용자 로그인</button>
                <button onclick="j$('form :radio[name=userType][value=2001]').attr('checked', true);j$('#id').val('admin');j$('#pwd').val('admin');">시스템 관리자 로그인</button>
                <button onclick="j$('form :radio[name=userType][value=3001]').attr('checked', true);j$('#id').val('cpadmin');j$('#pwd').val('cpadmin');">제휴사 관리자 로그인</button>
                <input type="radio" name="userType" id="userType1001" value="1001" checked /><label for="userType1001">사용자</label>
                <input type="radio" name="userType" id="userType2001" value="2001" /><label for="userType2001">시스템 관리자</label>
                <input type="radio" name="userType" id="userType3001" value="3001" /><label for="userType3001">제휴사 관리자</label>
            </div>
        </fieldset>
        </form>
        <script>
        function onSubmitHandler() {
        	j$("#id").val(j$("#id").val());
        	return true;
        }
        </script>
	</body>
</html>


