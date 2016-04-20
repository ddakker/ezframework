package org.ezdevgroup.ezframework.web.vo;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("squid:S1068")
public class PagingVo extends Vo {
	@Setter int pageSize	= 10;
	@Setter int currentPage	= 1;

	public int getFirstIndex() {
		return ((getCurrentPage() - 1) * getPageSize());
	}

	public int getPageSize() {
		return (pageSize < 1) ? 1 : pageSize;
	}

	public int getCurrentPage() {
		return (currentPage < 1) ? 1 : currentPage;
	}


	@Getter @Setter private boolean pageCommonFlag;
	@Getter @Setter private int pageCommonCount;


	/** Excel */
	@Getter @Setter private String excelData;
	@Getter @Setter private String excelFlag;
	@Getter @Setter private String excelWidth;
	@Getter @Setter private String excelAlign;
}
