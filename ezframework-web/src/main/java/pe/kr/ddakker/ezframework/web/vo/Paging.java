package pe.kr.ddakker.ezframework.web.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import pe.kr.ddakker.ezframework.web.vo.PagingVo;

public class Paging<T> {
	private List<T> content = new ArrayList<T>();

	@Getter @Setter int currentPage	= 1;	// 현재 페이지
	@Getter @Setter int totalCount	= 0;	// 총 데이터 수
	@Getter @Setter int totalActTime	= 0;// 총 작업일 수
	@Getter @Setter int pageSize	= 20;	// 한 페이지에 보일 데이터 갯수
	@Getter @Setter int pageBlock	= 10;	// 한페이지에 보일 페이징 블럭

	public List<T> getList() {
		return Collections.unmodifiableList(content);
	}

	public void setList(List<T> content) {
		this.content = content;
	}

	public void setPaging(PagingVo pagingVo) {
		this.currentPage 	= pagingVo.getCurrentPage();
		this.pageSize		= pagingVo.getPageSize();
	}

	/**
	 * 총 페이지 수
	 * @return
	 * @auther ddakker 2014. 5. 30.
	 */
	public int getPageCount() {

		return (int) Math.ceil(totalCount / (pageSize*1.0));
	}
}