package org.ezdevgroup.common.service.acl.resource;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.ezdevgroup.common.domain.entity.Resource;
import org.ezdevgroup.common.service.acl.resource.ResourceService;
import org.ezdevgroup.common.service.acl.resource.dto.ResourceDto;
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
public class ResourceSvcTest {
	@javax.annotation.Resource ResourceService resourceSvc;
	
	@Test
	@Transactional
	public void crudCase() {
		ResourceDto ResourceDto = new ResourceDto();
		ResourceDto.setResourceCd("test");
		ResourceDto.setResourceNm("테스트 명");
		ResourceDto.setResourceDc("테스트 정보");
		ResourceDto.setResourceUrl("/test/*");
		
		assertThat("등록은 한개지", resourceSvc.add(ResourceDto), is(1));
		
		Paging<Resource> paging = resourceSvc.getList(ResourceDto);
		
		assertThat("널 인감?", paging, is(notNullValue()));
		assertThat("널 인감?", paging.getList(), is(notNullValue()));
		assertThat("0보다는 크가나 같아야지", paging.getList().size(), greaterThanOrEqualTo(1));
		
		Resource insertResource = null;
		for (Resource Resource : paging.getList()) {
			if ("test".equals(Resource.getResourceCd())) {
				insertResource = Resource;
				break;
			}
		}
		
		assertThat("등록 한 데이터 조회 널 인감?", insertResource, is(notNullValue()));
		assertThat("등록 한 데이터가 맞는감?", insertResource.getResourceNm(), is("테스트 명"));
		
		
		ResourceDto = new ResourceDto();
		ResourceDto.setResourceCd("test");
		ResourceDto.setResourceNm("테스트 명 수정");
		ResourceDto.setResourceDc("테스트 정보 수정");
		ResourceDto.setResourceUrl("/test/modify/*");
		resourceSvc.modifys(ResourceDto);
		
		
		paging = resourceSvc.getList(ResourceDto);
		
		assertThat("널 인감?", paging, is(notNullValue()));
		assertThat("널 인감?", paging.getList(), is(notNullValue()));
		assertThat("0보다는 크가나 같아야지", paging.getList().size(), greaterThanOrEqualTo(1));
		
		insertResource = null;
		for (Resource Resource : paging.getList()) {
			if ("test".equals(Resource.getResourceCd())) {
				insertResource = Resource;
				break;
			}
		}
		
		assertThat("수정 한 데이터 조회 널 인감?", insertResource, is(notNullValue()));
		assertThat("수정 한 데이터가 맞는감?", insertResource.getResourceNm(), is("테스트 명 수정"));
		
		assertThat("삭제는 한개지", resourceSvc.remove(new String[]{"test"}), is(1));
	}
	
	
}
