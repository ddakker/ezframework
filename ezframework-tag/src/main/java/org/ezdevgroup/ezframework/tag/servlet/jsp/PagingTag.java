package org.ezdevgroup.ezframework.tag.servlet.jsp;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.ArrayUtils;
import org.ezdevgroup.ezframework.tag.servlet.jsp.common.TagUtil;
import org.ezdevgroup.ezframework.web.vo.Paging;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 페이징 넘버링 HTML 생성
 * @auther ddakker 2014. 5. 30.
 *
 */
@SuppressWarnings("serial")
public class PagingTag extends TagSupport {
	private final String MODE_TEXT 	= "text";
	private final String MODE_IMAGE = "image";
	
	private String mode		= "text";		// text | image
	
	private String btnFirst = TagUtil.getImageSSL() + "/welfare_pms/images/common/btn_first.jpg";
	private String btnPrev 	= TagUtil.getImageSSL() + "/welfare_pms/images/common/btn_prev.jpg";
	private String btnNext 	= TagUtil.getImageSSL() + "/welfare_pms/images/common/btn_next.jpg";
	private String btnLast 	= TagUtil.getImageSSL() + "/welfare_pms/images/common/btn_last.jpg";
	
	private String btnFirstNm = "처음";
	private String btnPrevNm  = "이전";
	private String btnNextNm  = "다음";
	private String btnLastNm  = "마지막";
	private Paging<Object> value;

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void setBtnFirst(String btnFirst) {
		this.btnFirst = btnFirst;
	}

	public void setBtnPrev(String btnPrev) {
		this.btnPrev = btnPrev;
	}

	public void setBtnNext(String btnNext) {
		this.btnNext = btnNext;
	}

	public void setBtnLast(String btnLast) {
		this.btnLast = btnLast;
	}

	public void setValue(Paging<Object> value) {
		this.value = value;
	}

	public void setBtnFirstNm(String btnFirstNm) {
		this.btnFirstNm = btnFirstNm;
	}

	public void setBtnPrevNm(String btnPrevNm) {
		this.btnPrevNm = btnPrevNm;
	}

	public void setBtnNextNm(String btnNextNm) {
		this.btnNextNm = btnNextNm;
	}

	public void setBtnLastNm(String btnLastNm) {
		this.btnLastNm = btnLastNm;
	}

	@Override
	public int doStartTag() throws JspException {
		String [] exclude = {"currentPage"};
		try {
			int currentPage 	= value.getCurrentPage();
			int pageCount		= value.getPageCount();
			int pageBlock		= value.getPageBlock();

			String outStr = "<div id='divPaging' class='pagging'>";

			int intTemp = -1;
			int intLoop = -1;

			String firstPg 	= btnFirstNm;
			String prePg 	= btnPrevNm;
			if (MODE_IMAGE.equals(mode)) {
				firstPg = "<img src='" + btnFirst + "' border='0' alt='1페이지로' align='absmiddle'>";
				prePg	= "<img src='" + btnPrev + "' border='0' alt='앞페이지로' align='absmiddle'>";
			}
			if (currentPage != 1) {
				outStr += "<a class='ctl first link' href='?currentPage=1" + queryString(exclude) + "'" + ">" + firstPg + "</a> \n";
				outStr += "<a class='ctl prev link' href='?currentPage=" + (currentPage-1) + queryString(exclude) + "'>" + prePg + "</a> \n";
			} else {
				outStr += "<a class='ctl first'>" + firstPg + "</a>\n";
				outStr += "<a class='ctl prev'>" + prePg + "</a> \n";
			}

			intTemp = ((currentPage - 1) / pageBlock) * pageBlock + 1;
			intLoop = 1;

			while( !( (intLoop > pageBlock) || (intTemp > pageCount) ) ){
				if (intTemp == currentPage) {
					outStr += "<a class='num curr" + (intLoop==1?"":" nxt_nb") + "'>" + intTemp + "</span> \n";
				} else {
					String pgNum = "<a class='num link" + (intLoop==1?"":" nxt_nb") + "' href='?currentPage=" + intTemp + queryString(exclude) + "'>" + intTemp + "</a>";
					outStr += "" + pgNum + " \n";
				}
				intTemp = intTemp + 1;
				intLoop = intLoop + 1;
			}

			String nextPg 	= btnNextNm;
			String finalPg 	= btnLastNm;
			if (MODE_IMAGE.equals(mode)) {
				nextPg 	= "<img  src='"  + btnNext + "' border='0' alt='다음페이지로' align='absmiddle'>";
				finalPg = "<img  src='"  + btnLast + "' border='0' alt='마지막페이지로' align='absmiddle'>";
			}
			if (currentPage != pageCount) {
				outStr += "<a class='ctl next link' href='?currentPage=" + (currentPage+1) + queryString(exclude) + "'>" + nextPg + "</a> \n";
				outStr += "<a class='ctl last link' href='?currentPage=" + pageCount + queryString(exclude) + "'>" + finalPg + "</a> \n";
			} else {
				outStr += "<a class='ctl next'>" + nextPg + "</a> \n";
				outStr += "<a class='ctl last'>" + finalPg + "</a> \n";
			}
			outStr += "</div>";

			pageContext.getOut().print(outStr);
		} catch (IOException e) {
			throw new JspTagException("Error:  IOException while writing to the user");
		}
		return 1;
	}

	@SuppressWarnings("rawtypes")
	private String queryString(String [] exclude) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String queryString = "";
		Enumeration eNames = request.getParameterNames();
		if (eNames.hasMoreElements()) {
			while (eNames.hasMoreElements()) {
				String name = (String) eNames.nextElement();
				String[] values = request.getParameterValues(name);
				if (values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						if (ArrayUtils.contains(exclude, name)) continue;
						queryString += "&" + name + "=" + values[i];
					}
				}
			}
		}
		return queryString;
	}
}
