package org.ezdevgroup;

import javax.annotation.Resource;

import org.ezdevgroup.ezframework.web.cache.Cache;
import org.ezdevgroup.sample.domain.entity.User;
import org.ezdevgroup.sample.service.log.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {
		"classpath*:config/spring/context-common.xml",
		"classpath*:config/spring/context-cache.xml",
		"classpath*:config/spring/test-context-datasource.xml"
})
//@ContextConfiguration(classes = ApplicationContext.class,loader = AnnotationConfigContextLoader.class, locations = {"file:webapp/META-INF/context.xml"})
//@ContextConfiguration(locations = { "classpath*:config/spring/context-*.xml" })
public class EhCacheTestCase {
	//@Resource AuthService authService;
	@Resource Cache<String> eCacheEvent;
    @Resource Cache<User> eCacheBanner;
    @Resource LogService logService;


    @Test
    public void test2() {
    	logService.getLogs();
    }
    @Test
    public void test() {
    	User user = new User();
		user.setUserNm("ddakker");

		eCacheEvent.put("a", "zzz");
		String a = eCacheEvent.get("a");
		System.out.println("a: " + a);

		eCacheBanner.put("user", user);
		User resultUser = eCacheBanner.get("user");
		System.out.println("user: " + resultUser);
    }

    /*@Test
    public void testAnnotation() {
    	List<Auth> authList = authService.getListCacheTest();
    	System.out.println("authList: " + authList);

    	authList = authService.getListCacheTest();
    	System.out.println("authList: " + authList);

    	AuthDto auth = new AuthDto();
    	auth.setSearchKey("AUTH_CD");
    	auth.setSearchKwd("ROLE_USER");

    	authList = authService.getListCacheTest(auth);
    	System.out.println("authList user: " + authList);

    	authList = authService.getListCacheTest(auth);
    	System.out.println("authList user: " + authList);
    }*/



}
