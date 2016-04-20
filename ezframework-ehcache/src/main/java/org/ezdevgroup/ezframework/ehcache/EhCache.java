package org.ezdevgroup.ezframework.ehcache;
import java.util.List;

import org.ezdevgroup.ezframework.web.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * Redis 캐쉬 제어
 * @author ddakker 2014. 10. 22.
 * @param <T>
 */
public class EhCache<T> implements Cache<T> {
	private static Logger log = LoggerFactory.getLogger(EhCache.class);

    private String cacheName;
    private EhCacheCacheManager cacheManager;

    public EhCache(EhCacheCacheManager cacheManager, String cacheName) {
    	this.cacheName = cacheName;
        this.cacheManager = cacheManager;

    }

    @Override
	public T get(Object key) {
    	ValueWrapper value = cacheManager.getCache(cacheName).get(key);
		return (T) (value==null?null:value.get());
	}

    @Override
	public void put(Object key, T value) {
		cacheManager.getCache(cacheName).put(key, value);
	}

    @Override
	public void remove(Object key) {
    	cacheManager.getCache(cacheName).evict(key);
    }

    @Override
	public void clear() {
		cacheManager.getCache(cacheName).clear();
	}

	@Override
	public int size() {
		return cacheManager.getCacheManager().getCache(cacheName).getSize();
	}

	public List<Object> keys() {
		return cacheManager.getCacheManager().getCache(cacheName).getKeys();
	}
}