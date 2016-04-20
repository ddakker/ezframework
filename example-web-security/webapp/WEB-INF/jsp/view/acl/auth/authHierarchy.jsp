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
					<td align="left" colspan="2" class="subtitle"> 권한에 대한 계층정보</td>
				</tr>
				<tr>
					<td height="10px"></td>
				</tr>

				<!-- 버튼 영역 start -->
				<tr>
					<td align="center">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td align="left" class="tabledesc"> · 체크 후 저장 합니다.</td>
								<td align="right">
									<span class="button"><a id="btAddFormOpen" href="#">추가</a></span>
									<span class="button"><a id="btRemove" href="#">삭제</a></span>
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
				<form:form id="frm" modelAttribute="authHierarchy" action="/acl/auth/hierarchy" method="PUT">
				<tr>
					<td>
						<table id="tbList" cellpadding="5" cellspacing="0" border="1" width="100%" style="border-collapse:collapse;" bordercolor="#DDDDDD">
							<thead>
							<tr>
								<th>선택</th>
								<th>상위 > 하위</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach var="list" items="${list}" varStatus="status">
							<tr class="editMode">
								<td align="center">
									<input type="checkbox" name="checkedIdx" value="${status.index}" dataValue="${list.seq}" />
									<form:hidden path="seq" value="${list.seq}" />
								</td>
								<td align="center">
									<select name="authResourceYns">
										<c:forEach var="authList" items="${authList}">
											<option value="${authList.authCd}" <c:if test="${list.parentAuthCd == authList.authCd}">selected</c:if>>${authList.authNm}</option>
										</c:forEach>
									</select>
									>
									<select name="authResourceYns">
										<c:forEach var="authList" items="${authList}">
											<option value="${authList.authCd}" <c:if test="${list.childAuthCd == authList.authCd}">selected</c:if>>${authList.authNm}</option>
										</c:forEach>
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

				<tr>
					<td height="50px"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<form:form id="removeFrm" modelAttribute="authHierarchy" action="/acl/auth/hierarchy" method="DELETE">
</form:form>
<script>
	j$("#btAddFormOpen").click(function(){
		j$.divPop('divAddFormPop', '계층 등록', j$("#divAddFormTemplete"));

		j$("#btAdd").click(function(){
			if (j$("#divAddFormPop select[name='parentAuthCd'] option:selected").val() == "") {
				j$.alert("상위 권한을 선택하세요.", function(){
					j$("#divAddFormPop select[name='parentAuthCd']").focus();
				});
				return;
			}
			if (j$("#divAddFormPop select[name='childAuthCd'] option:selected").val() == "") {
				j$.alert("하위 권한을 선택하세요.", function(){
					j$("#divAddFormPop select[name='childAuthCd']").focus();
				});
				return;
			}

			var params = {};
			params._method = "POST";
			params.parentAuthCd = j$("#divAddFormPop select[name='parentAuthCd'] option:selected").val();
			params.childAuthCd 	= j$("#divAddFormPop select[name='childAuthCd'] option:selected").val();

			jQuery.ajax({
				url: '/acl/auth/hierarchy',
				data: params,
				type: 'POST',
				dataType: 'json',
				success: function(data, textStatus){
					if (data.result == '0000') {
						location.reload();
					} else {
						j$.alert(data.message);
					}
				}
			});
		});
	});

	j$("#btRemove").click(function(){
		var seqs = "";
		j$("input:checkbox:checked").each(function(){
			seqs += "," + j$(this).attr("dataValue");
		});

		if (seqs == "") {
			j$.alert("삭제할 대상을 선택하세요.");
			return;
		}

		j$.alert("삭제 하시겠습니까?", function(){
			j$("#removeFrm").attr("action", j$("#removeFrm").attr("action") + "/" + seqs.substring(1))
							.submit();
		}, function(){});
	});
</script>


<div id="divAddFormTemplete" style="display: none">
	<table cellpadding="0" cellspacing="0" border="0" width="500px">
		<tr>
			<td align="center">
				<table cellpadding="5" cellspacing="0" border="1" width="460px" style="border-collapse:collapse;" bordercolor="#DDDDDD">
					<tr>
						<th>상위 > 하위</th>
					</tr>
					<tr>
						<td align="center">
							<select name="parentAuthCd">
								<option value="">-- 선택하세요 --</option>
								<c:forEach var="authList" items="${authList}">
									<option value="${authList.authCd}">${authList.authNm}</option>
								</c:forEach>
							</select>
							>
							<select name="childAuthCd">
								<option value="">-- 선택하세요 --</option>
								<c:forEach var="authList" items="${authList}">
									<option value="${authList.authCd}">${authList.authNm}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="20px"></td>
		</tr>
		<tr>
			<td align="center">
				<span class="button"><a id="btAdd" href="#">등록</a></span>
				<span class="button"><a class="btClose" href="#">취소</a></span>
			</td>
		</tr>
		<tr>
			<td height="20px"></td>
		</tr>
	</table>
</div>
