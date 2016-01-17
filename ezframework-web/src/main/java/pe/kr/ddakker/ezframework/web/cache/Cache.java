package pe.kr.ddakker.ezframework.web.cache;

import java.util.List;

/**
 * 케쉬 제어 인터페이스
 * 		Redis, Infinispan 구현체에 적용
 * @author ddakker 2014. 10. 22.
 * @param <T>
 */
public interface Cache<T> {
	/**
	 * 조회
	 * @param key
	 * @return
	 * @auther ddakker 2014. 10. 22.
	 */
	public T get(Object key);

	/**
	 * 등록
	 * @param key
	 * @param value
	 * @auther ddakker 2014. 10. 22.
	 */
	public void put(Object key, T value);

	/**
	 * 삭제
	 * @param key
	 * @auther ddakker 2014. 10. 22.
	 */
	public void remove(Object key);

	/**
	 * 모두 삭제
	 *
	 * @auther ddakker 2014. 10. 22.
	 */
	public void clear();


	/**
	 * Key Size
	 * @return
	 * @auther ddakker 2015. 2. 11.
	 */
	public int size();
}
