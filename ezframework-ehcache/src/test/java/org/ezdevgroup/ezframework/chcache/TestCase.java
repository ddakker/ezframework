package org.ezdevgroup.ezframework.chcache;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;

import org.ezdevgroup.ezframework.web.cache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:config/spring/context-cache.xml"
})
public class TestCase {
	@Resource EhCacheCacheManager ehCacheManager;
	@Resource Cache<String> eCacheTest;

	@Test
    public void test() {
		System.out.println("ehCacheManager: " + ehCacheManager);
		System.out.println("eCacheTest: " + eCacheTest);
		System.out.println(ehCacheManager.getCacheManager());
		CacheManager cacheManager = ehCacheManager.getCacheManager();

		System.out.println(ehCacheManager.getCacheNames());


		eCacheTest.put("a", "a value");

		assertThat("put 한개 하고", eCacheTest.size(), is(1));
		assertThat("put 후 get 비교", eCacheTest.get("a"), is("a value"));

		eCacheTest.put("b", "vvvvv");

		assertThat("put 두개 하고", eCacheTest.size(), is(2));

	}
}

