<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<table id="tb" cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td valign="top" align="center">
		<!-- 컨텐츠 시작 영역 start -->
			<table cellpadding="0" cellspacing="0" border="0" width="95%">
				<tr>
					<td height="20px" colspan="2"> </td>
				</tr>
				<tr>
					<td align="left" colspan="2" class="subtitle"> 권한에 대한 리소스</td>
				</tr>
				<tr>
					<td height="10px"></td>
				</tr>

				<!-- 검색/조건 영역 start -->
				<tr>
					<td colspan="2">
						<table cellpadding="5" cellspacing="0" border="1" width="100%" style="border-collapse:collapse;" bordercolor="#DDDDDD">
							<tr>
								<td width="200px" bgcolor="#F5F5F5" align="center"><strong>권한명</strong></td>
								<td width="*">
									<table cellpadding="0" cellspacing="0" border="0">
										<tr>${view.authNm}(${view.authCd})</tr>
									</table>
								</td>
						</table>
					</td>
				</tr>
				<tr>
					<td height="30px"></td>
				</tr>
				<!-- 검색/조건 영역 end -->
				<!-- 버튼 영역 start -->
				<tr>
					<td align="center">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td align="left" class="tabledesc"> · 체크 후 저장 합니다.</td>
								<td align="right">
									<table cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td id="btn01">
												<span class="button"><a id="btModify" href="#">저장</a></span>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="5"></td>
							</tr>
						</table>
					</td>
				</tr>
				<!-- 버튼 영역 end -->

				<!-- 컨텐츠 영역 start -->
				<form:form id="frm" modelAttribute="resourceDto" action="/acl/auth/${view.authCd}/resource" method="PUT">
				<hidden path="currentPage" value="${paging.currentPage}" />
				<tr>
					<td>
						<table id="tbList" cellpadding="5" cellspacing="0" border="1" width="100%" style="border-collapse:collapse;" bordercolor="#DDDDDD">
							<thead>
							<tr>
								<th>선택</th>
								<th>리소스코드</th>
								<th>리소스명</th>
								<th>리소스URL</th>
								<th>리소스설명</th>
								<th>사용유무</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach var="list" items="${paging.list}" varStatus="status">
							<tr class="editMode">
								<td align="center">
									<input type="checkbox" name="checkedIdx" value="${status.index}" />
									<form:hidden path="resourceCds" value="${list.resourceCd}" />
								</td>
								<td>
									${list.resourceCd}
								</td>
								<td>${list.resourceNm}</td>
								<td>${list.resourceUrl}</td>
								<td>${list.resourceDc}</td>
								<td align="center">
									<select name="authResourceYns" items="${l}" itemLabel="name" itemValue="code">
										<option value="Y" <c:if test="${list.authResourceYn == 'Y'}">selected</c:if>>사용</option>
										<option value="N" <c:if test="${list.authResourceYn == 'N'}">selected</c:if>>사용않함</option>

									</select>
								</td>
							</tr>
							</c:forEach>
							</tbody>
						</table>

					</td>
				</tr>
				</form:form>
				<!-- 컨텐츠 영역 end -->

				<!-- 페이지 영역 start -->
				<tr>
					<td height="10px"></td>
				</tr>
				<tr>
					<td align="center">
						<table cellpadding="0" cellspacing="0" border="0" height="22px">
							<tr>
								<ui:paging value="${paging}" />
							</tr>
						</table>
					</td>
				</tr>
				<!-- 페이지 영역 end -->
				<tr>
					<td height="50px"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<form:form id="removeFrm" modelAttribute="resource" action="/acl/resource" method="DELETE">
</form:form>
<script>
	j$("#btModify").click(function(){
		if (j$("input[name='checkedIdx']:checked").length == 0) {
			j$.alert("선택된 정보가 없습니다.");
			return;
		}
		j$("#frm").submit();
	});
</script>

