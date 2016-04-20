package org.ezdevgroup;

import javax.annotation.Resource;

import org.infinispan.spring.provider.SpringRemoteCacheManager;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = {
		"classpath*:config/spring/context-common.xml",
		"classpath*:config/spring/context-cache.xml",
		"classpath*:config/spring/test-context-datasource.xml"
})
//@ContextConfiguration(locations = { "classpath*:config/spring/context-*.xml" })
public class JdgTestCase {
    //@Resource Cache<String> cache;
	@Resource SpringRemoteCacheManager jdgCacheManager;

	@Test
	public void test1DePut() throws Exception {
		System.out.println("##### test1De S");
    	System.out.println("jdgCacheManager: " + jdgCacheManager);
    	if (jdgCacheManager != null) {
    		Cache cache = jdgCacheManager.getCache("USER_CACHE");
    		System.out.println("cache: " + cache);

    		for (int i = 0; i < 1000; i++) {
				cache.put("a"+i, "a"+i);
			}
			//RemoteCache<String, Object> rc = jdgCacheManager.getNativeCacheManager().getCache("USER_CACHE");
    		//System.out.println(rc.keySet());

    		System.out.println("put get cache.a0: " + cache.get("a0").get());
    		//cache.clear();
    		//System.out.println("clear after cache.a: " + cache.get("a"));
    	}
    	System.out.println("##### test1De E\n\n");

    }

	@Test
	public void test1DeGet() throws Exception {
		System.out.println("##### test1De S");
    	System.out.println("jdgCacheManager: " + jdgCacheManager);
    	if (jdgCacheManager != null) {
    		Cache cache = jdgCacheManager.getCache("USER_CACHE");
    		System.out.println("cache: " + cache);



    		for (int i = 0; i < 1000; i++) {
    			ValueWrapper val = cache.get("a"+i);
    			//System.out.println("val: " + val);
    			if (val == null) {
    				System.err.println("i: " + i + ", val: null");
    			} else {
	    			if (!("a"+i).equals(val.get())) {
	    				System.err.println("i: " + i + ", val: " + val.get());
	    			}
    			}
    			System.out.print(".");

			}
    		System.out.println("");
    		System.out.println("get cache.a0: " + cache.get("a0").get());
    		//cache.clear();
    		//System.out.println("clear after cache.a: " + cache.get("a"));
    	}
    	System.out.println("##### test1De E\n\n");

    }

	@Resource org.ezdevgroup.ezframework.web.cache.Cache<String> rCacheEvent;
    @Test
    public void test2() throws Exception {
    	System.out.println("##### test2 S");

    	System.out.println("rCacheEvent: " + rCacheEvent);
    	System.out.println("jdgCacheManager: " + jdgCacheManager);
    	if (jdgCacheManager != null) {
    		//SpringRemoteCacheManager springRemoteCacheManager = jdgCacheManager.getObject();
    		Cache cache = jdgCacheManager.getCache("USER_CACHE");
    		System.out.println("cache: " + cache);
    		cache.put("a", "a value");

    		System.out.println("cache.a: " + cache.get("a").get());
    		//cache.clear();
    		//System.out.println("clear after cache.a: " + cache.get("a"));
    	}
    	System.out.println("##### test2 E\n\n");

    }

	/*@Resource AuthService2 authService2;
    @Test
    public void test3Annotation() {
    	System.out.println("##### test3Annotation S");
    	List<Auth> authList = authService2.getList();
    	System.out.println("authList: " + authList);

    	authList = authService2.getList();
    	System.out.println("authList: " + authList);

    	Auth auth = new Auth();
    	auth.setSearchKey("AUTH_CD");
    	auth.setSearchKwd("ROLE_USER");

    	authList = authService2.getList(auth);
    	System.out.println("authList user: " + authList);

    	authList = authService2.getList(auth);
    	System.out.println("authList user: " + authList);
    	System.out.println("##### test3Annotation E\n\n");
    }*/

}
