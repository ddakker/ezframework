package pe.kr.ddakker.ezframework.ispn;

import org.infinispan.spring.provider.SpringRemoteCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache.ValueWrapper;

import pe.kr.ddakker.ezframework.web.cache.Cache;

public class IspnCache<T> implements Cache<T> {

	private static Logger log = LoggerFactory.getLogger(IspnCache.class);

    private String cacheName;
    private SpringRemoteCacheManager cacheManager;

    public IspnCache(SpringRemoteCacheManager cacheManager, String cacheName) {
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
		return cacheManager.getNativeCacheManager().getCache(cacheName).entrySet().size();
	}
}
