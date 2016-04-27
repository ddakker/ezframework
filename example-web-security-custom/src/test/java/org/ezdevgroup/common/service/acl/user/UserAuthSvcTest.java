package org.ezdevgroup.common.service.acl.user;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.ezdevgroup.common.domain.entity.UserAuth;
import org.ezdevgroup.common.service.acl.user.UserAuthService;
import org.ezdevgroup.common.service.acl.user.dto.UserAuthDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import org.ezdevgroup.ezframework.web.vo.Paging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:config/spring/context-common.xml",
		"classpath*:config/spring/context-transaction.xml",
		"classpath*:config/spring/test-context-datasource.xml"
})
public class UserAuthSvcTest {
	@Resource UserAuthService userAuthSvc;
	
	@Test
	@Transactional
	public void crudCase() {
		UserAuthDto UserAuthDto = new UserAuthDto();
		Paging<UserAuth> paging = userAuthSvc.getList(UserAuthDto);
		
		assertThat("널 인감?", paging, is(notNullValue()));
		assertThat("널 인감?", paging.getList(), is(notNullValue()));
		assertThat("0보다는 크가나 같아야지", paging.getList().size(), greaterThanOrEqualTo(1));
		
		
		UserAuthDto = new UserAuthDto();
		UserAuthDto.setUserId("test1");
		UserAuthDto.setAuthCd("ROLE_ADMIN");
		
		assertThat("수정는 한개지", userAuthSvc.modify(UserAuthDto), is(1));
		
		UserAuthDto = new UserAuthDto();
		UserAuthDto.setUserIdArr(new String[]{"test1", "test2"});
		UserAuthDto.setAuthCdArr(new String[]{"ROLE_ADMIN", "ROLE_USER"});
		
		assertThat("수정는 두개지", userAuthSvc.modify(UserAuthDto), is(2));
	}
}
