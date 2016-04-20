package org.ezdevgroup.ezframework.security.access.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ezdevgroup.ezframework.security.service.EzAuthService;
import org.ezdevgroup.ezframework.security.service.EzResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

/**
 * 권한 정보 로드
 * @author ddakker 2014. 8. 28.
 *
 */
public class EzSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static Logger log = LoggerFactory.getLogger(EzSecurityMetadataSource.class);

	private AntPathMatcher urlMatcher = new AntPathMatcher();

	private EzAuthService ezAuthService;
	private EzResourceService ezResourceService;


    private Map<String,Collection<ConfigAttribute>> relationMap = null;


    public EzSecurityMetadataSource(EzAuthService ezAuthService, EzResourceService ezResourceService) {
    	this.ezAuthService = ezAuthService;
    	this.ezResourceService = ezResourceService;
    	loadResourceAndAuthRelation();
	}


	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
		Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
		String requestUrl = ((FilterInvocation)obj).getRequestUrl();

        Iterator<String> it = relationMap.keySet().iterator();
        while(it.hasNext()) {
        	String url = it.next();
        	if (requestUrl.indexOf("?")!=-1) {
        		requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
        	}
        	log.debug("===== , matcher[{}], requestUrl[{}], url[{}]", urlMatcher.match(url, requestUrl), requestUrl, url);
        	if (urlMatcher.match(url, requestUrl)) {
        		attributes.addAll(relationMap.get(url));
        	}
        }
        if (log.isDebugEnabled()) {
        	Collection<? extends GrantedAuthority> grantedAuthorities = new ArrayList<>();
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();
			if (authentication != null) {
				grantedAuthorities = authentication.getAuthorities();
			}
			log.debug("===== 접근에 필요한 권한 목록: {}", attributes);
			log.debug("===== 본인에 할당된 권한 목록: {}", grantedAuthorities);
        }
		return attributes.size()==0?null:attributes;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}



	/**
	 * 권한 별 리소스 셋팅
	 *
	 * @auther ddakker 2014. 8. 14.
	 */
	private void loadResourceAndAuthRelation() {
		relationMap = new HashMap<String,Collection<ConfigAttribute>>();

		List<String> auths = ezAuthService.getTotalAuthsQuery();

		if(auths != null) {
			for(String auth : auths) {
				List<String> resources = ezResourceService.getAuthResourceUrlQuery(auth);
				if(resources != null) {
					for(String resource : resources) {
						Collection<ConfigAttribute> configAttributes = null;
						ConfigAttribute configAttribute = new SecurityConfig(auth);
						if(relationMap.containsKey(resource)){
							configAttributes = relationMap.get(resource);
							configAttributes.add(configAttribute);
						}else{
							configAttributes = new ArrayList<ConfigAttribute>() ;
							configAttributes.add(configAttribute);
							relationMap.put(resource, configAttributes);
			             }
					}

				}

			}
		}
	}

}
