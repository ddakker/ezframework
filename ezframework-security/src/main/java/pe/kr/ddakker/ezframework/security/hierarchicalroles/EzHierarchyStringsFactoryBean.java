package pe.kr.ddakker.ezframework.security.hierarchicalroles;

import org.springframework.beans.factory.FactoryBean;

import pe.kr.ddakker.ezframework.security.service.EzAuthService;

/**
 * 권한에 대한 부모자식 관계 설정에 대한 처리
 * @author ddakker 2014. 8. 28.
 *
 */
public class EzHierarchyStringsFactoryBean implements FactoryBean  {

	private EzAuthService ezAuthService;

	private String hierarchyStrings;

	public void setEzAuthService(EzAuthService ezAuthService) {
		this.ezAuthService = ezAuthService;
	}

	public void init() throws Exception {
		this.hierarchyStrings = ezAuthService.getAuthHierarchyQuery();
	}

	@Override
	public Object getObject() throws Exception {
		if (this.hierarchyStrings == null) {
			init();
		}
		return this.hierarchyStrings;
	}

	@Override
	public Class getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
