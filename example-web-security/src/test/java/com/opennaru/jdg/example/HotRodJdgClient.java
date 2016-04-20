package com.opennaru.jdg.example;

import java.util.Map;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.junit.Test;


public class HotRodJdgClient {

	
	@Test
	public void putTest() {
		RemoteCacheManager cacheManager = new RemoteCacheManager("222.231.44.162:11222;222.231.44.175:11222");
        System.out.println("cacheManager: " + cacheManager);
        RemoteCache<String, Object> cache = cacheManager.getCache("KHAN_SESSION_USER");
        
        for (int i = 0; i < 100; i++) {
			cache.put("a"+i, "a"+i);
			System.out.print(".");
		}
		System.out.println("\nend");
	}
	
	@Test
	public void getTest() {
		RemoteCacheManager cacheManager = new RemoteCacheManager("222.231.44.162:11222;222.231.44.175:11222");
        System.out.println("cacheManager: " + cacheManager);
        RemoteCache<String, Object> cache = cacheManager.getCache("KHAN_SESSION_USER");
        
        for (int i = 0; i < 100; i++) {
        	String val = (String) cache.get("a"+i);
        	if (!("a"+i).equals(val)) {
				System.err.println("i: " + i + ", val: " + val);
			}
			System.out.print(".");
		}
        System.out.println("\nend");
	}
	
	@Test
	public void putTest2() {
		RemoteCacheManager cacheManager = new RemoteCacheManager("222.231.44.162:11222;222.231.44.175:11222");
        System.out.println("cacheManager: " + cacheManager);
        RemoteCache<String, Object> cache = cacheManager.getCache("KHAN_SESSION_SHOP");
        
        for (int i = 0; i < 100; i++) {
			cache.put("a"+i, "a"+i);
			System.out.print(".");
		}
		System.out.println("\nend");
	}
	
	@Test
	public void getTest2() {
		RemoteCacheManager cacheManager = new RemoteCacheManager("222.231.44.162:11222;222.231.44.175:11222");
        System.out.println("cacheManager: " + cacheManager);
        RemoteCache<String, Object> cache = cacheManager.getCache("KHAN_SESSION_SHOP");
        
        for (int i = 0; i < 100; i++) {
        	String val = (String) cache.get("a"+i);
        	if (!("a"+i).equals(val)) {
				System.err.println("i: " + i + ", val: " + val);
			}
			System.out.print(".");
		}
        System.out.println("\nend");
	}
}
