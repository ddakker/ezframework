<%-- <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<h1>권한별 UI제어 예제</h1>

		<p />

		<h2>UI제어</h2>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<button>관리자만 보일거임</button>
		</sec:authorize>

		<sec:authorize access="hasAnyRole('ROLE_MIDDLE_ADMIN')">
			<button>중간 관리자 및 상위 계층 권한자 보일거임</button>
		</sec:authorize>

		<sec:authorize access="hasAnyRole('ROLE_USER')">
			<button>사용자 및 상위 계층 권한자 보일거임</button>
		</sec:authorize>

		<sec:authorize access="hasAnyRole('ROLE_MIDDLE_ADMIN, ROLE_USER')">
			<button>사용자랑 중간관리자 및 상위 계층 권한자 보일거임</button>
		</sec:authorize>


		<pre class="brush: html">
			&lt;sec:authorize access="hasAnyRole('ROLE_ADMIN')">
				&lt;button>관리자만 보일거임</button>
			&lt;/sec:authorize>

			&lt;sec:authorize access="hasAnyRole('ROLE_MIDDLE_ADMIN')">
				&lt;button>중간 관리자 및 상위 계층 권한자 보일거임</button>
			&lt;/sec:authorize>

			&lt;sec:authorize access="hasAnyRole('ROLE_USER')">
				&lt;<button>사용자 및 상위 계층 권한자 보일거임</button>
			&lt;/sec:authorize>

			&lt;sec:authorize access="hasAnyRole('ROLE_MIDDLE_ADMIN, ROLE_USER')">
				&lt;button>사용자랑 중간관리자 및 상위 계층 권한자 보일거임</button>
			&lt;/sec:authorize>
		</pre>

		<h2>Java 사용자 및 권한정보, 권한제어</h2>

		<pre class="brush: java">
			if (UserDetailsHelper.isAuthenticated() ) {
				System.out.println("principal: " + principal.getName());

				User user = UserDetailsHelper.getAuthenticatedUser();
				System.out.println("getUserId: " + user.getUserId());
				System.out.println("getUserNm" + user.getUserNm());

				for (String role : UserDetailsHelper.getAuthorities()) {
					System.out.println("role: " + role);
				}
			}
			System.out.println("IS ROLE_ADMIN: " + request.isUserInRole("ROLE_ADMIN"));
			System.out.println("IS ROLE_MIDDLE_ADMIN: " + request.isUserInRole("ROLE_MIDDLE_ADMIN"));
			System.out.println("IS ROLE_USER: " + request.isUserInRole("ROLE_USER"));
		</pre>



	</body>
</html>

 --%>