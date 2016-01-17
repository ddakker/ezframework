package pe.kr.ddakker.ezframework.security.service;

import java.util.List;

/**
 * 리소스 관리
 * @author ddakker 2014. 8. 28.
 *
 */
public interface EzResourceService {
	/**
	 * 권한에 맵핑된 리소스 정보 가져오기
	 * @param auth
	 * @return
	 * @auther ddakker 2014. 8. 28.
	 */
	public List<String> getAuthResourceUrlQuery(String auth);
}
