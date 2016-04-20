package org.ezdevgroup.common.service.acl.auth;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.ezdevgroup.common.domain.entity.Auth;
import org.ezdevgroup.common.service.acl.auth.AuthService;
import org.ezdevgroup.common.service.acl.auth.dto.AuthDto;
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
public class AuthSvcTest {
	@Resource AuthService authSvc;
	
	@Test
	@Transactional
	public void crudCase() {
		AuthDto AuthDto = new AuthDto();
		AuthDto.setAuthCd("ROLE_TEST");
		AuthDto.setAuthNm("테스터");
		AuthDto.setAuthDc("테스터 정보여");
		
		assertThat("등록은 한개지", authSvc.add(AuthDto), is(1));
		
		List<Auth> AuthList = authSvc.getList(AuthDto);
		
		assertThat("널 인감?", AuthList, is(notNullValue()));
		assertThat("0보다는 크가나 같아야지", AuthList.size(), greaterThanOrEqualTo(1));
		
		Auth insertAuth = null;
		for (Auth Auth : AuthList) {
			if ("ROLE_TEST".equals(Auth.getAuthCd())) {
				insertAuth = Auth;
				break;
			}
		}
		
		assertThat("등록 한 데이터 조회 널 인감?", insertAuth, is(notNullValue()));
		assertThat("등록 한 데이터가 맞는감?", insertAuth.getAuthNm(), is("테스터"));
		
		
		AuthDto = new AuthDto();
		AuthDto.setAuthCd("ROLE_TEST");
		AuthDto.setAuthNm("테스터 수정");
		AuthDto.setAuthDc("테스터 정보여 수정");
		authSvc.modifys(AuthDto);
		
		
		AuthList = authSvc.getList(AuthDto);
		
		assertThat("널 인감?", AuthList, is(notNullValue()));
		assertThat("0보다는 크가나 같아야지", AuthList.size(), greaterThanOrEqualTo(1));
		
		insertAuth = null;
		for (Auth Auth : AuthList) {
			if ("ROLE_TEST".equals(Auth.getAuthCd())) {
				insertAuth = Auth;
				break;
			}
		}
		
		assertThat("등록 한 데이터 조회 널 인감?", insertAuth, is(notNullValue()));
		assertThat("등록 한 데이터가 맞는감?", insertAuth.getAuthNm(), is("테스터 수정"));
		
		assertThat("삭제는 한개지", authSvc.remove("ROLE_TEST"), is(1));
	}
}
