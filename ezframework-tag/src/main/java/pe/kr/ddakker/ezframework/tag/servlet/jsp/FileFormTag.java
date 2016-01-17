package pe.kr.ddakker.ezframework.tag.servlet.jsp;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 페이징 넘버링 HTML 생성
 * @auther ddakker 2014. 5. 30.
 *
 */
@SuppressWarnings("serial")
public class FileFormTag extends TagSupport {
	private String height;
	private String pkKey;
	private String fileNmKey;
	private List<Map<String, Object>> fileItemList;


	public void setHeight(String height) {
		this.height = height;
	}

	public void setPkKey(String pkKey) {
		this.pkKey = pkKey;
	}

	public void setFileNmKey(String fileNmKey) {
		this.fileNmKey = fileNmKey;
	}

	public void setFileItemList(List<Map<String, Object>> fileItemList) {
		this.fileItemList = fileItemList;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			
			String outStr = "";
			outStr += "\n			<script>";
			outStr += "\n				jQuery(document).ready(function(){";
			outStr += "\n					jQuery(\"#btFileAdd\").click(function(){";
			outStr += "\n						showFileUploadForm();";
			outStr += "\n						";
			outStr += "\n						jQuery(\"#btFileUpload\").click(function(){";
			outStr += "\n							if (jQuery(\".file-upload-layer input[name='file']\").val() == \"\") {";
			outStr += "\n								alert(\"파일을 선택 하세요.\");";
			outStr += "\n							} else {";
			outStr += "\n								jQuery(\"#frmFileUpload\").submit();";
			outStr += "\n							}";
			outStr += "\n						});";
			outStr += "\n					});";
			outStr += "\n					jQuery(\"#uploadList\").on(\"click\", \".btFileRemove\", function(){";
			outStr += "\n						var seq = jQuery(this).parent().attr(\"seq\");";
			outStr += "\n						if(seq) {";
			outStr += "\n							jQuery(\".file-upload-layer\").append(\"<input type=\\\"hidden\\\" name=\\\"" + pkKey + "\\\" value=\\\"\" + seq + \"\\\" />\");";
			outStr += "\n						}";
			outStr += "\n						jQuery(this).parent().remove();";
			outStr += "\n					});";
			outStr += "\n				});";
			outStr += "\n				function showFileUploadForm() {";
			outStr += "\n					createFileUploadForm();";
			outStr += "\n					if (jQuery(\".file-upload-form\").css(\"display\") == \"none\") {";
			outStr += "\n						jQuery(\"#btFileAdd\").val(\" 닫 기 \");";
			outStr += "\n						";
			outStr += "\n						var left = jQuery(\".file-upload-layer\").offset().left + (jQuery(\".file-upload-layer\").width()/2) - (jQuery(\".file-upload-form\").width()/2);";
			outStr += "\n						var top = jQuery(\".file-upload-layer\").offset().top - jQuery(\".file-upload-form\").height() - 20;";
			outStr += "\n						jQuery(\".file-upload-form\").css(\"left\", left + \"px\")";
			outStr += "\n													.css(\"top\", top + \"px\")";
			outStr += "\n													.show();";
			outStr += "\n					} else {";
			outStr += "\n						jQuery(\".file-upload-form .file\").val(\"\");";
			outStr += "\n						jQuery(\".file-upload-form #btFileAdd\").val(\" 첨 부 \");";
			outStr += "\n						jQuery(\".file-upload-form\").hide();";
			outStr += "\n					}";
			outStr += "\n				}";
			outStr += "\n				function createFileUploadForm() {";
			outStr += "\n					if (jQuery(\"#divFileUploadForm\").length == 0) {";
			outStr += "\n						var fileUploadFormHtml = \"\";";
			outStr += "\n						fileUploadFormHtml += \"<div class=\\\"file-upload-form\\\" id=\\\"divFileUploadForm\\\" style=\\\"display: none; position: absolute; width: 300px; background-color: white; border: 1px solid #DDDDDD; padding: 10px 10px 10px 10px\\\">\";";
			outStr += "\n						fileUploadFormHtml += \"	<iframe id=\\\"ifFileUpload\\\" name=\\\"ifFileUpload\\\" style=\\\"margin: 0px; display: none\\\" width=\\\"0\\\" height=\\\"0\\\" frameborder=\\\"0\\\"></iframe>\";";
			outStr += "\n						fileUploadFormHtml += \"	<form id=\\\"frmFileUpload\\\" action=\\\"/tmp/upload\\\" method=\\\"post\\\" enctype=\\\"multipart/form-data\\\" target=\\\"ifFileUpload\\\">\";";
			outStr += "\n						fileUploadFormHtml += \"	<div style=\\\"float: left;\\\"></div>\";";
			outStr += "\n						fileUploadFormHtml += \"	<div style=\\\"float: right;;\\\">\";";
			outStr += "\n						fileUploadFormHtml += \"		<input type=\\\"button\\\" id=\\\"btFileUpload\\\" value=\\\" 업로드 \\\" class=\\\"btn btn-default btn-xs\\\">\";";
			outStr += "\n						fileUploadFormHtml += \"	</div>\";";
			outStr += "\n						fileUploadFormHtml += \"	<div style=\\\"overflow:hidden; padding: 5px 10px 5px 0px;\\\">\";";
			outStr += "\n						fileUploadFormHtml += \"		<input class=\\\"file\\\" type=\\\"file\\\" name=\\\"file\\\" style=\\\"width: 100%\\\" />\";";
			outStr += "\n						fileUploadFormHtml += \"	</div>\";";
			outStr += "\n						fileUploadFormHtml += \"	</form>\";";
			outStr += "\n						fileUploadFormHtml += \"</div>\";";
			outStr += "\n						jQuery(\"body\").append(fileUploadFormHtml);";
			outStr += "\n					}";
			outStr += "\n				}";
			outStr += "\n				function tmpFileUploadSetting(data) {";
			outStr += "\n					var result				= data.result;";
			outStr += "\n					var seq					= data.seq;";
			outStr += "\n					var fileOriginalName	= data.fileOriginalName;";
			outStr += "\n					var filePhysicalName	= data.filePhysicalName;";
			outStr += "\n					var msg					= data.msg;";
			outStr += "\n					if (result == \"0000\") {";
			outStr += "\n						var fileLineHtml = \"\";";
			outStr += "\n						fileLineHtml += \"<div fileOriginalName=\\\"\" + fileOriginalName + \"\\\" filePhysicalName=\\\"\" + filePhysicalName + \"\\\" style=\\\"padding-top: 5px\\\">\";";
			outStr += "\n						fileLineHtml += \"	<input type=\\\"hidden\\\" name=\\\"fileOriginalName\\\" value=\\\"\" + fileOriginalName + \"\\\" />\";";
			outStr += "\n						fileLineHtml += \"	<input type=\\\"hidden\\\" name=\\\"filePhysicalName\\\" value=\\\"\" + filePhysicalName + \"\\\" />\";";
			outStr += "\n						fileLineHtml += \"	<div class=\\\"\\\" style=\\\"float: left;\\\">\";";
			outStr += "\n						fileLineHtml += \"	<img src=\\\"http://img.ezwelfare.net/welfare/eznew/service/images/head/2015_head/ico_new.gif\\\">\";";
			outStr += "\n						fileLineHtml += \"	\" + fileOriginalName;";
			outStr += "\n						fileLineHtml += \"	</div>\";";
			outStr += "\n						fileLineHtml += \"	<div class=\\\"btFileRemove\\\" style=\\\"float: right;\\\">x</div>\";";
			outStr += "\n						fileLineHtml += \"<br/></div>\";";
			outStr += "\n						jQuery(\".file-upload-layer #uploadList\").append(fileLineHtml);";
			outStr += "\n						jQuery(\".file-upload-form .file\").val(\"\");";
			outStr += "\n					} else {";
			outStr += "\n						alert(msg);";
			outStr += "\n					}";
			outStr += "\n				}";
			outStr += "\n			</script>";
			outStr += "\n			<div class=\"file-upload-layer\">";
			outStr += "\n				<div style=\"\">";
			outStr += "\n					<div style=\"float: left;\"></div>";
			outStr += "\n					<div style=\"float: right; padding: 5px 0px 0px 5px;\">";
			outStr += "\n						<input type=\"button\" id=\"btFileAdd\" value=\" 첨 부 \" class=\"btn btn-default btn-xs\">";
			outStr += "\n					</div>";
			outStr += "\n					<div id=\"divFileUploadLayer\" style=\"overflow:hidden; padding: 5px 0px 5px 0px;\">";
			outStr += "\n						<div id=\"uploadList\" class=\"uploadList\" style=\"border: 1px solid #DDDDDD; overflow-y:auto; padding: 10px 15px 15px 10px; height: " + height + "px\">";
			if (fileItemList != null) {
				for (Map<String, Object> fileItem : fileItemList) {
					outStr += "\n						<div seq=\"" + fileItem.get(pkKey) + "\" fileOriginalName=\" + fileOriginalName + \" style=\"padding-top: 5px\">";
					outStr += "\n							<div class=\"\" style=\"float: left;\">";
					outStr += "\n							<img src=\"http://img.ezwelfare.net/welfare/eznew/service/images/head/2015_head/ico_new.gif\">";
					outStr += "\n							" + fileItem.get(fileNmKey);
					outStr += "\n							</div>";
					outStr += "\n							<div class=\"btFileRemove\" style=\"float: right;\">x</div><br/>";
					outStr += "\n						</div>";
				}
			}
			outStr += "\n						</div>";
			outStr += "\n					</div>";
			outStr += "\n				</div>";
			outStr += "\n			</div>";
			

			pageContext.getOut().print(outStr);
			
			
		} catch (IOException e) {
			throw new JspTagException("Error:  IOException while writing to the user");
		}
		return 1;
	}

}
