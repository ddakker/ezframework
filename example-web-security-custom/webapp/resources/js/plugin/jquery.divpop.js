/**
 * @param 	id 		팝업 레이어 상위 엘리먼트 ID
 * @param	popup	팝업 데이터
 * 						- HTML Url						= Ajax 통신으로 UI HTML을 받아와 띄울때
 * 						- HTML 엘리먼트 Object(jQuery)	= 현재 화면의 레이어를 띄울 때
 *
 */
;(function($) {

	jQuery.divPop = function(id, title, popup, options){
		var defaultOptions = {
				focusId: ''
		}

		options = jQuery.extend({}, defaultOptions, options);

		var popupHtml = '';
		if( popup instanceof Object ){
			popupHtml = popup.html();
			popup.empty();
		}else{
			jQuery.ajax({
				url: popup,
				async: false,
				dataType: "html",
				success: function(data, textStatus){
					popupHtml = data;
				}
			});
		}

		if( jQuery('#' + id).length == 0 ){
			var templeteHtml = "";
			templeteHtml += '<div class="divpopuplayer">';
			templeteHtml += '	<div class="divpopupLayerbg"></div>';
			templeteHtml += '		<div id="' + id + '" class="divPopupContentLayer" style="padding:10px 20px 20px 20px;>';

			templeteHtml += '<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">';
			templeteHtml += '<tr>';
			templeteHtml += '	<td height="70px" align="center" >';
			templeteHtml += '		<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">';
			templeteHtml += '			<tr>';
			templeteHtml += '				<td align="left" class="popuptitle">' + title + '</td>';
			templeteHtml += '				<td align="right"><a class="btClose" href="#"><img src="http://img.ezdevgroup.org/welfare_pms/images/popup/button02.jpg" border="0" /></a></td>';
			templeteHtml += '			</tr>';
			templeteHtml += '		</table>';
			templeteHtml += '	</td>';
			templeteHtml += '</tr>';
			templeteHtml += '<tr>';
			templeteHtml += '	<td align="center">';

			templeteHtml += '	</td>';
			templeteHtml += '</tr>';
			templeteHtml += '<tr>';
			templeteHtml += '	<td height="20px"></td>';
			templeteHtml += '</tr>';
			templeteHtml += '</table>';

			templeteHtml += '	</div>';
			templeteHtml += '</div>';
			jQuery('body').append(templeteHtml);
		}
		var layerEl = jQuery('#' + id);
		layerEl.append(popupHtml);
		var rootLayer = layerEl.parent();

		var positionLeft = jQuery('.divpopuplayer').length * 20;
		var positionTop = jQuery('.divpopuplayer').length * 20;

		rootLayer.fadeIn();

		// 화면의 중앙에 레이어를 띄운다.
		if (layerEl.outerHeight() < $(document).height() ) layerEl.css('margin-top', '-'+(layerEl.outerHeight()/2 - positionLeft)+'px');
		else layerEl.css('top', '0px');
		if (layerEl.outerWidth() < $(document).width() ) layerEl.css('margin-left', '-'+(layerEl.outerWidth()/2 - positionTop)+'px');
		else layerEl.css('left', '0px');

		if( options.focusId == "" ){
			jQuery("#" +id + " input[type='text']").each(function(){
				if( !jQuery(this).attr("readonly") ){
					jQuery(this).focus();
					return false;
				}
			});
		}else{
			jQuery("#" + options.focusId).focus();
		}

		layerEl.find('.btClose').click(function(e){
			if ( typeof popup == 'string' ) {
				jQuery("div[class='divpopuplayer']").eq($("div[class='divpopuplayer']").length-1).remove();
				rootLayer.fadeOut();
				eval($(this).attr("callback"));
			} else if ( typeof popup == 'object') {
				if( jQuery(this).attr("callback") ){
					eval($(this).attr("callback"));
				}
				rootLayer.fadeOut();
				if( popup instanceof Object ){
					popup.html(layerEl.html());
					layerEl.empty();
				}
				e.preventDefault();
				jQuery(this).remove();
			}
		});
		return layerEl;
	};

})(jQuery);
