/*
 * jQuery Layer Alert Plugin
 * javascript의 alert, confirm으로는 대응할 수 없는 케이스를 사용하고 자 만듬
 * 약간 실험적인 케이스... 저 이외 사용하지 마셈...
 * 버튼 N개 셋팅 가능
 * @author 	ddakker
 * @date 	2013-02-18
 * ex)
 * jQuery.alert(
 *				'임의 지정 후 callback 테스트',
 *				{label: '테스트 반영', callback: function(){alert(1);}} ,
 *			  	{label: '전체 반영', callback: function(){alert(2);}},
 *				{label: '취소'}
 *			   );
 * jQuery.alert(
 *				'확인 테스트'
 *			   );
 * jQuery.alert(
 *				'확인 클릭 후 callback 테스트',
 *				function(){
 *					alert('확인 클릭 후 호출');
 *				}
 *			   );
 * jQuery.alert(
 *				'예, 아니오 클릭 후 calback 테스트',
 *				function(){
 *					alert('예 클릭 후 호출');
 *				},
 *				function(){
 *					alert('아니오 클릭 후 호출');
 *				}
 *			   );
 */
;(function($) {
	$.alert = function(/* '메시지', {label: , callback:(지정하지 않을경우 창 hide) }(n개),... 가변인자 */) {
		var defaultLabelArr = [["확인"], ["예", "아니오"]];
		var idTime = new Date().getTime();

		var title 		= "";
		var buttonArr 	= new Array();
		jQuery(arguments).each(function(index, el){
			if( index == 0 )	title = el;
			else				buttonArr.push(el);
		});
		buttonArr.length==0?buttonArr.push(null):buttonArr;
		title = title.replace(/</ig, "&lt;");
		title = title.replace(/\n/ig, "<br/>");

		var bgHtml = "<div id=\"divAlertBg\"></div>";
		var html = "";
		html += "<div id=\"divAlert_" + idTime + "\" style=\"position: absolute; display: none; background-color:white; border: 1px solid #B2B2B2; left:0px; top:0px; z-index: 10000001\">";
		html += "	<div style=\"background-color:#F0F0F0; padding:10px; border-bottom: 1px solid #B2B2B2;\">* 메시지</div>";
		html += "	<div style=\"padding-top:20px; padding-left:30px; padding-right:30px;\">" + title + "</div>";
		html += "	<div style=\"padding-top:20px; padding-bottom:15px; padding-left:30px; padding-right:30px; text-align:center\"></div>";
		html += "</div>";

		if( jQuery("#divAlertBg").length == 0 ){
			jQuery("body").append(bgHtml);
			jQuery("#divAlertBg").css("position", "absolute")
								 .css("z-index", "10000000")
								 .css("top", "0px")
								 .css("left", "0px")
								 .css("width", jQuery(window).width() + "px")
								 .css("height", jQuery(window).height() + "px")
								 .css("background", "black")
								 .css("opacity", "0.05")
								 .show();
		}

		jQuery("body").append(html);


		jQuery(buttonArr).each(function(index, el){
			var label;
			var callback;
			if( el == null || (el.label == undefined) ){
				label 		= defaultLabelArr[buttonArr.length-1][index];
				callback 	= el;
			}else{
				label 		= el.label;
				callback 	= el.callback;
			}
			jQuery("#divAlert_" + idTime).children("div").eq(2).append("<input type=\"button\" id=\"btAlert_" + (idTime + "_" + index) + "\" value=\"&nbsp;" + label + "&nbsp;\" />&nbsp;&nbsp;");
			jQuery("#btAlert_" + (idTime + "_" + index)).click(function(){
				jQuery("#divAlert_" + idTime).remove();
				if( jQuery("div[id^='divAlert_']").length == 0 ) jQuery("#divAlertBg").remove();
				callbackCall(callback);
			});

			//alert(jQuery("#divAlert_" + idTime).length);

			/*jQuery("#divAlert_" + idTime).keypress(function(e) {
				jQuery("div[id^='divAlert_']").eq(jQuery("div[id^='divAlert_']").length-1).off("keypress").remove();
				jQuery("div[id^='divAlert_']").eq(jQuery("div[id^='divAlert_']").length-1).focus();
				if( jQuery("div[id^='divAlert_']").length == 0 ) jQuery("#divAlertBg").remove();
				callbackCall(callback);
			});*/
			if(jQuery("div[id^='divAlert_']").length==1){
				jQuery("body").keypress(function(e) {
					if(e.keyCode == 32 || e.keyCode == 13){
						jQuery("div[id^='divAlert_']").eq(jQuery("div[id^='divAlert_']").length-1).off("keypress").remove();
						jQuery("div[id^='divAlert_']").eq(jQuery("div[id^='divAlert_']").length-1).focus();
						if( jQuery("div[id^='divAlert_']").length == 0 ) jQuery("#divAlertBg").remove();
						callbackCall(callback);
					}
				});
			}
		});


		var left 	= jQuery(window).width()/2 - jQuery("#divAlert_" + idTime).width()/2 - 50;
		var height 	= jQuery(window).height()/2 - jQuery("#divAlert_" + idTime).height()/2 + jQuery(document).scrollTop() - 50;
		if( jQuery("div[id^='divAlert_']").length > 1 ){
			left += 10;
			height += 10;
		}
		jQuery("#divAlert_" + idTime).css("left", left + "px")
									 .css("top", height + "px")
									 .show()
									 .focus();

		//jQuery("#divAlertBg").focus();
		function callbackCall(callback){
			if(jQuery("div[id^='divAlert_']").length==0){
				jQuery("body").off("keypress");
			}
			if(callback) callback();
		}
	};
})(jQuery);