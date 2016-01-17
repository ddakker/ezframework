package pe.kr.ddakker.ezframework.web.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * 
 * ---------------------------------------------------------------------- 
 * 날짜 작업자 수정내역 
 * ---------------------------------------------------------------------- 
 * 2015. 12. 15. ddakker 신규생성
 */
public class FullBeanNameGenerator implements org.springframework.beans.factory.support.BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return definition.getBeanClassName();
	}
}