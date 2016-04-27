// -----------------------------------------------------------
// Function :
// Date : 2011.07.13 jyhyun
// Desc : 포커스가 select, text readonly 속성의 input에 있을 경우 백스페이스 불가
// -----------------------------------------------------------
document.onkeydown = function () {
    var backspace = 8;
    var t = document.activeElement;

    if (event.keyCode == backspace) {

    	if (t != null) {
	        if (t.tagName == "SELECT")
	            return false;

	        if ((t.tagName == "INPUT" || t.tagName == "TEXTAREA") && window.event.srcElement.readOnly) {
	        	return false;
	        }
    	}

    }
}

function strCut(value, exp, limit) {
	if (value.length <= limit) return value;
	return value.substring(0, limit) + exp;
}

function escapeXml(value) {
	return value.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
}