package com.opennaru.jdg.example;

import java.util.Map;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.junit.Test;


public class HotRodClient {


	@Test
	public void sessionTest() {
		RemoteCacheManager cacheManager = new RemoteCacheManager("222.231.44.162:11722;222.231.44.175:11722");
        System.out.println("cacheManager: " + cacheManager);
        RemoteCache<String, Object> cache = cacheManager.getCache("KHAN_SESSION_EZWELMIND_ADMIN");
        System.out.println(cache.getBulk());
        for(Object object : cache.getBulk().keySet()) {
        	String str = (String) object;
        	System.out.println(str);
        	if (str.contains("d9542cf7-0344-43e7-a6db-00203f8c49d8")) {
        		System.out.println(cache.getBulk().get(str));
        		break;
        	}
        	
        }
        cache = cacheManager.getCache("KHAN_SESSION_LOGIN_EZWELMIND_ADMIN");
        System.out.println(cache.getBulk());
        for(Object object1 : cache.getBulk().keySet()) {
        	String str = (String) object1;
        	System.out.println(str);
        	if (str.contains("d9542cf7-0344-43e7-a6db-00203f8c49d8")) {
        		System.out.println(cache.getBulk().get(str));
        		break;
        	}
        }        
       
		System.out.println("\nend");
	}
	
	@Test
	public void putTest() {
		RemoteCacheManager cacheManager = new RemoteCacheManager("222.231.44.162:11722;222.231.44.175:11722");
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
		RemoteCacheManager cacheManager = new RemoteCacheManager("222.231.44.162:11722;222.231.44.175:11722");
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
}
