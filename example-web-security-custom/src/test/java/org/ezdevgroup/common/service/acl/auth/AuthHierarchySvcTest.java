package org.ezdevgroup.common.service.acl.auth;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.ezdevgroup.common.domain.entity.AuthHierarchy;
import org.ezdevgroup.common.service.acl.auth.AuthHierarchyService;
import org.ezdevgroup.common.service.acl.auth.dto.AuthHierarchyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:config/spring/context-common.xml",
		"classpath*:config/spring/context-transaction.xml",
		"classpath*:config/spring/test-context-datasource.xml"
})
public class AuthHierarchySvcTest {
	@Resource AuthHierarchyService authHierarchySvc;
	
	@Test
	@Transactional
	public void crudCase() {
		AuthHierarchyDto AuthHierarchyDto = new AuthHierarchyDto();
		AuthHierarchyDto.setParentAuthCd("ROLE_ADMIN");
		AuthHierarchyDto.setChildAuthCd("ROLE_USER");
		
		assertThat("등록은 한개지", authHierarchySvc.add(AuthHierarchyDto), is(1));
		
		List<AuthHierarchy> AuthHierarchyList = authHierarchySvc.getList();
		
		assertThat("널 인감?", AuthHierarchyList, is(notNullValue()));
		assertThat("0보다는 크가나 같아야지", AuthHierarchyList.size(), greaterThanOrEqualTo(1));
	}
}
