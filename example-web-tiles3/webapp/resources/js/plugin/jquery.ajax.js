var AJAX_PROCESS_COUNT = 0;
jQuery(document).ready(function(){
	// 통신 시작
	jQuery(document).ajaxSend(function(event, xhr, options){
		AJAX_PROCESS_COUNT++;
	});
	// 통신 완료(성공 및 실패)
	jQuery(document).ajaxComplete(function(event, xhr, options){
		AJAX_PROCESS_COUNT--;
	});
	// 통신 성공
	jQuery(document).ajaxSuccess(function(event, xhr, options){
	});
	// 통신 실패
	jQuery(document).ajaxError(function(event, xhr, options, thrownError){
	});
});