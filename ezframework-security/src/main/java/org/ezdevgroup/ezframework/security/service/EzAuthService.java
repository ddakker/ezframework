package org.ezdevgroup.ezframework.security.service;

import java.util.List;

/**
 * 권한 관리
 * @author ddakker 2014. 8. 28.
 *
 */
public interface EzAuthService {
	/**
	 * 전체 권한 불러오기
	 * @return
	 * @auther ddakker 2014. 8. 28.
	 */
	public List<String> getTotalAuthsQuery();

	/**
	 * 권한의 상하 관계 불러오기
	 * @return
	 * @auther ddakker 2014. 8. 28.
	 */
	public String getAuthHierarchyQuery();
}
