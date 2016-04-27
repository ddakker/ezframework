<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/layout/inc/tags.jspf"%>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td height="20px" colspan="2"> </td>
	</tr>
	<tr>
		<td align="left" colspan="2" class="subtitle"> 인증 권한관리</td>
	</tr>
	<tr>
		<td height="10px"></td>
	</tr>

	<!-- 검색/조건 영역 start -->
	<form:form id="searchFrm" modelAttribute="auth" action="/acl/auth" method="GET">
	<tr>
		<td colspan="2">
			<table cellpadding="5" cellspacing="0" border="1" width="100%" style="border-collapse:collapse;" bordercolor="#DDDDDD">
				<tr>
					<td bgcolor="#F5F5F5" align="center"><strong>검색조건</strong></td>
					<td>
						<form:select path="searchKey" cssClass="search">
							<form:option value="AUTH_NM">명칭</form:option>
							<form:option value="AUTH_DC">설명</form:option>
						</form:select>
						<form:input cssClass="search" path="searchKwd" placeholder="검색어를 입력하세요" />
						<li id="btSearch" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-zoomin"></span><span class="text">검색</span></li>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="30px"></td>
	</tr>
	</form:form>
	<!-- 검색/조건 영역 end -->
	<!-- 버튼 영역 start -->
	<tr>
		<td align="center">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td align="left" class="tabledesc"> · 더블클릭하면 수정 가능합니다.</td>
					<td align="right">
						<span class="button"><a id="btAddFormOpen" href="#">추가</a></span>
						<span class="button"><a id="btModify" href="#">저장</a></span>
						<span class="button"><a id="btRemove" href="#">삭제</a></span>
						<span class="button"><a onclick="location.href='/acl/auth/hierarchy'">계층정보</a></span>
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
	<form:form id="modifyFrm" modelAttribute="auth" action="/acl/auth" method="PUT">
	<tr>
		<td>
			<table id="tbList" cellpadding="5" cellspacing="0" border="1" width="100%" style="border-collapse:collapse;" bordercolor="#DDDDDD">
				<thead>
				<tr>
					<th>선택</th>
					<th>권한코드</th>
					<th>권한명</th>
					<th>권한설명</th>
					<th>등록일</th>
					<th>수정일</th>
					<th>리소스</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="list" items="${list}">
				<tr class="editMode">
					<td align="center">
						<form:checkbox path="authCd" value="${list.authCd}" />
						<form:hidden path="authCds" value="${list.authCd}" />
					</td>
					<td>
						${list.authCd}
					</td>
					<td><form:input path="authNms" controlType="edit" value="${list.authNm}" /></td>
					<td><form:input path="authDcs" controlType="edit" value="${list.authDc}" /></td>
					<td align="center">${list.regDt}</td>
					<td align="center">${list.modiDt}</td>
					<td align="center">
						<span class="button small"><a onclick="location.href='/acl/auth/${list.authCd}/resource'">관리</a></span>
					</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			<script>
				jQuery(document).ready(function(){
					editModeControl();
				});
				function editModeControl() {
					jQuery("input[controlType='edit']").off("dblclick").off("blur")
					jQuery("input[controlType='edit']").prop("readonly", true)
													   .css("width", "100%")
													   .dblclick(function(){
														  jQuery(this).prop("readonly", false)
														  			  .css("border-width", "1px")
														  			  .select();
														  if (!jQuery(this).attr("oriVal")) {
															  jQuery(this).attr("oriVal", jQuery(this).val());
														  }
													   }).blur(function(){
														   jQuery(this).prop("readonly", true)
														   			   .css("border-width", "1px");
														   if (jQuery(this).val() != jQuery(this).attr("oriVal")) {
															   jQuery(this).css("background-color", "#F2C160")
															   			   .parents("tr .editMode").attr("edit", "true");
														   } else {
															   jQuery(this).css("background-color", "")
															   			   .parents("tr .editMode").attr("edit", "true");
														   }
													   });
				}
			</script>

		</td>
	</tr>
	</form:form>
	<!-- 컨텐츠 영역 end -->

	<tr>
		<td height="50px"></td>
	</tr>
</table>
<form:form id="removeFrm" modelAttribute="auth" action="/acl/auth" method="DELETE">
</form:form>
<script>
	j$("#btSearch").click(function(){
		j$("#searchFrm").submit();
	});
	j$("#btModify").click(function(){
		var isValridate = true;
		j$("input[controlType='edit']").each(function(){
			if (j$(this).val() == "") {
				j$.alert("값을 입력하십시요.");
				j$(this).focus();
				isValridate = false;
				return false;
			}
		});

		if (!isValridate) return;

		j$("tr .editMode").each(function(){
			if (j$(this).attr("edit") != "true") {
				j$(this).remove();
			}
		});
		j$("#modifyFrm").submit();
	});
	j$("#btRemove").click(function(){
		var authCds = "";
		j$("input:checkbox:checked").each(function(){
			authCds += "," + j$(this).val();
		});
		if (authCds == "") {
			j$.alert("삭제할 대상을 선택하세요.");
			return;
		}

		j$.alert("삭제 하시겠습니까?", function(){
			j$("#removeFrm").attr("action", j$("#searchFrm").attr("action") + "/" + authCds.substring(1))
							.submit();
		}, function(){});
	});

	j$("#btAddFormOpen").click(function(){
		j$.divPop("divAddFormPop", "권한등록", j$("#divAddFormTemplete"));

		j$("#btAdd").click(function(){
			var isSubmit = true;
			j$("#divAddFormPop input:text").each(function(){
				$this = j$(this);
				if ($this.val() == "") {
					j$.alert($this.attr("placeholder"), function(){
						$this.focus();
					});
					isSubmit = false;
					return false;
				}

				if ($this.attr("name") == "authCd" && $this.val().indexOf("ROLE_") == -1) {
					j$.alert("권한코드는 'ROLE_'로 시작해야 합니다.", function(){
						$this.focus();
					});
					isSubmit = false;
					return false;
				}
			});
			if (!isSubmit) return;

			var params = {};
			params._method = "POST";
			params.authCd = j$("#divAddFormPop input[name='authCd']").val().trim();
			params.authNm = j$("#divAddFormPop input[name='authNm']").val();
			params.authDc = j$("#divAddFormPop input[name='authDc']").val();

			jQuery.ajax({
				url: "/acl/auth",
				data: params,
				type: "POST",
				dataType: "json",
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
</script>

<div id="divAddFormTemplete" style="display: none">
	<table cellpadding="0" cellspacing="0" border="0" width="500px">
		<tr>
			<td align="center">
				<table cellpadding="5" cellspacing="0" border="1" width="460px" style="border-collapse:collapse;" bordercolor="#DDDDDD">
					<tr>
						<td align="center" bgcolor="#F5F5F5"><strong>권한코드</strong></td>
						<td colspan="3"><input name="authCd" type="text" style="width:99%; ime-mode:disabled; text-transform:uppercase;" value="" placeholder="권한코드를 입력하십시요." /></td>
					</tr>
					<tr>
						<td align="center" bgcolor="#F5F5F5"><strong>권한명</strong></td>
						<td colspan="3"><input name="authNm" type="text" style="width:99%;" value="" placeholder="권한명을 입력하십시요." /></td>
					</tr>
					<tr>
						<td align="center" bgcolor="#F5F5F5"><strong>권한설명</strong></td>
						<td colspan="3"><input name="authDc" type="text" style="width:99%;" value="" placeholder="권한설명을 입력하십시요." /></td>
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