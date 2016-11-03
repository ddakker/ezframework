
/**
 * POI 기반의 엑셀 다운로드 자동화
 * 타이틀 선언, 셀 병합, 정렬, 셀 너비 지정 자동화
 *
 * 현재 타이틀 병합은 2줄까지만 가능
 *
 * html 태그 클래스 지정 [form = .excelForm] [tr = .excel]
 * td태그에 속성 지정 [as = Query As명] [width = 크기지정, 생략가능, 1이 기본크기 (1증가할때마다 2배)]
 * 						[line = 정렬지정, 생략가능, 중앙이 기본, 속성값 (right, left), 가운데 정렬시 생략하면 됨]
 *
 * @author ljc727
 */
function createExcel() {
	var excelData = "";
	var excelType = $(".excel").length;

	var excelWidth = "";
	var excelAlign = "";

	if (excelType == 2) {
		var resultTxt = "";
		var arr = new Array(2);

		j$(".excel").each(function(idx) {
			var detailArr = new Array();

			$(this).find("td").each(function() {
				var info = {};
				info.textVal = $(this).text();
				info.as = $(this).attr("as");
				info.width = $(this).attr("area");
				info.align = $(this).attr("line");
				info.rowSpan = $(this).attr("rowspan");
				detailArr.push(info);

				var attrColspan = $(this).attr("colspan");
				if (typeof attrColspan != "undefined") {
					for (var i = 0; i < attrColspan - 1; i++) {
						var info = {};
						info.textVal = $(this).text();
						detailArr.push(info);
					}
				}
			});
			arr[idx] = detailArr;
		});

		var temp = new Array();
		var cnt = 0;
		for (var i = 0; i < arr[0].length; i++) {
			if (arr[0][i].rowSpan == 2) {
				temp.push(arr[0][i]);
				continue;
			}
			temp.push(arr[1][cnt]);
			cnt++;
		}
		arr[1] = temp;

		var title = "";
		var column = "";
		for (var i = 0; i < arr.length; i++) {
			for (var j = 0; j < arr[i].length; j++) {
				title += arr[i][j].textVal + ",";
				if (i == arr.length - 1) {
					column += arr[i][j].as + ",";

					/**
					 * 셀 넓이 및 정렬 옵션설정
					 */
					var width = arr[i][j].width;
					if ( typeof width == "undefined" || width == "") {
						width = "-1";
					}
					excelWidth += width + ",";

					var align = arr[i][j].align;
					if ( typeof align == "undefined" || align == "") {
						align = "-1";
					}
					excelAlign += align + ",";
				}
			}
			title += " //";
		}
		excelData = title + column;


	} else {
		var length = j$(".excel").find("td").length;
		j$(".excel").find("td").each(function (index) {
			var as = j$(this).attr("as");
			if ( typeof as == "undefined" ) {
				return true; // continue
			}

			excelData += as + "," + j$(this).text();
			if (index != (length - 1)) {
				excelData += "//";
			}

			/**
			 * 셀 넓이 및 정렬 옵션설정
			 */
			var width = j$(this).attr("area");
			if ( typeof width == "undefined" || width == "") {
				width = "-1";
			}
			excelWidth += width + ",";

			var align = j$(this).attr("line");
			if ( typeof align == "undefined" || align == "") {
				align = "-1";
			}
			excelAlign += align + ",";
		});
	}

	j$(".excelForm").prepend("<input name='excelData' type='hidden' value='" + excelData + "' /> ");
	j$(".excelForm").prepend("<input name='excelWidth' type='hidden' value='" + excelWidth + "' /> ");
	j$(".excelForm").prepend("<input name='excelAlign' type='hidden' value='" + excelAlign + "' /> ");
	j$(".excelForm").prepend("<input name='excelFlag' type='hidden' value='true' /> ");

	j$(".excelForm").attr("method", "post");
	j$(".excelForm").submit();

	j$("[name='excelFlag']").remove();
	j$("[name='excelData']").remove();
	j$("[name='excelAlign']").remove();
	j$("[name='excelWidth']").remove();
	j$(".excelForm").attr("method", "post");

	return false;
}